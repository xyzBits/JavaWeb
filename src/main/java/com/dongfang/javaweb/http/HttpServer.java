package com.dongfang.javaweb.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * request = GET / HTTP/1.1
 * Host: localhost:31943
 * Connection: keep-alive
 * Cache-Control: max-age=0
 * Upgrade-Insecure-Requests: 1
 * User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36
 * Sec-Fetch-User: ?1
 * Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,
        *Sec-Fetch-Site:cross-site
        *Sec-Fetch-Mode:navigate
        *Accept-Encoding:gzip,deflate,br
        *Accept-Language:en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7
        *Cookie:_ga=GA1.1.1172827387.1526876019

 */
public class HttpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(31943);
        while (true) {
            System.out.println("开始监听31943。。。。。。。");
            Socket client = server.accept();

            //接收数据
            InputStream inputStream = client.getInputStream();
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            String request = new String(buffer);
            System.out.println("request = " + request);


            OutputStream outputStream = client.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream);
            writer.println("hello");

            inputStream.close();
            writer.close();



        }
    }
}
