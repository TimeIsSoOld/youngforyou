package com.example.youngforyou.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.youngforyou.commom.controller.BaseController;
import com.example.youngforyou.commom.result.Result;
import com.example.youngforyou.commom.util.WebUtil;


import com.example.youngforyou.dao.IndexDao;
import com.example.youngforyou.entity.model.MbTable;
import com.example.youngforyou.entity.service.MbTableService;
import com.example.youngforyou.service.AccountantService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/accountant")
public class AccountantMngController extends BaseController {

    @Autowired
    private AccountantService accountantService;

    @Autowired
    private MbTableService mbTableService;

    @Autowired
    private IndexDao indexDao;

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

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
        String[] header = {"期间","客户简称", "品号", "品名", "规格", "库存单位", "币种","销货计价净量","销货净额-本币","净税额-本币","销货总净额-本币","税票","业务人员"};

        //数据内容
//        String[] student1 = {"1", "小红", "女", "23", "成都青羊区", "96"};
//        String[] student2 = {"2", "小强", "男", "26", "成都金牛区", "91"};
//        String[] student3 = {"3", "小明", "男", "28", "成都武侯区", "90"};

        List<Map<String, Object>> maps = mbTableService.listMaps();
        List<Map<String,Object>> list=new ArrayList<>();
        for ( Map<String, Object> map: maps) {
            Map<String, Object> ttMap = new LinkedHashMap<String, Object>();
            List<Map<String, Object>> getsphm = indexDao.getsphm(map);
            ttMap.put("qj",map.get("qj"));
            ttMap.put("khqc",map.get("khqc"));
            ttMap.put("ph",map.get("ph"));
            ttMap.put("pm",map.get("pm"));
            ttMap.put("gg",map.get("gg"));
            ttMap.put("ccdw",map.get("ccdw"));
            ttMap.put("bz",map.get("bz"));
            ttMap.put("jl",map.get("jl"));
            ttMap.put("xhje",map.get("xhje"));
            ttMap.put("jse",map.get("jse"));
            ttMap.put("xhze",map.get("xhze"));

            if(getsphm!=null&&getsphm.size()>0){
                ttMap.put("sphm",getsphm.get(0).get("SPHM")+"");
                ttMap.put("wyry",getsphm.get(0).get("wyry")+"");
            }
            list.add(ttMap);
        }


//        Map<String,Object> map1 = new LinkedHashMap<>();
//        map1.put("id","1");
//        map1.put("name","小红");
//        map1.put("sex","女");
//        map1.put("age","23");
//        map1.put("adress","成都青羊区");
//        Map<String,Object> map2 = new LinkedHashMap<>();
//        map2.put("id","2");
//        map2.put("name","小强");
//        map2.put("sex","男");
//        map2.put("age","33");
//        map2.put("adress","成都武侯区");

//        list.add(map1);
//        list.add(map2);
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



    @GetMapping("/excelAddRow")
    public void excelAddRow() throws IOException {
        OutputStream out = null;
        try{
            File finalXlsxFile = new File("C:/Users/Administrator/Desktop/2020年1至4月销货统计表.xlsx");
            Workbook wb = getWorkbok(finalXlsxFile);


            Sheet sheet = wb.getSheetAt(0);
            //创建一个内容对象
            HSSFRichTextString text = new HSSFRichTextString("税票号码");

            Row row1 = sheet.getRow(0);
            row1.createCell(10);

            for (int r = 1; r <= sheet.getLastRowNum(); r++) {
                Row row = sheet.getRow(r);//通过sheet表单对象得到 行对象
                if (row == null){
                    continue;
                }

                Cell c0=row.getCell(0);
                c0.setCellType(CellType.STRING);
                Object dddh=c0.getStringCellValue();

                Cell c1=row.getCell(1);
                c1.setCellType(CellType.STRING);
                Object ph=c0.getStringCellValue();

                Cell c2=row.getCell(2);
                c2.setCellType(CellType.STRING);
                Object pm=c0.getStringCellValue();

                Cell c3=row.getCell(3);
                c3.setCellType(CellType.STRING);
                Object gg=c0.getStringCellValue();


            }



            out = new FileOutputStream("C:/Users/Administrator/Desktop/2020年1至4月销货统计表.xlsx");
            wb.write(out);

        }catch (Exception e){
            System.out.print(e.getMessage());
        }
    }

    public static Workbook getWorkbok(File file) throws IOException {
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if (file.getName().endsWith(EXCEL_XLS)) {     //Excel&nbsp;2003
            wb = new HSSFWorkbook(in);
        } else if (file.getName().endsWith(EXCEL_XLSX)) {    // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }


}
