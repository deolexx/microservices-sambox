package com.deo.microservices.websockerclientservice;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Scanner;

public class StompClient {

//    private static String URL = "ws://dev-truckup-backend-lb-331162186.us-east-2.elb.amazonaws.com/api/";
    private static String URL = "ws://localhost:8886/api/auth/ws/info";

    public static void main(String[] args) {
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);

        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSessionHandler sessionHandler = new StompSessionHandler();
        stompClient.connect(URL, sessionHandler);

        new Scanner(System.in).nextLine(); // Don't close immediately.
    }
}
