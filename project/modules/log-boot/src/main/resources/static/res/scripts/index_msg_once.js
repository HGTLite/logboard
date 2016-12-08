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
            document.getElementById("totalLogCounts").innerHTML ="***";

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
            document.getElementById("newExpCounts").innerHTML ="***";

        }
    });
    //endregion 请求待处理异常数量，同步

    //region 请求接入应用系统数
    var dataToSend0 = "";
    var targetServerURL = LOGBASE_HOST_ENDPOINT + "/logb/apps/counts"
    $.ajax({
        url: targetServerURL,
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
            document.getElementById("totalAppsCounts").innerHTML ="***";
        }
    });
    //endregion 请求接入应用系统数


}