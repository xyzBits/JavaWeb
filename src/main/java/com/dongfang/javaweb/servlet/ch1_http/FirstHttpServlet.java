package com.dongfang.javaweb.servlet.ch1_http;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

/**
 * 调用流程分析
 *      FirstHttpServlet被实例化时，有继承在HttpServlet的service(ServletRequest, ServletResponse)方法
 *      GenericServlet中的为抽象方法，所以调HttpServlet中的方法，然后再调用HttpServlet自己实现的service()方法，
 *      此service方法中，根据请求，再调用真正的doGet方法
 */

/**
 * 转发与重定向的区别
 *                      转发                                      重定向
 * 浏览器地址栏           不会变化                                会变化
 * Request              同一个请求                              两次请求
 * APi                  request对象                            Response对象
 * 位置                  服务器内部完成                           浏览器完成
 * WEB-INF              可以访问                                不能访问
 * 共享请求域数据          可以共享                                不可以共享
 * 目标资源               必须是当前web应用中的资源                  不局限于当前web应用
 */
public class FirstHttpServlet extends HttpServlet {
    public FirstHttpServlet() {
        System.out.println("我是构造器");
    }
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    // 每次请求都调用doGet方法
    // 用来处理GET请求
    // post目前只有一种，表单提交的时候指定method=post
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet...");
        doPut(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost...");
        // HttpServletRequest request 代表的是封装浏览器请求信息对象，收到的浏览器的请求
        // HttpServletResponse response 代表就是要发送给浏览器的响应对象，封装我们的响应信息
        // 1、可以给浏览器响应字符串
        resp.getWriter().println("Hello HttpServlet....");

        // 2、可以重定向到一个页面或者其他资源，重定向就是服务器告诉浏览器重新请求别的资源
        // Location: /pages/html/success.html
        // 已经对之前 的request响应完了
        // 重定向请求了两次，第一次的响应为302
        resp.sendRedirect("/pages/html/success.html");
        // 动态资源的虚拟路径就是web.xml中配置的url-pattern，告诉浏览器，重新请求另一个资源
        System.out.println("req.getClass() = " + req.getClass());

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPut...");
        // HttpServletRequest req 代表浏览器发送给服务器的请求信息
        // http请求 请求首行  空行  请求体(封装的请求数据)
        // get将所有的参数放在url后
        // 1、获取请求数据，在url后面，或者请求体中
        String username = req.getParameter("username");
        System.out.println("username = " + username);
        String name = req.getParameter("name");
        System.out.println("name = " + name);

        System.out.println("url中的多个参数");
        String[] favors = req.getParameterValues("favor");
        System.out.println(Arrays.toString(favors));

        // 2、获取请求头信息
        String userAgent = req.getHeader("User-Agent");
        System.out.println("userAgent = " + userAgent);
        
        // 3、转发一个页面，资源
        // 先获取一个请求转发器
        RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/html/success.html");
        // 将请求转发出去，只发一次请求
        dispatcher.forward(req, resp);
        // 转发的资源是静态或者页面，服务器会直接给浏览器返回这个资源
        // 交给servlet的时候，可以继续处理，直到最后一个servlet完成响应

        // 4、作为域对象 ServletContext


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doDelete...");
    }

    /**
     * 我们重写后，将会调用service，不会调用post
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
//    @Override
//    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
//        System.out.println("super.service(req, res);");
//        HttpServletRequest request = (HttpServletRequest) req;
//        System.out.println(request.getMethod());
//        PrintWriter writer = res.getWriter();
//        writer.println(request.getMethod());
//    }
}
