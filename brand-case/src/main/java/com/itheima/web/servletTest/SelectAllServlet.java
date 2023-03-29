package com.itheima.web.servletTest;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceimpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

//@WebServlet("/selectAllServlet")
public class SelectAllServlet extends HttpServlet {
    //多态 进行实现类切换 解除耦合性，
    private  BrandService brandService  = new BrandServiceimpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、调用Service查询
        List<Brand> brands = brandService.selecctAll();
        //2、转为JSON
        String jsonString = JSON.toJSONString(brands);
        //3、存在中文数据，编码
        response.setContentType("text/json;charset=utf-8");
        //写数据
        response.getWriter().write(jsonString);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
