package com.aia.servlet;

import com.aia.utils.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 在服务器中的Servlet判断是否有一个名为lastTime的cookie
 1. 有：不是第一次访问
 1. 响应数据：欢迎回来，您上次访问时间为:2018年6月10日11:50:20
 2. 写回Cookie：lastTime=2018年6月10日11:50:01
 2. 没有：是第一次访问
 1. 响应数据：您好，欢迎您首次访问
 2. 写回Cookie：lastTime=2018年6月10日11:50:01

 */
@WebServlet("/cookieServlet")
public class CookieDemo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       response.setContentType("text/html;charset=utf-8");
        //获取Cookie
       Cookie[] cookies = request.getCookies();
        boolean falg = false;//没有cookie
       if (cookies != null && cookies.length > 0){
            for (Cookie cookie :cookies){
                String name = cookie.getName();
                if ("lastTime".equals(name)){
                    falg = true;
                    String str_date = TimeUtil.getTimeFommat();
                    str_date = URLEncoder.encode(str_date,"utf-8");
                    cookie.setValue(str_date);
                    cookie.setMaxAge( 60 * 60 * 24 * 30);//设置cookie保命时间
                    response.addCookie(cookie);

                    //响应数据
                    String value = cookie.getValue();
                    value = URLDecoder.decode(value,"utf-8");
                    response.getWriter().write("<h1>欢迎回来,您上次访问时间为:"+value+"</h1>");
                    break;
                }
            }

       }

        if(cookies == null || cookies.length == 0 || falg == false){
            //第一次访问
            String str_date = TimeUtil.getTimeFommat();
            str_date = URLEncoder.encode(str_date,"utf-8");

            Cookie cookie = new Cookie("lastTime",str_date);
            cookie.setMaxAge( 60 * 60 * 24 * 30 );
            response.addCookie(cookie);
            response.getWriter().write("<h1>您好,欢迎您首次访问</h1>");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
