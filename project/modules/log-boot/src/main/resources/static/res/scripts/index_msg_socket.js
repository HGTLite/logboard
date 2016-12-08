/**
 * 监听实时新消息
 */
function waitingForSocketMsg() {

    //var msgURLOrigin="http://localhost:8080"
    var msgURLOrigin = "";

    var socket = new SockJS(msgURLOrigin + "/logging");
    var stompClient = Stomp.over(socket);
    //取消控制台日志，正式部署时需注释
    // stompClient.debug = null;

    stompClient.connect({}, function (frame) {

            //region 日志处理量更新，订阅主题log-counts-streaming
            var topicLogCounts = "/topic/log-counts-streaming";
            stompClient.subscribe(msgURLOrigin + topicLogCounts, function (data) {
                var addCounts = parseInt(data.body);
                // console.log("新增条数是 " + addCounts + " 条记录");
                //获取现有文字
                var oldCounts = parseInt(document.getElementById("totalLogCounts").innerHTML);
                // console.log("现有文字是 " + oldCounts + " 条记录");
                //添加新的处理量
                var newCounts = addCounts + oldCounts;
                document.getElementById("totalLogCounts").innerHTML = newCounts.toString();
                // console.log("更新后的文字是" + newCounts);
            });
            //endregion 日志处理量更新

            //region 异常处理量更新，订阅主题log-exp-streaming
            var topicLogCounts = "/topic/log-exp-streaming";
            stompClient.subscribe(msgURLOrigin + topicLogCounts, function (data) {
                var addCounts = parseInt(data.body);
                var oldCounts = parseInt(document.getElementById("newExpCounts").innerHTML);
                var newCounts = addCounts + oldCounts;
                document.getElementById("newExpCounts").innerHTML = newCounts.toString();
                // console.log("更新后的文字是" + newCounts);
            });
            //endregion 异常处理量更新

        }
    );

}

function renderDOMExpCurve() {

}