package com.deo.microservices.simplehttpclientservice;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

@Service
public class HttpService {

    @SneakyThrows
    public void sendReceive() {
        Socket socket = new Socket("httpbin.org", 80);

        InputStream responce = socket.getInputStream();
        OutputStream request = socket.getOutputStream();

        byte[] data = ("GET / HTTP/1.1\n" +
                "Host: httpbin.org\n\n").getBytes();
        request.write(data);

        int c;
        while ((c = responce.read()) != -1) {

            System.out.print((char) c);
        }

        socket.close();
    }

}
