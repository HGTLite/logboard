/**
 * 只在页面载入时更新一次的请求
 */
function requestMsgOnce() {

    //region 请求日志处理量，同步
    var dataToSend1 = "";
    var targetServerURL1 = LOGBASE_HOST_ENDPOINT + "/logb/counts/rid/logCountsStreaming"
    $.ajax({
        url: targetServerURL1,
        type: 'GET',
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: dataToSend1,
        async: false,
        timeout: 4000,
        dataType: 'json',
        success: function (response) {
            console.log("请求成功--日志处理量 ");
            // console.log( response);
            document.getElementById("totalLogCounts").innerHTML = response.data.counts.toString();
        },
        error: function (response) {
            console.log("请求失败--日志处理量 ");
            console.log(response);
            document.getElementById("totalLogCounts").innerHTML = "***";

        }
    });
    //endregion 请求日志处理量，同步

    //region 请求待处理异常数量，同步
    var dataToSend2 = "";
    var targetServerURL2 = LOGBASE_HOST_ENDPOINT + "/logb/counts/rid/expCountsStreaming"
    $.ajax({
        url: targetServerURL2,
        type: 'GET',
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: dataToSend2,
        async: false,
        timeout: 4000,
        dataType: 'json',
        success: function (response) {
            console.log("请求成功--待处理异常数量 ");
            // console.log( response);
            document.getElementById("newExpCounts").innerHTML = response.data.counts.toString();
        },
        error: function (response) {
            console.log("请求失败--待处理异常数量 ");
            console.log(response);
            document.getElementById("newExpCounts").innerHTML = "***";

        }
    });
    //endregion 请求待处理异常数量，同步

    //region 请求接入应用系统数
    var dataToSend0 = "";
    var targetServerURL0 = LOGBASE_HOST_ENDPOINT + "/logb/apps/counts"
    $.ajax({
        url: targetServerURL0,
        type: 'GET',
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: dataToSend0,
        async: true,
        timeout: 4000,
        dataType: 'json',
        success: function (response) {
            console.log("请求成功--接入应用数 ");
            // console.log( response);
            document.getElementById("totalAppsCounts").innerHTML = response.data.contents.toString();
        },
        error: function (response) {
            console.log("请求失败--接入应用数 ");
            console.log(response);
            document.getElementById("totalAppsCounts").innerHTML = "***";
        }
    });
    //endregion 请求接入应用系统数

    //region 请求近一小时按应用统计的日志数
    var dataToSend3 = "";
    // var dateNow = convertJsTimeToStr(new Date());
    var dateNow = "2016-12-08 21:20:00";
    // var dateNow = "2016-12-08 20:00:00";
    // var dateNow = "2016-12-08 14:00:00";
    var targetServerURL3 = LOGBASE_HOST_ENDPOINT + "/logb/by/apps/pie/hour/" + dateNow;
    $.ajax({
        url: targetServerURL3,
        type: 'GET',
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: dataToSend3,
        async: true,
        timeout: 4000,
        dataType: 'json',
        success: function (response) {
            console.log("请求成功--近一小时按应用统计的日志数 ");
            var appKV = response.data;
            var alength = appKV.length;
            console.log(appKV);
            //饼状图制图数据
            var pieDate = new Array();
            if (alength == 0) {
                alert("饼状图无最新日志");
            } else {

                for (var i = 0; i < alength; i++) {
                    var app1={};
                    var appCode=appKV[i].appCode;
                    var appName="";
                    var dataToSend = "";
                    var targetServerURL = LOGBASE_HOST_ENDPOINT + "/logb/apps/id/appid"+appCode;
                    console.log(targetServerURL);
                    $.ajax({
                        url: targetServerURL,
                        type: 'GET',
                        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                        data: dataToSend,
                        async: false,
                        timeout: 4000,
                        dataType: 'json',
                        success: function (response) {
                            // console.log("请求成功 -- " + " ");
                            // console.log(response);
                            appName = response.data.appName;
                            // console.log(appName);

                        },
                        error: function (response) {
                            console.log("请求失败 -- " +" ");
                            // console.log(response);
                        }
                    });
                    app1.key = appName;
                    app1.value = appKV[i].counts;
                    pieDate.push(app1);
                }
            }

            // console.log(pieDate);

        },
        error: function (response) {
            console.log("请求失败--近一小时按应用统计的日志数 ");
            console.log(response);
            createLogsGeneralPie(null);

            // document.getElementById("totalAppsCounts").innerHTML = "***";
        }
    });
    //endregion 请求近一小时按应用统计的日志数

}