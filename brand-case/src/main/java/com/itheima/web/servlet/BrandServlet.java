package com.itheima.web.servlet;


import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{

    private BrandService brandService  = new BrandServiceimpl();

    /**
     * 查询所有
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("查询所有");

        //1、调用Service查询
        List<Brand> brands = brandService.selecctAll();
        //2、转为JSON
        String jsonString = JSON.toJSONString(brands);
        //3、存在中文数据，编码
        resp.setContentType("text/json;charset=utf-8");
        //写数据
        resp.getWriter().write(jsonString);

    }

    /**
     * 新增数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void  add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//        System.out.println("新增数据");
        //1、接收品牌数据
        BufferedReader bufferedReader = req.getReader();
        String params = bufferedReader.readLine();

        //转为JAVA对象
//        Brand brand = JSON.parseObject(params, Brand.class);
        Brand brand = JSON.parseObject(params, Brand.class);

        //2、调用service
        brandService.add(brand);

        //响应成功标识
        resp.getWriter().write("success");
    }

    /**
     * 批量删除
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void  deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//        System.out.println("新增数据");
        //1、接收品牌数据
        BufferedReader bufferedReader = req.getReader();
        String params = bufferedReader.readLine();

        //转为int数组
        int[] ids = JSON.parseObject(params, int[].class);

        //2、调用service
    brandService.deleteByIds(ids);

        //响应成功标识
        resp.getWriter().write("success");
    }

    /**
     * 分页查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、接受参数 当前页码和每页展示条数
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");
        //将接受到的字符串类型转为int
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //2、调用service查询
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

        //2、转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //3、存在中文数据，编码
        resp.setContentType("text/json;charset=utf-8");
        //写数据
        resp.getWriter().write(jsonString);

    }

    /**
     * 分页条件查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPageAndCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、接受参数 当前页码和每页展示条数
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");

        //将接受到的字符串类型转为int
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //获取查询条件对象
        //1、接收品牌数据
        BufferedReader bufferedReader = req.getReader();
        String params = bufferedReader.readLine();

        //转为Brand
        Brand brand = JSON.parseObject(params, Brand.class);


        //2、调用service查询
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage, pageSize,brand);

        //2、转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //3、存在中文数据，编码
        resp.setContentType("text/json;charset=utf-8");
        //写数据
        resp.getWriter().write(jsonString);

    }
}
