/**
 * 监听新消息
 */
function waitingForNewMsg() {

    //var msgURLOrigin="http://localhost:8080"
    var msgURLOrigin = "";
    // var totalLogCounts = document.getElementById("totalLogCounts").innerHTML;
    // console.log("获取div文字"+totalLogCounts);
    // var newNum= parseInt(totalLogCounts)+1;
    // console.log("加1后的文字是 "+newNum);
    // document.getElementById("totalLogCounts").innerHTML =newNum.toString();

    var socket = new SockJS(msgURLOrigin + "/logging");

    var stompClient = Stomp.over(socket);

    //region 日志处理量更新
    stompClient.connect({}, function (frame) {
            //订阅的主题名为message
            var topicLogCounts = "/topic/log-counts-streaming";
            stompClient.subscribe(msgURLOrigin + topicLogCounts, function (data) {
                var addCounts = parseInt(data.body);
                console.log("新增条数是 " + addCounts + " 条记录");
                //获取现有文字
                var oldCounts = parseInt(document.getElementById("totalLogCounts").innerHTML);
                console.log("现有文字是 " + oldCounts + " 条记录");
                //添加新的处理量
                var newCounts = addCounts + oldCounts;
                document.getElementById("totalLogCounts").innerHTML = newCounts.toString();
                console.log("更新后的文字是" + newCounts);
            });
        }
    );
    //endregion

}

function renderDOMExpCurve() {

}