package com.example.youngforyou.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.youngforyou.commom.controller.BaseController;
import com.example.youngforyou.commom.result.Result;
import com.example.youngforyou.commom.util.WebUtil;


import com.example.youngforyou.service.AccountantService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/accountant")
public class AccountantMngController extends BaseController {

    @Autowired
    private AccountantService accountantService;



    @ApiOperation(value = "Excel导入", notes = "Excel导入")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "上传的excel文件", required = true, paramType = "query", dataType = "String"),
    })
    @PostMapping("/importDate")
    @ResponseBody
    public Result importDate(@RequestParam("file") MultipartFile uploadFile , HttpServletResponse response) {
     String Date = accountantService.importData(uploadFile);
        return getSuccess(Date);
    }
}
