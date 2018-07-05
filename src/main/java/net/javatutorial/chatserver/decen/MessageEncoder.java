/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.javatutorial.chatserver.decen;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import net.javatutorial.chatserver.pojos.ChatMessage;

/**
 *
 * @author makel
 */
    public class MessageEncoder implements Encoder.Text<ChatMessage> {

        @Override
        public void init(EndpointConfig config) {
        }

        @Override
        public String encode(ChatMessage message) throws EncodeException {
            return Json.createObjectBuilder()
                    .add("username", message.getUsername())
                    .add("message", message.getMessage()).build().toString();
        }

        @Override
        public void destroy() {
        }
    }
