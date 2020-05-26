package com.example.youngforyou.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.youngforyou.commom.base.BasePage;
import com.example.youngforyou.commom.controller.BaseController;
import com.example.youngforyou.commom.result.Result;
import com.example.youngforyou.entity.model.UmsAdmin;
import com.example.youngforyou.entity.service.UmsAdminService;
import com.example.youngforyou.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/test")
public class indexController extends BaseController {

    @Autowired
    private UmsAdminService umsAdminService;

    @Autowired
    private IndexService indexService;

    private static final Logger LOG = LoggerFactory.getLogger(indexController.class);

    @GetMapping("/index")
    public Result sayHello(){

        IPage<UmsAdmin> page = umsAdminService.page(new Page<>(1, 5));
        return getSuccess(page);
    }

    @GetMapping("/testThreeLeavl")
    public Result testThreeLeavl(){
        BasePage<Map<String, Object>> page = new BasePage<>(1,5);
        BasePage<Map<String, Object>>list=indexService.getUserList(page);
        return getSuccess(list);
    }



}
