package com.dongfang.javaweb.servlet.ch0_intro;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 一个servlet只能处理一个指定的请求
 * 应该指定servlet处理哪个请求
 * 指定的方法就是在web.xml中配置servlet的详细信息
 *
 * 在Deployment 的Application context 中配置相对路径
 *      tomcat的端口更改 server.xml Connector 中8080->31945
 *
 *      idea中的tomcat是镜像，和原来的tomcat脱离关系，是两个不同的tomcat
 *
 *
 * 编写servlet的三步
 *      1、创建自己的类FirstServlet，实现servlet接口
 *      2、实现service方法
 *      3、在web.xml中配置servlet信息
 *      测试：运行项目，在浏览器访问配置的url
 *
 *      静态资源：实际的文件 html css png
 *      动态资源：请求的动态资源是启动一段程序来处理
 */
public class FirstServlet implements Servlet {

    public FirstServlet() {
        super();
        System.out.println("被 创建");
    }
    // 初始化
    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    // 获取servlet的配置信息
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    // 服务，是用来处理来自客户端的请求
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("我是第一个Servlet");
        // ServletResponse res 可以给浏览器发送一个响应

        // 获取一个写数据的对象
        PrintWriter writer = res.getWriter();
        writer.write("hello world");

        System.out.println("this = " + this);
    }

    // 获取servlet信息
    @Override
    public String getServletInfo() {
        return null;
    }

    //
    @Override
    public void destroy() {

    }
}
