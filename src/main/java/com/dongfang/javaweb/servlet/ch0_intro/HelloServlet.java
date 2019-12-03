package com.dongfang.javaweb.servlet.ch0_intro;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet生命周期
 *      Servlet是跑在tomcat服务器上的
 *      Tomcat服务器------>Servlet容器
 *
 *      生命周期：从出生到死亡
 *      Servlet的生命周期：Servlet从创建到销毁的过程
 *
 *      当我们第一次访问HelloServlet对象时
 *          1）、创建一个Servlet对象
 *          2）、调用init方法  init()初始化Servlet
 *          3）、调用service方法   service()方法处理请求
 *      以后请求；
 *          4）、只调用service方法来处理请求，整个运行期间，只创建了一个Servlet对象
 *          servlet.service() servlet是单实例，多线程运行
 *      当项目从服务器上卸载：
 *          4）、服务会调用destroy方法销毁Servlet对象，清理善后
 *
 */
public class HelloServlet implements Servlet {
    public HelloServlet() {
        System.out.println("我是HelloServlet的构造器");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.err.println("我是init方法...");
    }

    @Override
    public ServletConfig getServletConfig() {
        System.err.println("我是getServletConfig方法...");
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.err.println("我是service方法......");
        // 利用ServletResponse 获取 一个向浏览器输出数据的输出流
        PrintWriter writer = res.getWriter();
        writer.write("HelloWorld");

    }

    @Override
    public String getServletInfo() {
        System.err.println("我是getServletInfo方法...");
        return null;
    }

    @Override
    public void destroy() {
        System.err.println("我是destroy方法...");
    }
}
