package com.dongfang.javaweb.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * GET / HTTP/1.1
 * 请求方法 资源路径 协议版本
 *
 * 请求头信息：请求的头部信息
 * Host: localhost:31943
 * Connection: keep-alive 长连接
 * Cache-Control: max-age=0
 * Upgrade-Insecure-Requests: 1
 * 用户代理：用户发送请求时使用的工具的详细信息，用户客户端的详细信息
 * User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36
 * Sec-Fetch-User: ?1
 * 可以接收的内容类型
 * Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,
 * Sec-Fetch-Site:cross-site
 * Sec-Fetch-Mode:navigate
 * 接收的编码格式
 * Accept-Encoding:gzip,deflate,br
 * Accept-Language:en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7
 * Cookie:_ga=GA1.1.1172827387.1526876019
 *
 * 所有的请求头；https://en.wikipedia.org/wiki/List_of_HTTP_header_fields
 *
 * GET请求没有请求体
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
