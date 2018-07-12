<script>
    var webSocket = null;
    if('WebSocket' in window) {
        webSocket = new WebSocket('ws://sell/wechat/webSocket');
    }else {
        alert('浏览器不支持webSocket');
    }

    webSocket.onopen = function(event) {
        console.log('建立连接')
    }

    webSocket.onclose = function(event) {
        console.log('连接关闭');
    }

    webSocket.onmessage = function(event) {
        console.log('收到消息：' + event.data);
        //弹窗提示, 播放音乐
    }

    webSocket.onerror = function(event) {
        alert('websocket通信发送错误');
    }

    window.onbeforeonload = function() {
        webSocket.close();
    }

</script>