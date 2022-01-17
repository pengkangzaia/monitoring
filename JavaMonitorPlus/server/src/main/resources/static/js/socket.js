var interval_time = 60000;
var pid = GetQueryString("pid");
var address = GetQueryString("address");

// Socket
function gc() {
    var socket = new SockJS('/websocket');
    var stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/gc', function (d) {
            var data = JSON.parse(d.body)
            s0(data)
            s1(data)
            eden(data)
            old(data)
            mc(data)
            ccsc(data)
            gcn(data)
            gct(data)
            setTimeout(function () {
                stompClient.send("/app/gc", {}, JSON.stringify({"pid":pid,"address":address}));
            }, interval_time);
        });
        stompClient.send("/app/gc", {}, JSON.stringify({"pid":pid,"address":address}));
    });
}

function cl() {
    var socket = new SockJS('/websocket');
    var stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/class', function (d) {
            var data = JSON.parse(d.body)
            classn(data)
            classt(data)
            comn(data)
            comt(data)
            combyte(data)
            setTimeout(function () {
                stompClient.send("/app/class", {}, JSON.stringify({"pid":pid,"address":address}));
            }, interval_time);
        });
        stompClient.send("/app/class", {}, JSON.stringify({"pid":pid,"address":address}));
    });
}

function thread() {
    var socket = new SockJS('/websocket');
    var stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/thread', function (d) {
            var data = JSON.parse(d.body)
            thread(data);
            setTimeout(function () {
                stompClient.send("/app/thread", {}, JSON.stringify({"pid":pid,"address":address}));
            }, interval_time);
        });
        stompClient.send("/app/thread", {}, JSON.stringify({"pid":pid,"address":address}));
    });
}


function cpu() {
    var socket = new SockJS('/websocket');
    var stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/cpu', function (d) {
            var data = JSON.parse(d.body)
            cpuUsage(data);
            cpuLoad(data);
            setTimeout(function () {
                stompClient.send("/app/cpu", {}, JSON.stringify({"address":address}));
            }, interval_time);
        });
        stompClient.send("/app/cpu", {}, JSON.stringify({"address":address}));
    });
}

function memory() {
    var socket = new SockJS('/websocket');
    var stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/memory', function (d) {
            var data = JSON.parse(d.body)
            memoryUsage(data);
            memoryPercent(data);
            setTimeout(function () {
                stompClient.send("/app/memory", {}, JSON.stringify({"address":address}));
            }, interval_time);
        });
        stompClient.send("/app/memory", {}, JSON.stringify({"address":address}));
    });
}

function disk() {
    var socket = new SockJS('/websocket');
    var stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/disk', function (d) {
            var data = JSON.parse(d.body)
            diskio(data)
            diskkb(data)
            diskwait(data)
            diskserver(data)
            diskutil(data)
            setTimeout(function () {
                stompClient.send("/app/disk", {}, JSON.stringify({"address":address}));
            }, interval_time);
        });
        stompClient.send("/app/disk", {}, JSON.stringify({"address":address}));
    });
}

//链接
gc()
cl()
thread()
cpu()
memory()
disk()

//设置频率
$("#pinlv").click(function () {
    //prompt层
    layer.prompt({title: '请设置图表刷新频率,单位/秒'}, function (pass, index) {
        layer.close(index);
        interval_time = pass * 1000;
        layer.msg('设置成功！刷新频率：' + pass + '秒')
    });
});