package com.feelcolor.website.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class SystemWebSocketHandler implements WebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(SystemWebSocketHandler.class);
    private static final Map<String, WebSocketSession> sessions = new HashMap<String, WebSocketSession>();

    /**
     * 第一次链接的处理方法
     *
     * @param session
     * @throws Exception
     */
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userName = getUserName(session);
        logger.info("websocket:【{}】 connect to the websocket success......", userName);
        /*WebSocketSession oldSession = sessions.get(userName);
        if (oldSession != null) {
            //如果已有相同编号的客户端链接，则将之前的客户端下线
            oldSession.sendMessage(new TextMessage("帐号在其他地方登录"));
            oldSession.close();
        }*/
        sessions.put(userName, session);
        session.sendMessage(new TextMessage("1111111111111Server:connected OK!"));
    }

    private String getUserName(WebSocketSession session) {
        return String.valueOf(session.getAttributes().get("username"));
    }

    /**
     * 处理客户端发来的消息
     *
     * @param wss
     * @param wsm
     * @throws Exception
     */
    public void handleMessage(WebSocketSession wss, WebSocketMessage<?> wsm) throws Exception {
        logger.info("websocket:【{}】 message: {}", getUserName(wss), wsm.getPayload().toString());
        TextMessage returnMessage = new TextMessage(wsm.getPayload()
                + " received at server");
        wss.sendMessage(returnMessage);
    }

    /**
     * 长链接出错的处理方法
     *
     * @param wss
     * @param thrwbl
     * @throws Exception
     */
    public void handleTransportError(WebSocketSession wss, Throwable thrwbl) throws Exception {
        if (wss.isOpen()) {
            wss.close();
        }
        String userName = getUserName(wss);
        sessions.remove(userName);
        logger.info("websocket:【{}】 connection error......", userName);
    }

    /**
     * 长链接关闭的处理方法
     *
     * @param wss
     * @param cs
     * @throws Exception
     */
    public void afterConnectionClosed(WebSocketSession wss, CloseStatus cs) throws Exception {
        sessions.remove(getUserName(wss));
        logger.info("websocket:【{}】 connection closed......", getUserName(wss));
    }

    public boolean supportsPartialMessages() {
        logger.info("websocket connection supportsPartialMessages");
        return false;
    }


    /**
     * 给某个用户发送消息
     *
     * @param message
     */
    public static String sendMessageToUser(final SocketMessage message) {
        if (message == null || message.getId() == null || message.getMessage() == null) {
            return null;
        }
        WebSocketSession webSocketSession = sessions.get(message.getId());
        if (webSocketSession == null) {
            return null;
        }
        try {
            if (webSocketSession.isOpen()) {
                synchronized (webSocketSession) {
                    logger.info("给打印机【{}】发送消息：{}", message.getId(), message.getMessage());
                    webSocketSession.sendMessage(new TextMessage(message.getMessage()));
                }
                return "1";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "0";
    }

}
