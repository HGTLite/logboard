/**
 * 日志概览曲线
 */
function createLogsGeneralCurve() {

    var limit = 60 * 1,
        duration = 5 * 1000,
        now = new Date(Date.now() - duration);

    var width = 720,
        height = 400;

    //**数据配置
    var groups = {
        current: {
            value: 0,
            color: '#00c5ff',
            data: d3.range(limit).map(function () {
                return 0
            })
        }
    };

    // console.log("制图的数据是");
    // console.log(groups.current.data);

    var x = d3.time.scale()
        .domain([now - (limit - 2), now - duration])
        .range([0, width]);

    var y = d3.scale.linear()
        .domain([0, 110])
        .range([height, 0]);

    var line = d3.svg.line()
        .interpolate('basis')
        .x(function (d, i) {
            return x(now - (limit - 1 - i) * duration)
        })
        .y(function (d) {
            return y(d)
        });

    var svg = d3.select('#svgLogsGenaral').append('svg')
        .attr('class', 'chart')
        .attr('width', width)
        .attr('height', height + 25);

    var axis = svg.append('g')
        .attr('class', 'x axis')
        .attr('transform', 'translate(0,' + height + ')')
        .call(x.axis = d3.svg.axis().scale(x).orient('bottom'));

    var paths = svg.append('g');

    for (var name in groups) {
        var group = groups[name]
        group.path = paths.append('path')
            .data([group.data])
            .attr('class', name + ' group')
            .style('stroke', group.color)
    }

    function tick() {

        now = new Date()

        // Add new values
        for (var name in groups) {
            var group = groups[name]
            //group.data.push(group.value) // Real values arrive at irregular intervals
            group.data.push(20 + Math.random() * 100)
            group.path.attr('d', line)
        }

        // Shift domain
        x.domain([now - (limit - 2) * duration, now - duration])

        // Slide x-axis left
        axis.transition()
            .duration(duration)
            .ease('linear')
            .call(x.axis)

        // Slide paths left
        paths.attr('transform', null)
            .transition()
            .duration(duration)
            .ease('linear')
            .attr('transform', 'translate(' + x(now - (limit - 1) * duration) + ')')
            .each('end', tick)

        // Remove oldest data point from each group
        for (var name in groups) {
            var group = groups[name]
            group.data.shift()
        }
    }

    tick()
}

/**
 * 日志分布饼状图
 */
function createLogsGeneralPie(pieData) {
    // console.log(pieData);

    //region 设置统计时间
    var dateNow = new Date();
    var endTime = convertJsTimeToStr(dateNow);
    var startTime = operateDatetimeByHour(endTime, -1);
    document.getElementById("updatePieIndicator").innerHTML = startTime + "  -  " + endTime;
    //endregion

//配置数据源
    var statsCountsByApp = {};

    if (isEmpty(pieData) || typeof pieData == "undefined") {
        // console.log("为空")

        statsCountsByApp = [
            {key: "One", value: 5},
            {key: "Two", value: 2},
            {key: "Three", value: 9},
            {key: "Four", value: 7},
        ];
    }else{
        // console.log("非空");
        statsCountsByApp = pieData;

    }

    var height = 325;
    var width = 325;

    nv.addGraph(function () {
        var chart1 = nv.models.pieChart()
            .x(function (d) {
                return d.key
            })
            .y(function (d) {
                return d.value
            })
            .donut(true)
            .width(width)
            .height(height)
            .padAngle(.08)
            .cornerRadius(5)
            .id('donut1'); // allow custom CSS for this one svg

        // chart1.title("100%");
        // chart1.pie.labelsOutside(true).donut(true);
        chart1.showLabels(false);
        d3.select("#svgLogsGenaralPie")
            .datum(statsCountsByApp)
            .transition().duration(1200)
            .call(chart1);


        return chart1;

    });
}

/**
 * 日志异常曲线
 */
function createLogsExpCurve() {
    var limit = 60 * 1,
        duration = 1 * 1000,
        now = new Date(Date.now() - duration)

    var width = 1080,
        height = 280;

    //**配置数据
    var groups = {
        current: {
            value: 0,
            color: '#ff6666',
            data: d3.range(limit).map(function () {
                return 0
            })
        }
    };

    var x = d3.time.scale()
        .domain([now - (limit - 2), now - duration])
        .range([0, width]);

    var y = d3.scale.linear()
        .domain([0, 110])
        .range([height, 0]);

    var line = d3.svg.line()
        .interpolate('basis')
        .x(function (d, i) {
            return x(now - (limit - 1 - i) * duration)
        })
        .y(function (d) {
            return y(d)
        });

    var svg = d3.select('#svgLogsExp').append('svg')
        .attr('class', 'chart')
        .attr('width', width)
        .attr('height', height + 50)

    var axis = svg.append('g')
        .attr('class', 'x axis')
        .attr('transform', 'translate(0,' + height + ')')
        .call(x.axis = d3.svg.axis().scale(x).orient('bottom'))

    var paths = svg.append('g')

    for (var name in groups) {
        var group = groups[name]
        group.path = paths.append('path')
            .data([group.data])
            .attr('class', name + ' group')
            .style('stroke', group.color)
    }


    function tick() {
        now = new Date()

        // Add new values
        for (var name in groups) {
            var group = groups[name]
            //group.data.push(group.value) // Real values arrive at irregular intervals
            group.data.push(20 + Math.random() * 100)
            group.path.attr('d', line)
        }

        // Shift domain
        x.domain([now - (limit - 2) * duration, now - duration])

        // Slide x-axis left
        axis.transition()
            .duration(duration)
            .ease('linear')
            .call(x.axis)

        // Slide paths left
        paths.attr('transform', null)
            .transition()
            .duration(duration)
            .ease('linear')
            .attr('transform', 'translate(' + x(now - (limit - 1) * duration) + ')')
            .each('end', tick)

        // Remove oldest data point from each group
        for (var name in groups) {
            var group = groups[name]
            group.data.shift()
        }
    }

    tick()
}

/**
 * 日志统计聚簇图
 */
function createLogsStatsForces() {

    var width = 1080,
        height = 400,
        padding = 5, // separation between nodes
        maxRadius = 10;

    //**配置数据
    var n = 200; // total number of nodes
    var m = 4; // number of distinct clusters

    var logTypes = new Array("LOGIN", "GENERAL", "TESTS");
    renderDOMTypeNameDiv(logTypes);

    var color = d3.scale.category10()
        .domain(d3.range(m));

    var x = d3.scale.ordinal()
        .domain(d3.range(m))
        .rangePoints([0, width], 1);

    var nodes = d3.range(n).map(function () {

        var i = Math.floor(Math.random() * m),
            v = (i + 1) / m * -Math.log(Math.random());

        return {
            radius: Math.sqrt(v) * maxRadius,
            color: color(i),
            cx: x(i),
            cy: height / 2
        };

    });

    // console.log("节点数据");
    // console.log(nodes);

    var force = d3.layout.force()
        .nodes(nodes)
        .size([width, height])
        .gravity(0)
        .charge(0)
        .on("tick", tick)
        .start();

    var svg = d3.select("#svgLogsStats").append("svg")
        .attr("width", width)
        .attr("height", height);

    var circle = svg.selectAll("circle")
        .data(nodes)
        .enter().append("circle")
        .attr("r", function (d) {
            return d.radius;
        })
        .style("fill", function (d) {
            return d.color;
        })
        .call(force.drag);

    function tick(e) {
        circle
            .each(gravity(.2 * e.alpha))
            .each(collide(.5))
            .attr("cx", function (d) {
                return d.x;
            })
            .attr("cy", function (d) {
                return d.y;
            });
    }

    // Move nodes toward cluster focus.
    function gravity(alpha) {
        return function (d) {
            d.y += (d.cy - d.y) * alpha;
            d.x += (d.cx - d.x) * alpha;
        };
    }

    // Resolve collisions between nodes.
    function collide(alpha) {
        var quadtree = d3.geom.quadtree(nodes);
        return function (d) {
            var r = d.radius + maxRadius + padding,
                nx1 = d.x - r,
                nx2 = d.x + r,
                ny1 = d.y - r,
                ny2 = d.y + r;

            quadtree.visit(function (quad, x1, y1, x2, y2) {
                if (quad.point && (quad.point !== d)) {
                    var x = d.x - quad.point.x,
                        y = d.y - quad.point.y,
                        l = Math.sqrt(x * x + y * y),
                        r = d.radius + quad.point.radius + (d.color !== quad.point.color) * padding;
                    if (l < r) {
                        l = (l - r) / l * alpha;
                        d.x -= x *= l;
                        d.y -= y *= l;
                        quad.point.x += x;
                        quad.point.y += y;
                    }
                }
                return x1 > nx2 || x2 < nx1 || y1 > ny2 || y2 < ny1;
            });
        };
    }
}

function renderDOMTypeNameDiv(typeNames) {

    var types = typeNames;
    var typeLength = typeNames.length;
    var html = "";

    for (var i = 0; i < typeLength; i++) {
        html += "<div style=\"width:" + 100 / typeLength + "%\" class=\"_float_left _text_align_center text-muted\" >日志类型-" + types[i] + "</div>";
    }
    document.getElementById("typeNameDiv").innerHTML = html;

}