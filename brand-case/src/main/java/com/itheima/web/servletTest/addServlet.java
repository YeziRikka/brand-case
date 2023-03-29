package com.itheima.web.servletTest;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

//@WebServlet("/addServlet")
public class addServlet extends HttpServlet {
    //多态 进行实现类切换 解除耦合性，
    private  BrandService brandService  = new BrandServiceimpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、接收品牌数据
        BufferedReader bufferedReader = request.getReader();
        String params = bufferedReader.readLine();

        //转为JAVA对象
//        Brand brand = JSON.parseObject(params, Brand.class);
        Brand brand = JSON.parseObject(params, Brand.class);

        //2、调用service
        brandService.add(brand);

        //响应成功标识
        response.getWriter().write("success");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
