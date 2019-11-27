package com.dongfang.javaweb.http;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * HTTP/1.1 200
 * 服务器传回的数据的内容类型
 * Content-Type: text/html;charset=ISO-8859-1
 * 响应体中的数据长度
 * Content-Length: 20
 * Date: Wed, 27 Nov 2019 12:37:44 GMT
 *
 * 响应码
 * 200 请求成功，它告诉浏览器响应的结果
 * 404 请求的资源没找到，说明客户端请求了不存在的资源
 * 500 请求资源找到了，但是服务器内部出现了错误
 * 302 重定向，表示服务器要求浏览器重新再发一个请求，服务器会发送一个响应头location，指定了新的url地址
 *
 * 以2 开头的状态码，都表示的是成功，
 * 以3 开头的状态码，都表示的重新请求另外一个资源
 * 以4 开头的状态码，都表示的是资源未找到，客户端错误，资源异常，如果服务器启动失败，所有url都是404
 * 以5 开头的状态码，都表示服务器内部出错，代码错了，服务端错误
 *
 * Content-Type: 内容类型：表示服务器传回的数据的内容类型，就像文件扩展名一样
 * multipurpose internet mail extensions MIME
 * 超文本标记语言  .html text/html
 * 普通文本       text/text
 * 图片
 * 声音
 * 视频
 * 压缩文件
 */
public class HttpClient {
    public static void main(String[] args)  {
        try {
            Socket client = new Socket("localhost", 31945);
            // 自己将请求发送出去，服务器就会获取输出流来响应请求
            // 创建http请求
            // 1、 模拟HTTP请求头

            /**
             * GET /JavaWeb_war_exploded/Hello HTTP/1.1
             * Host: localhost:31945
             * Connection: keep-alive
             * Pragma: no-cache
             * Cache-Control: no-cache
             * Upgrade-Insecure-Requests: 1
             * User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36
             * Sec-Fetch-User: ?1
             * Accept: text/html
             * Sec-Fetch-Site: same-origin
             * Sec-Fetch-Mode: navigate
             * Referer: http://localhost:31945/JavaWeb_war_exploded/
             * Accept-Encoding: gzip, deflate, br
             * Accept-Language: en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7
             * Cookie: JSESSIONID=4CD9FB5F833603E9A2760BEFA6E61056; _ga=GA1.1.1172827387.1526876019; Idea-7ff554d4=9d9c0421-6c6c-4b5a-ae0a-2fc6092cc196; Webstorm-7a62b4d1=e9f747ff-bfbc-442d-a7e1-98e13611d4ca
             */

            // 2、发送请求
            OutputStream outputStream = client.getOutputStream();
            String sb = "GET /JavaWeb_war_exploded/Hello HTTP/1.1\r\n" +
                    "Host: localhost:31945\r\n" +
                    "Connection: keep-alive\n\n" +
                    "Pragma: no-cache\n\n" +
                    "Cache-Control: no-cache\n\n" +
                    "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36\n\n" +
                    "Accept: text/html\n\n" +
                    "Accept-Encoding: gzip, deflate, br\n\n" +
                    "Accept-Language: en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7\n\n";
            outputStream.write(sb.getBytes());

            Thread.sleep(2000); //睡两秒后接收响应

            // 3、接收响应
            InputStream inputStream = client.getInputStream();
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            System.out.println("size = " + size);
            int read = inputStream.read(buffer);


            String response = new String(buffer);

            System.out.println(response);
        } catch (Exception e) {

        }



    }
}
