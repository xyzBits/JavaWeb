package com.dongfang.javaweb.cookie;

import com.sun.source.util.DocSourcePositions;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Cookie：服务器发送给浏览器要保存的数据，浏览器保存相关数据
 *      HTTP是无状态协议，
 *          服务器无法分辨每次的讲求来自于谁
 *          如果客户端来的时候能带上类似会员卡的东西，记录卡号
 *
 *      Cookie是服务器发送给浏览器的会员卡
 *      服务器让浏览器保存一份数据，以后访问的时候带上相应的数据
 *
 *
 *      1、cookie创建发送给浏览器，响应头多了：Set-Cookie:userName=lidongfang
 *          就是命令浏览器保存cookie
 *          浏览器默认保存这个字段，默认在一个会话期间只有访问这个项目，cookie都携带
 *          以后，只要是请求当前项目下的资源 ，都会携带
 *
 *      2、获取cookie
 *          request.getCookies()
 *      3、删除：cookie生命周期
 *              1）、默认cookie是在会话期间有效，浏览器一直不关
 *              2）、cookie可以修改默认的存活时间
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
        getCookie(req, resp);
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


    protected void deleteCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();

    }
}
