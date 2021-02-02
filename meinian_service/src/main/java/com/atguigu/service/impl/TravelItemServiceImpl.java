package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.TravelItemDao;
import com.atguigu.entity.PageResult;
import com.atguigu.pojo.TravelItem;
import com.atguigu.service.TravelItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = TravelItemService.class)//发布服务，注册到zk中心
@Transactional//声明式事务，所有方法都增加事务
public class TravelItemServiceImpl implements TravelItemService {
    @Autowired
    TravelItemDao travelItemDao;
    //ctrl+i  调用接口中的方法
    @Override
    public void add(TravelItem travelItem) {
        travelItemDao.add(travelItem);
    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        //分页插件
        //开启分页功能
        //limit ？，？ 第一个问号表示开始的索引，第二的问号表示查询的条数
        //limit（currentPage-1）*pageSize，pageSize
        PageHelper.startPage(currentPage,pageSize);
        Page<TravelItem> page = travelItemDao.findPage(queryString);//返回当前页数据
        return new PageResult(page.getTotal(),page.getResult());//1总记录数 2.分页数据的集合
    }

    @Override
    public TravelItem findById(String id) {
        return travelItemDao.findById(id);
    }


}
