package com.itheima.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求路径
        String uri = req.getRequestURI();
        System.out.println(uri);
        //2、获取最后一段路径 得到方法名

        int index = uri.lastIndexOf("/"); //截断的路径为 /servlet 包含左括号
        String methodName = uri.substring(index + 1); //因此这里的index + 1

        System.out.println(methodName);

        //3、执行方法
        //3、1 获取字节码 Class 对象

        //this 谁调用我(this所在的方法)我（this）代表谁
        //System.out.println(this);
        Class<? extends BaseServlet> cls = this.getClass();

        //3、2获取方法对象 反射原理
        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //3、3执行方法
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
