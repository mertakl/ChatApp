/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.javatutorial.chatserver.decen;

import java.io.StringReader;
import java.util.Collections;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import net.javatutorial.chatserver.pojos.ChatMessage;

/**
 *
 * @author makel
 */
    public class MessageDecoder implements Decoder.Text<ChatMessage> {

        private JsonReaderFactory factory = Json
                .createReaderFactory(Collections.<String, Object>emptyMap());

        @Override
        public void init(EndpointConfig config) {
        }

        @Override
        public ChatMessage decode(String str) throws DecodeException {
            ChatMessage message = new ChatMessage();
            JsonReader reader = factory.createReader(new StringReader(str));
            JsonObject json = reader.readObject();
            message.setUsername(json.getString("username"));
            message.setMessage(json.getString("message"));
            return message;
        }

        @Override
        public boolean willDecode(String str) {
            return true;
        }

        @Override
        public void destroy() {
        }
    }
