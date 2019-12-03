package com.dongfang.javaweb.servlet.ch0_intro;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class ConfigServlet implements Servlet {
    private ServletConfig config;

    /**
     * Servlet的初始化方法，在Servlet第一次被创建的时候调用
     * 只调用一次
     *
     * 一个Servlet对应一个ServletConfig
     * 一个web项目对应一个ServletContext，一个ServletContext中有多个ServletConfig
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        // ServletConfig
        // 1、是封装了Servlet的配置信息的对象，一个Servlet对应一个ServletConfig
        // 封装的是当前Servlet的配置信息
        // 2、功能
        /*
        *       1）、getServletName() 获取Servlet别名
        *       2）、getInitParameter() 获取Servlet初始化参数
        *       3）、getServletContext() 获取ServletContext对象，代表Servlet的上下文，代表当前的web项目信息
        * */
        this.config = config;
        String servletName = config.getServletName();
        System.out.println("servletName = " + servletName);
        String username = config.getInitParameter("dongfang");
        System.out.println("初始化参数 username = " + username);

        ServletContext servletContext = config.getServletContext();
        System.out.println("servletContext = " + servletContext);


    }

    /**
     * 获取Servlet的配置信息
     * @return
     */
    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    /**
     * 处理请求
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        // 1）、getServletName() 获取Servlet别名
        String servletName = config.getServletName();
        System.out.println("servletName = " + servletName);

        // 2）、getInitParameter() 获取Servlet初始化参数
        String username = config.getInitParameter("username");
        System.out.println("username = " + username);
        // 打印多个初始化参数的名字
        printServletParameters();

        // 3）、getServletContext() 获取ServletContext对象，代表Servlet的上下文，代表当前的web项目信息
        ServletContext servletContext = config.getServletContext();
        System.out.println("servletContext = " + servletContext);
    }

    private void printServletParameters() {
        Enumeration<String> initParameterNames = config.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String parameterName = initParameterNames.nextElement();
            System.out.println("parameterName = " + parameterName);
            String parameterValue = config.getInitParameter(parameterName);
            System.out.println("parameterValue = " + parameterValue);
        }

    }

    /**
     * 返回Servlet的描述信息
     * @return
     */
    @Override
    public String getServletInfo() {
        return this.getClass().getName();
    }

    /**
     * 销毁Servlet对象，由Tomcat销毁
     * tomcat停止或者卸载项目的时候销毁
     */
    @Override
    public void destroy() {

    }
}
