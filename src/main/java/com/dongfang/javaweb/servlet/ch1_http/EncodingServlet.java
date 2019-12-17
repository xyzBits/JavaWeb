package com.dongfang.javaweb.servlet.ch1_http;

import com.dongfang.utils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ibm.icu.lang.UScriptRun;

import javax.servlet.RequestDispatcher;
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

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        System.out.println("userName = " + userName);

        resp.setContentType("test/html;charset=utf-8");
        resp.getWriter().println("请求成功");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 浏览器将数据提交上来，但是服务器并不知道编码规则
        // 解决方法；让服务器知道编码规则，重新设置请求的编码格式
        // 如果是url中的参数，要在connector中设置 URIEncoding="UTF-8"
        // 浏览器会将地址栏进行编码，服务不知道编码规则，而且端口接收到请求后，已经按照默认的方式解码
        // 所以req中设置解码方式对url中的参数没有作用，就修改tomcat的配置文件
        req.setCharacterEncoding("utf-8");

        JsonNode reqNode = JsonUtils.parseInputToJson(req.getInputStream());
        JsonNode userName = reqNode.findValue("userName");
        System.out.println("userName = " + userName);
        JsonNode age = reqNode.findValue("age");
        System.out.println("age = " + age);
        System.out.println("reqNode = " + reqNode);

        resp.setContentType("application/json;charset-utf-8");
        PrintWriter out = resp.getWriter();
        ObjectNode respNode = JsonUtils.getMapper().createObjectNode();

        respNode.put("Mobile", 9988888);
        respNode.put("Name", "ManojSarnaik");
        out.print(respNode.toString());
    }
}
