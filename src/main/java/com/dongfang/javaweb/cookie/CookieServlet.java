package com.dongfang.javaweb.cookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * Cookie：服务器发送给浏览器要保存的数据，浏览器保存相关数据
 * HTTP是无状态协议，
 * 服务器无法分辨每次的讲求来自于谁
 * 如果客户端来的时候能带上类似会员卡的东西，记录卡号
 *
 * Cookie是服务器发送给浏览器的会员卡
 * 服务器让浏览器保存一份数据，以后访问的时候带上相应的数据
 *
 * 1、cookie创建发送给浏览器，响应头多了：Set-Cookie:userName=lidongfang
 *      就是命令浏览器保存cookie
 *      浏览器默认保存这个字段，默认在一个会话期间只有访问这个项目，cookie都携带
 *      以后，只要是请求当前项目下的资源 ，都会携带
 *
 * 2、获取cookie
 *      request.getCookies()
 * 3、删除：cookie生命周期
 *      1）、默认cookie是在会话期间有效，浏览器一直不关
 *      2）、cookie可以修改默认的存活时间，修改maxAge为0就可以删除，设置为负数后，一般浏览器还是保存，破法期为会活期间
 *      3）、cookie.setMaxAge(60 * 60)一小时后删除
 * 5、设置cookie路径，告诉浏览器访问哪些资源会携带cookie
 *
 *
 *
 *
 *
 */

/**
 * 1、浏览器第一次访问服务器
 * 2、服务器发送cookie给浏览器，告诉浏览器要保存cookie
 * 3、浏览器收到响应头保存cookie的要求，就保存cookie，要求有时间和路径，保存在浏览器的进程中
 * 4、以后访问项目中的每个请求，都带上所要求的cookie
 * 5、浏览器按cookie的name识别是否为同一cookie，要以创建一个同名cookie，添加到响应中
 *      利用同名cookie，可以进行cookie修改操作
 */
public class CookieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        createCookie(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        createCookie(req, resp);
        getCookie(req, resp);
        deleteCookie(req, resp);
        setCookiePath(req, resp);
    }

    /**
     * 服务器创建一个cookie
     */
    private void createCookie(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie cookie = new Cookie("userName", "lidongfang");
        // 把cookie发给浏览器
        resp.addCookie(cookie);
        cookie = new Cookie("password", "12345");
        resp.addCookie(cookie);
        resp.getWriter().println("发给你了。。。。。");
    }

    private void getCookie(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 从请求头中获取所有的cookie
        Cookie[] cookies = req.getCookies();
        PrintWriter writer = resp.getWriter();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            writer.println("cookie的name " + name);
            writer.println("cookier的value " + value);
        }
    }


    /**
     * setMaxAge
     * 设置最大存活时间，
     * 正数，表示时间
     * 负数，表示发给浏览器，浏览器也不会存储
     * 0 表示删除cookie
     */
    protected void deleteCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if ("userName".equalsIgnoreCase(cookie.getName())) {
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        }
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> headerNames = req.getHeaderNames();
        ArrayList<String> headerList = Collections.list(headerNames);
        StringBuilder sb = new StringBuilder();
        headerList.remove("connection");
        for (String nextElement : headerList) {
            sb.append(nextElement).append(":").append(req.getHeader(nextElement)).append(System.getProperty("line.separator"));
        }

        System.out.println("sb = " + sb);
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> headers = Collections
                .list(req.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(h -> h, req::getHeader));
        System.out.println("headers = " + headers);
    }

    protected void setCookiePath(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("sex", "232423");
        // 告诉浏览器，访问哪些路径带上此cookie，精确的告诉访问哪些资源需要cookie，可以节省流量
        cookie.setPath("/cookieServlet");
        resp.addCookie(cookie);
    }
}
