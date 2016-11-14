
var apps = apps || {};
apps.namespace = function (str) {
    var parts = str.split("."),
        parent = apps,
        i = 0,
        l = 0;

    if (parts[0] === "apps") {
        parts = parts.slice(1);
    }

    for (i = 0, l = parts.length; i < l; i++) {
        if (typeof parent[parts[i]] === "undefined") {
            parent[parts[i]] = {};
        }
        parent = parent[parts[i]];
    }
    return parent;
}

apps.attrs = {
    "mapSources": [
        {
            "mapbox": "//{s}.basemaps.cartocdn.com/dark_all/{z}/{x}/{y}.png"
        },
        {
            "autonavi": "//webrd0{s}.is.autonavi.com/appmaptile?lang=zh_cn&size=1&scale=1&style=8&x={x}&y={y}&z={z}"
        },
        {
            "osm": "//{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        }
    ]
};

apps.data = {
    "hgtPoint": {
        "type": "FeatureCollection",
        "crs": {
            "type": "name",
            "properties": {
                "name": "urn:ogc:def:crs:OGC:1.3:CRS84"
            }
        },
        "features": [
            {
                "type": "Feature",
                "properties": {
                    "time": 16,
                    "id": "route1",
                    "name": "lhkServerPoint"
                },
                "geometry": {
                    "type": "Point",
                    "coordinates": [
                        114.400,
                        30.503
                    ]
                }
            }
        ]
    },

    "ipPoints1": {
        "type": "FeatureCollection",
        "crs": {
            "type": "name",
            "properties": {
                "name": "urn:ogc:def:crs:OGC:1.3:CRS84"
            }
        },
        "features": [
            {
                "type": "Feature",
                "properties": {
                    "time": 1,
                    "id": "route1",
                    "name": "p1"
                },
                "geometry": {
                    "type": "Point",
                    "coordinates": [
                        114.360477,
                        30.527036
                    ]
                }
            },
            {
                "type": "Feature",
                "properties": {

                    "time": 16,
                    "id": "route1",
                    "name": "p2"
                },
                "geometry": {
                    "type": "Point",
                    "coordinates": [
                        114.420,
                        30.520
                    ]
                }
            }
        ]
    }
};

apps.maps = {
    mapOperations: (function () {
        return {
            initBasemap: function (mapUrl) {
                return L.tileLayer(mapUrl);
            }

        }
    })(),
    animations: {
        arcInterpolate: function (points) {
            return points.join("A 30,30 0 0 1 ");
        }

    },
    general: {
        projectPoint: function (x, y) {
            var point = apps.attrs.map.latLngToLayerPoint(new L.LatLng(y, x));
            this.stream.point(point.x, point.y);
        },
        applyLatLngToLayer: function (d) {
            //获取纬度
            var y = d.geometry.coordinates[1];
            //获取经度
            var x = d.geometry.coordinates[0];
            //LatLngToLayerPoint is used to get coordinates for the layer's new position
            return apps.attrs.map.latLngToLayerPoint(new L.LatLng(y, x));
        }
    }
};

apps.start = {
    init: function () {
        var mData = apps.namespace("apps.data");


        var mapTiles = L.tileLayer(apps.attrs.mapSources[0].mapbox);
        apps.attrs.map = L.map('map').addLayer(mapTiles).setView([30.520, 114.360], 10);
        var map = apps.namespace("apps.attrs.map");
        apps.attrs.svg = d3.select(map.getPanes().overlayPane).append("svg");
        apps.attrs.g = apps.attrs.svg.append("g").attr("class", "leaflet-zoom-hide");

    },
    go: function () {
        var mData = apps.namespace("apps.data");
        var mMaps = apps.namespace("apps.maps");

        var mMapOperations = apps.namespace("apps.maps.mapOperations");
        var map = apps.namespace("apps.attrs.map");
        var svg = apps.namespace("apps.attrs.svg");
        var g = apps.namespace("apps.attrs.g");

        var lhkServerPoint = mData.hgtPoint;
        var ipPoints = mData.ipPoints1;

//            console.log(lhkServerPoint)
//            console.log(ipPoints)


        //把导入的各个坐标点作为起点，把服务器位置作为终点，构建起止点数组
        var startEndArr = [];
        var pLength = ipPoints.features.length;
        for (var i = 0; i < pLength; i++) {
            var tempLine = cloneObj(lhkServerPoint);
            var x1 = lhkServerPoint.features[0];
            tempLine.features[0] = ipPoints.features[i];
            tempLine.features[1] = x1;
            startEndArr[i] = tempLine;
        }
//            console.log(startEndArr);

        //定义方法：将经纬度转成屏幕地图图层点
        var transform = d3.geo.transform({
            point: apps.maps.general.projectPoint
        });
        var d3path = d3.geo.path().projection(transform);


        var collection = startEndArr[0];
        var featuresdata = collection.features;


        var toLine = d3.svg.line()
            .interpolate(function (points) {
                return points.join("A 30,30 0 0 1 ");
            })
            .x(function (d) {
                return mMaps.general.applyLatLngToLayer(d).x;
            })
            .y(function (d) {
                return mMaps.general.applyLatLngToLayer(d).y;
            });


//            目标服务器点，用于reset刷新
        var ptFeatures = g.selectAll("circle")
            .data(lhkServerPoint.features)
            .enter()
            .append("circle")
            .attr("r", 3)
            .attr("class", "travelMarker");


        //声明每对起止点的线
//            for (var ii = 0; ii < pLength; ii++) {
//
//
//
//            }

        var lines = cloneObj([featuresdata]);
        lines[1] = cloneObj(featuresdata);

        lines[1][0].geometry.coordinates[0] = 114.43;

//            console.log(lines);

        //已走路线
        var linePath = g.selectAll(".lineConnect")
            .data(lines)
            .enter()
            .append("path")
            .attr("class", "lineConnect");


        // 运动点
//            var marker = g.append("circle")
//                    .attr("r", 10)
//                    .attr("id", "marker")
//                    .attr("class", "travelMarker");

        // NOT BEST Practice. Style start & end points.
//            var originANDdestination = [featuresdata[0], featuresdata[1]]

        map.on("viewreset", reset);

        // this puts stuff on the map!
        reset();

        // Reposition the SVG to cover the features. 注意裁剪
        function reset() {
            var bounds = d3path.bounds(ipPoints),
                topLeft = bounds[0],
                bottomRight = bounds[1];


            ptFeatures.attr("transform",
                function (d) {
                    return "translate(" +
                        mMaps.general.applyLatLngToLayer(d).x + "," +
                        mMaps.general.applyLatLngToLayer(d).y + ")";
                });


            // Setting the size and location of the overall SVG container
            svg.attr("width", bottomRight[0] - topLeft[0] + 100)
                .attr("height", bottomRight[1] - topLeft[1] + 100)
                .style("left", topLeft[0] - 50 + "px")
                .style("top", topLeft[1] - 50 + "px");


            linePath.attr("d", toLine);

            var totalLength = linePath.node().getTotalLength();

            linePath.attr("stroke-dasharray", totalLength + "," + totalLength)
                .attr("stroke-dashoffset", totalLength)
                .transition()
                .duration(300)
                .ease("linear")
                .attr("stroke-dashoffset", 0)
            ;

            g.attr("transform", "translate(" + (-topLeft[0] + 50) + "," + (-topLeft[1] + 50) + ")");
        } // end reset

    }


//        }
}

apps.start.init();
apps.start.go();