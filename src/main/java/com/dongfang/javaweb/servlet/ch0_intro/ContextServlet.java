package com.dongfang.javaweb.servlet.ch0_intro;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * 研究ServletContext对象
 */
public class ContextServlet implements Servlet {
    private ServletConfig config;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        // 获取ServletConfig对象
        ServletConfig servletConfig = this.getServletConfig();
        
        // 使用ServletConfig获取ServletContext
        ServletContext servletContext = servletConfig.getServletContext();

        // 研究ServletContext对象
        // 1、是什么，一个web应用对应一个ServletContext，代表整个web项目
        // 2、功能
        //   1）、可以获取web项目的配置信息，获取web项目的初始化参数
        String user = servletContext.getInitParameter("user");
        System.out.println("user = " + user);


        //    2）、获取web项目的路径
        String contextPath = servletContext.getContextPath();
        System.out.println("contextPath = " + contextPath);

        //    3）、获取虚拟资源的真实路径，方便文件下载
        //        虚拟路径：网络访问使用虚拟路径，每一个虚拟路径对应一个实际的资源
        //               静态资源（文件） 动态资源：只是启动一段程序代码
        //        真实路径：文件在磁盘中的存储路径
        //                getRealPath()传入虚拟路径，返回真实路径
        String realPath = servletContext.getRealPath("/pages/html/index.html");
        System.out.println("realPath = " + realPath);

        //     4）、作为最大的域对象，共享数据，4大域对象 application域对象

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
