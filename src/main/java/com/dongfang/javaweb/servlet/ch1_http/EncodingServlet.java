package com.dongfang.javaweb.servlet.ch1_http;

import javafx.concurrent.Worker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 方法一
 *          1）、设置内容类型setContentType
 *          2）、设置字符编码setCharacterEncoding
 * 方法二：
 *          直接设置响应头中的content-type字段
 *          resp.addHeader("content-type", "text/html;charset=utf-8");
 * 方法三：
 *          resp.setContentType("text/html;charset=utf-8");
 *
 *     字符乱码存在于以下地方
 *          1）、响应给浏览器的数据是乱码
 *              原因：是浏览器不知道数据的类型及编码格式
 *              解决，告诉浏览器内容类型及编码格式
 *              方法：3种
 *          2）、
 *
 */
public class EncodingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ???? 在第一次操作response 之前进行设置内容类型和字符编码
        resp.addHeader("content-type", "text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("请求成功");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // ???? 在第一次操作response 之前进行设置内容类型和字符编码
        // 数据的内容类型
        resp.setContentType("text/html");
        // 告诉浏览器编码
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("请求成功");

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ???? 在第一次操作response 之前进行设置内容类型和字符编码
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter writer = resp.getWriter();
        writer.println("请求成功");
    }
}
