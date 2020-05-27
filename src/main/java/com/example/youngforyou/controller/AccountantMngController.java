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
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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


    /**
     * Excel表格导出接口
     * http://localhost:8080/ExcelDownload
     * @param response response对象
     * @throws IOException 抛IO异常
     */
    @RequestMapping("/ExcelDownload")
    public void excelDownload(HttpServletResponse response) throws IOException {
        //表头数据
        String[] header = {"ID", "姓名", "性别", "年龄", "地址", "分数"};

        //数据内容
//        String[] student1 = {"1", "小红", "女", "23", "成都青羊区", "96"};
//        String[] student2 = {"2", "小强", "男", "26", "成都金牛区", "91"};
//        String[] student3 = {"3", "小明", "男", "28", "成都武侯区", "90"};
        Map<String,Object> map1 = new LinkedHashMap<>();
        map1.put("id","1");
        map1.put("name","小红");
        map1.put("sex","女");
        map1.put("age","23");
        map1.put("adress","成都青羊区");
        Map<String,Object> map2 = new LinkedHashMap<>();
        map2.put("id","2");
        map2.put("name","小强");
        map2.put("sex","男");
        map2.put("age","33");
        map2.put("adress","成都武侯区");
        List<Map<String,Object>> list=new ArrayList<>();
        list.add(map1);
        list.add(map2);
        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        //生成一个表格，设置表格名称为"学生表"
        HSSFSheet sheet = workbook.createSheet("学生表");

        //设置表格列宽度为10个字节
        sheet.setDefaultColumnWidth(10);

        //创建第一行表头
        HSSFRow headrow = sheet.createRow(0);

        //遍历添加表头(下面模拟遍历学生，也是同样的操作过程)
        for (int i = 0; i < header.length; i++) {
            //创建一个单元格
            HSSFCell cell = headrow.createCell(i);

            //创建一个内容对象
            HSSFRichTextString text = new HSSFRichTextString(header[i]);

            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);
        }


        for (int i = 1; i <list.size()+1 ; i++) {
            HSSFRow row = sheet.createRow(i);
            Map<String,Object> map=list.get(i-1);
            int num =0;
            for (String key: map.keySet()) {
                HSSFCell cell = row.createCell(num);
                HSSFRichTextString text = new HSSFRichTextString(map.get(key)+"");
                cell.setCellValue(text);
                num++;
            }

        }
        //模拟遍历结果集，把内容加入表格
        //模拟遍历第一个学生
//        HSSFRow row1 = sheet.createRow(1);
//        for (int i = 0; i < student1.length; i++) {
//            HSSFCell cell = row1.createCell(i);
//            HSSFRichTextString text = new HSSFRichTextString(student1[i]);
//            cell.setCellValue(text);
//        }



        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");

        //这后面可以设置导出Excel的名称，此例中名为student.xls
        response.setHeader("Content-disposition", "attachment;filename=student.xls");

        //刷新缓冲
        response.flushBuffer();

        //workbook将Excel写入到response的输出流中，供页面下载
        workbook.write(response.getOutputStream());
    }

}
