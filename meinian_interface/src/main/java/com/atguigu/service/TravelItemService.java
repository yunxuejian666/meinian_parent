package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.pojo.TravelItem;

public interface TravelItemService {//ctrl+alt+跳转到实现类
    void add (TravelItem travelItem);
    PageResult findPage(Integer currentPage,Integer pageSize,String queryString);

    TravelItem findById(String id);
}
