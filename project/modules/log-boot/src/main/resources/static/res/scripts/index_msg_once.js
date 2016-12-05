/**
 * 只在页面载入时更新一次的请求
 */
function requestMsgOnce() {

    var eleDOM = document.getElementById("totalAppCounts");
    var dataToSend = "";
    var targetServerURL = LOGBASE_HOST_ENDPOINT + "/logb/apps/counts"
    $.ajax({
        url: targetServerURL,
        type: 'GET',
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: dataToSend,
        async: true,
        timeout: 4000,
        dataType: 'json',
        success: function (response) {
            console.log("请求成功 " + response);
            console.log( response);
            eleDOM.innerHTML = response.contents;

        },
        error: function (response) {
            console.log("请求失败 " + response);

        }
    });


}