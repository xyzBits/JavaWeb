package com.dongfang.javaweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Servlet的作用，运行在web server中的java小程序
 *      1、接收请求
 *      2、处理请求
 *      3、完成响应
 *      Servlet是服务端的一个组件，本质是服务端小程序，Servlet的实例对象由Servlet容器负责创建，
 *      Servlet的方法由容器在特定情况下调用，Servlet容器会在web应用卸载时销毁Servlet对象的实例
 *
 *      server letter
 */
@WebServlet(name = "Hello")
public class Hello extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应内容类型
        response.setContentType("text/html");

        //设置逻辑实现
        PrintWriter out = response.getWriter();
        out.println("Servlet");


//        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        String requestJson = objectWriter.writeValueAsString(request);
//        out.println();
//        out.println(requestJson);
//        System.out.println("requestJson = " + requestJson);
//
//        String responseJson = objectWriter.writeValueAsString(response);
//        out.println(responseJson);
//
//        System.out.println("responseJson = " + responseJson);

        String requestToString = httpServletRequestToString(request);

        //out.println();
        //out.println(requestToString);
        System.out.println("requestToString = " + requestToString);
    }


    private String httpServletRequestToString(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();

        sb.append("Request Method = [" + request.getMethod() + "], ");
        sb.append("Request URL Path = [" + request.getRequestURL() + "], ");

        String headers =
                Collections.list(request.getHeaderNames()).stream()
                        .map(headerName -> headerName + " : " + Collections.list(request.getHeaders(headerName)) )
                        .collect(Collectors.joining(", "));

        if (headers.isEmpty()) {
            sb.append("Request headers: NONE,");
        } else {
            sb.append("Request headers: ["+headers+"],");
        }

        String parameters =
                Collections.list(request.getParameterNames()).stream()
                        .map(p -> p + " : " + Arrays.asList( request.getParameterValues(p)) )
                        .collect(Collectors.joining(", "));

        if (parameters.isEmpty()) {
            sb.append("Request parameters: NONE.");
        } else {
            sb.append("Request parameters: [" + parameters + "].");
        }

        return sb.toString();
    }
}
