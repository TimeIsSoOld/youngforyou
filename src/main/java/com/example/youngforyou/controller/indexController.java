package com.example.youngforyou.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.youngforyou.commom.controller.BaseController;
import com.example.youngforyou.commom.result.Result;
import com.example.youngforyou.entity.model.UmsAdmin;
import com.example.youngforyou.entity.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class indexController extends BaseController {

    @Autowired
    private UmsAdminService umsAdminService;

    @RequestMapping("/index")
    public Result sayHello(){

        IPage<UmsAdmin> page = umsAdminService.page(new Page<>(1, 10));
        return getSuccess(page);
    }

}
