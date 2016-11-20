/**
 * 生成普通日志概览曲线
 */
function createHourlyLogsCurve() {
    var n = 60;
    var random = d3.random.normal(1000, 200);
    var data = d3.range(0, n, 1).map(random);

    var svg = d3.select("#svgLogsGenaral"),
        margin = {top: 20, right: 20, bottom: 40, left: 100},
        width = svg.attr("width") - margin.left - margin.right,
        height = svg.attr("height") - margin.top - margin.bottom,
        g = svg.append("g").attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    var x = d3.scale.linear()
        .domain([1, n - 2])
        .range([0, width]);

    var y = d3.scale.linear()
        .domain([500, 1500])
        .range([height, 0]);

    var xAxis = d3.svg.axis()
        .scale(x)
        .tickSize(-height);

    var yAxis = d3.svg.axis()
        .scale(y)
        .ticks(4)
        .orient("left");

    var line = d3.svg.line()
        .x(function (d, i) {
            return x(i);
        })
        .y(function (d, i) {
            return y(d);
        })
        .interpolate("basis");

    g.append("defs").append("clipPath")
        .attr("id", "clip")
        .append("rect")
        .attr("width", width)
        .attr("height", height);

    g.append("g")
        .attr("class", "axis axis--x")
        .attr("transform", "translate(0," + y(500) + ")")
        .call(xAxis);

    g.append("g")
        .attr("class", "axis axis--y")
        .attr("transform", "translate(" + x(0) + ",0)")
        .call(yAxis);

    g.append("g")
        .attr("clip-path", "url(#clip)")
        .append("path")
        .datum(data)
        .attr("class", "logs_genereal_line")
        .transition()
        .duration(1000)
        .ease("linear")
        .on("start", function tick() {

            // Push a new data point onto the back.
            data.push(random());

            // Redraw the line.
            d3.select(this)
                .attr("d", line)
                .attr("transform", null);

            // Slide it to the left.
            d3.active(this)
                .attr("transform", "translate(" + x(0) + ",0)")
                .transition()
                .on("start", tick);

            // Pop the old data point off the front.
            data.shift();
        });
}

/**
 * 生成异常日志变化曲线
 */
function createExpLogsCurve() {
    var n = 26;
    var random = d3.randomNormal(50, 15);
    var data = d3.range(0, n, 1).map(random);

    var svg = d3.select("#svgLogsExp"),
        margin = {top: 20, right: 20, bottom: 40, left: 100},
        width = svg.attr("width") - margin.left - margin.right,
        height = svg.attr("height") - margin.top - margin.bottom,
        g = svg.append("g").attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    var x = d3.scale.linear()
        .domain([1, n - 2])
        .range([0, width]);

    var y = d3.scale.linear()
        .domain([0, 100])
        .range([height, 0]);

    var line = d3.line()
        .curve(d3.curveBasis)
        .x(function (d, i) {
            return x(i);
        })
        .y(function (d, i) {
            return y(d);
        });

    g.append("defs").append("clipPath")
        .attr("id", "clip")
        .append("rect")
        .attr("width", width)
        .attr("height", height);

    g.append("g")
        .attr("class", "axis axis--x")
        .attr("transform", "translate(0," + y(0) + ")")
        .call(d3.axisBottom(x));

    g.append("g")
        .attr("class", "axis axis--y")
        .attr("transform", "translate(" + x(0) + ",0)")
        .call(d3.axisLeft(y));

    g.append("g")
        .attr("clip-path", "url(#clip)")
        .append("path")
        .datum(data)
        .attr("class", "logs_exp_line")
        .transition()
        .duration(1000)
        .ease(d3.easeLinear)
        .on("start", function tick() {

            // Push a new data point onto the back.
            data.push(random());

            // Redraw the line.
            d3.select(this)
                .attr("d", line)
                .attr("transform", null);

            // Slide it to the left.
            d3.active(this)
                .attr("transform", "translate(" + x(0) + ",0)")
                .transition()
                .on("start", tick);

            // Pop the old data point off the front.
            data.shift();
        });
}

/**
 * 生成异常分布统计环形图
 */
function createExpStatsPie() {

    //region 绘制饼状图
    var svgPieExp = d3.select("#svgExpStatsPie");

    var w = svgPieExp.attr("width");
    var h = svgPieExp.attr("height");

    // var dataset = [5, 10, 20, 45, 6, 25];
    var expDistData = [
        {name: "app1二维地图", value: 5},
        {name: "app2三维地图", value: 10},
        {name: "app3KPI网站", value: 20},
        {name: "app4云平台门户", value: 45},
        {name: "app5点餐系统", value: 6},
        {name: "app6日志测试生产者", value: 25}
    ];

    var outerRadius = w / 2;
    var innerRadius = w / 3;
    var arc = d3.arc()
        .innerRadius(innerRadius)
        .outerRadius(outerRadius);

    var pie = d3.pie();

    var color = d3.scaleOrdinal(d3.schemeCategory20);

    var dataset = new Array();
    for (var i = 0; i < expDistData.length; i++) {
        dataset[i] = expDistData[i].value;
    }

    var arcs = svgPieExp.selectAll("g.arc")
        .data(pie(dataset))
        .enter()
        .append("g")
        .attr("class", "arc")
        .attr("transform", "translate(" + outerRadius + "," + outerRadius + ")");

    arcs.append("path")
        .attr("fill", function (d, i) {
            return color(i);
        })
        .attr("d", arc);

    //Labels
    arcs.append("text")
        .attr("transform", function (d) {
            return "translate(" + arc.centroid(d) + ")";
        })
        .attr("text-anchor", "middle")
        .text(function (d, i) {
            return expDistData[i].value;
        });
    //endregion

    //region 绘制图例
    var svgLegendExp = d3.select("#svgExpStatsLegend");

    var g = svgLegendExp.selectAll("g")
        .data(expDistData)
        .enter()
        .append("g")
        .attr("width", 18)
        .attr("height", 18)
        .attr("transform", function (d, i) {
            return "translate(0," + i * 36 + ")";
        });

    g.append("rect")
        .attr("width", 18)
        .attr("height", 18)
        .style("fill", function (d, i) {
            return color(i);
        });

    g.append("text")
        .attr("x", 24)
        .attr("y", 9)
        .attr("dy", ".35em")
        .text(function (d) {
            return d.name;
        });
    //endregion

}

/**
 * 生成异常统计环形图图例
 */
function createLogsTagsForce() {

    var margin = {top: 100, right: 100, bottom: 100, left: 100};

    var width = 960,
        height = 500,
        padding = 1.5, // separation between same-color circles
        clusterPadding = 6, // separation between different-color circles
        maxRadius = 12;

    var n = 200, // total number of nodes
        m = 10, // number of distinct clusters
        z = d3.scaleOrdinal(d3.schemeCategory20),
        clusters = new Array(m);

    // var svg = d3.select('body')
    //     .append('svg')
    //     .attr('height', height)
    //     .attr('width', width)
    //     .append('g').attr('transform', 'translate(' + width / 2 + ',' + height / 2 + ')');

    var svg = d3.select('#svgLogsForce')
        .append('g').attr('transform', 'translate(' + width / 2 + ',' + height / 2 + ')');

    var nodes = d3.range(200).map(() = > {
            var i = Math.floor(Math.random() * m),
            radius = Math.sqrt((i + 1) / m * -Math.log(Math.random())) * maxRadius,
        d = {cluster: i, r: radius};
    if (!clusters[i] || (radius > clusters[i].r)) clusters[i] = d;
    return d;
})
    ;

    var circles = svg.append('g')
            .datum(nodes)
            .selectAll('.circle')
            .data(d = > d
)
.
    enter().append('circle')
        .attr('r', (d) = > d.r
)
.
    attr('fill', (d) = > z(d.cluster)
)
.
    attr('stroke', 'black')
        .attr('stroke-width', 1);

    var simulation = d3.forceSimulation(nodes)
        .velocityDecay(0.2)
        .force("x", d3.forceX().strength(.0005))
        .force("y", d3.forceY().strength(.0005))
        .force("collide", collide)
        .force("cluster", clustering)
        .on("tick", ticked);

    function ticked() {
        circles
            .attr('cx', (d) = > d.x
    )
    .
        attr('cy', (d) = > d.y
    )
        ;
    }

    // These are implementations of the custom forces.
    function clustering(alpha) {
        nodes.forEach(function (d) {
            var cluster = clusters[d.cluster];
            if (cluster === d) return;
            var x = d.x - cluster.x,
                y = d.y - cluster.y,
                l = Math.sqrt(x * x + y * y),
                r = d.r + cluster.r;
            if (l !== r) {
                l = (l - r) / l * alpha;
                d.x -= x *= l;
                d.y -= y *= l;
                cluster.x += x;
                cluster.y += y;
            }
        });
    }

    function collide(alpha) {
        var quadtree = d3.quadtree().x((d) = > d.x
    ).
        y((d) = > d.y
    )
    .
        addAll(nodes);

        nodes.forEach(function (d) {
            var r = d.r + maxRadius + Math.max(padding, clusterPadding),
                nx1 = d.x - r,
                nx2 = d.x + r,
                ny1 = d.y - r,
                ny2 = d.y + r;
            quadtree.visit(function (quad, x1, y1, x2, y2) {

                if (quad.data && (quad.data !== d)) {
                    var x = d.x - quad.data.x,
                        y = d.y - quad.data.y,
                        l = Math.sqrt(x * x + y * y),
                        r = d.r + quad.data.r + (d.cluster === quad.data.cluster ? padding : clusterPadding);
                    if (l < r) {
                        l = (l - r) / l * alpha;
                        d.x -= x *= l;
                        d.y -= y *= l;
                        quad.data.x += x;
                        quad.data.y += y;
                    }
                }
                return x1 > nx2 || x2 < nx1 || y1 > ny2 || y2 < ny1;
            });
        });
    }
}