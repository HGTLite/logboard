/**
 * 监听新消息
 */
function waitingForNewMsg(){

    //var msgURLOrigin="http://localhost:8080"
    var msgURLOrigin = "";
    var totalLogCounts = $("#totalLogCounts");
    var socket = new SockJS(msgURLOrigin + "/logging");

    var stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
            //订阅的主题名为message
            var topicLogCounts = "/topic/log-counts-streaming";
            stompClient.subscribe(msgURLOrigin + topicLogCounts, function (data) {
                var counts = data.body;
                totalLogCounts.text(counts);
            });
        }
    );

}

function renderDOMExpCurve(){

}