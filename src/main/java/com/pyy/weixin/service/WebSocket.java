package com.pyy.weixin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Administrator on 2018/7/12 0012.
 */
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {

    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSockets.add(this);
        log.info("【WebSocket消息】有新的连接，总数：{}", webSockets.size());
    }

    @OnClose
    public void onClose() {
        webSockets.remove(this);
        log.info("【WebSocket消息】连接断开，总数：{}", webSockets.size());
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("【WebSocket消息】收到客户端发送的消息：{}" + message);
    }

    public void sendMessage(String message) {
        for(WebSocket webSocket: webSockets) {
            log.info("【WebSocket消息】广播消息，message={}", message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
