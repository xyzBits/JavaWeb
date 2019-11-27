package com.dongfang.javaweb.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


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
