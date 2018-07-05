/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.javatutorial.chatserver.sockets;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import net.javatutorial.chatserver.pojos.ChatMessage;
import net.javatutorial.chatserver.decen.MessageDecoder;
import net.javatutorial.chatserver.decen.MessageEncoder;

@ServerEndpoint(value = "/chat", encoders = {MessageEncoder.class}, decoders = {MessageDecoder.class})
public class ChatServerEndpoint {

    private static final Set<Session> sessions = Collections
            .synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    @OnMessage
    public void onMessage(ChatMessage message, Session client)
            throws IOException, EncodeException {
        for (Session session : sessions) {
            session.getBasicRemote().sendObject(message);
        }
    }
}
