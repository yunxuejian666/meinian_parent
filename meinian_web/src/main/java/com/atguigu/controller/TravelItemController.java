package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.TravelItem;
import com.atguigu.service.TravelItemService;
import org.springframework.web.bind.annotation.*;

@RestController   //组合注解@Controller+@ResponseBody
@RequestMapping("/travelItem")
public class TravelItemController {
    @Reference//远程调用服务
    TravelItemService travelItemService;


    @RequestMapping("/findById")
    public Result findById(@RequestParam String id){
        try {
            TravelItem travelItem = travelItemService.findById(id);
            return new Result(true,MessageConstant.ADD_TRAVELITEM_SUCCESS,travelItem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_TRAVELITEM_FAIL);
        }
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = travelItemService.findPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize(), queryPageBean.getQueryString());
        return pageResult;
    }

    //表单项参数名必须和实体对象的属性名称一致，提供对应的set方法，框架创建对象并封装数据
    @RequestMapping("/add")
    public Result add(@RequestBody TravelItem travelItem){//从请求体获取数据
        try {
            travelItemService.add(travelItem);
            return new Result(true, MessageConstant.ADD_TRAVELITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_TRAVELITEM_FAIL);
        }
    }
}
