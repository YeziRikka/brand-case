package com.itheima.service.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceimpl implements BrandService {
    //Mybatis代码
    //1、创建sqlsessionfactory对象
    SqlSessionFactory factory =SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Brand> selecctAll() {
        //2、获取sqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3、获取BrandMapper
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //4、调用方法
        List<Brand> brands = brandMapper.selectAll();
        //释放资源
        sqlSession.close();
        return brands;
    }

    @Override
    public void add(Brand brand) {
        //2、获取sqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3、获取BrandMapper
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //4、调用方法
        brandMapper.add(brand);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        //2、获取sqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3、获取BrandMapper
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //4、调用方法
        brandMapper.deleteByIds(ids);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        //2、获取sqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3、获取BrandMapper
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        //4、计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //5、季孙查询条目数
        int size = pageSize;

        //6、查询当前页数据
        List<Brand> rows = brandMapper.selectByPage(begin, size);

        //7、查询总记录数
        int totalCount = brandMapper.selectTotalCount();

        //8、封装为PageBean对象
        PageBean<Brand> pageBean= new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //释放资源
        sqlSession.close();
        return pageBean;
    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {

        //2、获取sqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3、获取BrandMapper
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        //4、计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //5、季孙查询条目数
        int size = pageSize;

        //处理brand条件 模糊表达式
        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0 ){
            brand.setBrandName("%" + brandName + "%");
        }
        String companyName = brand.getCompanyName();
        if (companyName != null && companyName.length() > 0 ){
            brand.setCompanyName("%" + brandName + "%");
        }

        //6、查询当前页数据
        List<Brand> rows = brandMapper.selectByPageAndCondition(begin, size,brand);

        //7、查询总记录数
        int totalCount = brandMapper.selectTotalCountByCondition(brand);

        //8、封装为PageBean对象
        PageBean<Brand> pageBean= new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //释放资源
        sqlSession.close();
        return pageBean;
    }
}
