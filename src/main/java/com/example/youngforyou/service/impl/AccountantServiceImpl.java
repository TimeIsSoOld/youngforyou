package com.example.youngforyou.service.impl;

import com.example.youngforyou.service.AccountantService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
@Transactional
public class AccountantServiceImpl implements AccountantService {

    @Override
    public String importData(MultipartFile file) {

        try {
            InputStream is = file.getInputStream();
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            Workbook wb=null;
            if ("xlsx".equals(suffix)){
                wb =new XSSFWorkbook(is);
            }else if ("xls".equals(suffix)){
                wb = WorkbookFactory.create(is);
            }

            Sheet sheet = wb.getSheetAt(0);
            if(sheet==null){
                return  "2";
            }
            for (int r = 1; r <= sheet.getLastRowNum(); r++) {
                //r = 2 表示从第三行开始循环 如果你的第三行开始是数据
                Row row = sheet.getRow(r);//通过sheet表单对象得到 行对象
                if (row == null){
                    continue;
                }
               int num =sheet.getLastRowNum();

                Object username = row.getCell(0).getStringCellValue();
                System.out.println(username);
            }

        }catch (Exception e){
            System.out.print(e.getMessage());
        }
        return  "1";

    }
}
