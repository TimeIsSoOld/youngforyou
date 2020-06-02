package com.example.youngforyou.service.impl;

import com.example.youngforyou.commom.util.MathUtils;
import com.example.youngforyou.entity.model.IncomedetailTable;
import com.example.youngforyou.entity.service.IncomedetailTableService;
import com.example.youngforyou.service.AccountantService;
import com.github.pagehelper.util.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
@Transactional
public class AccountantServiceImpl implements AccountantService {

    @Autowired
    private IncomedetailTableService incomedetailTableService;


    @Override
    public String importData(MultipartFile file) {

        try {
            InputStream is = file.getInputStream();
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            Workbook wb=null;
            if ("xlsx".equals(suffix)){
                wb =new XSSFWorkbook(is);
            }else if ("xls".equals(suffix)){
                wb = new HSSFWorkbook(is);
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
               //int num =sheet.getLastRowNum();

                IncomedetailTable incomedetailTable=new IncomedetailTable();
                incomedetailTable.setPKey(UUID.randomUUID()+"");
//                UUID.randomUUID().toString().replaceAll("-", "")

                Cell c0=row.getCell(0);
                c0.setCellType(CellType.STRING);
                Object dddh=c0.getStringCellValue();
                if (StringUtil.isNotEmpty(dddh+"")){
                    incomedetailTable.setDddh(dddh+"");
                }

                Cell c1=row.getCell(1);
                c1.setCellType(CellType.STRING);
                Object Jzrq=row.getCell(1).getStringCellValue();
                if (StringUtil.isNotEmpty(Jzrq+"")){
                    incomedetailTable.setJzrq(Jzrq+"");
                }

                Cell c2=row.getCell(2);
                c2.setCellType(CellType.STRING);
                Object Lydh=row.getCell(2).getStringCellValue();
                if (StringUtil.isNotEmpty(Lydh+"")){
                    incomedetailTable.setLydh(Lydh+"");
                }

                Cell c3=row.getCell(3);
                c3.setCellType(CellType.STRING);
                Object Ywbm=row.getCell(3).getStringCellValue();
                if (StringUtil.isNotEmpty(Ywbm+"")){
                    incomedetailTable.setYwbm(Ywbm+"");
                }

                Cell c4=row.getCell(4);
                c4.setCellType(CellType.STRING);
                Object Ywry=row.getCell(4).getStringCellValue();
                if (StringUtil.isNotEmpty(Ywry+"")){
                    incomedetailTable.setYwry(Ywry+"");
                }

                Cell c5=row.getCell(5);
                c5.setCellType(CellType.STRING);
                Object Khqc=row.getCell(5).getStringCellValue();
                if (StringUtil.isNotEmpty(Khqc+"")){
                    incomedetailTable.setKhqc(Khqc+"");
                }

                Cell c6=row.getCell(6);
                c6.setCellType(CellType.STRING);
                Object Sphm=row.getCell(6).getStringCellValue();
                if (StringUtil.isNotEmpty(Sphm+"")){
                    incomedetailTable.setSphm(Sphm+"");
                }



                Cell c8=row.getCell(7);
                c8.setCellType(CellType.STRING);
                Object Ph=row.getCell(7).getStringCellValue();
                if (StringUtil.isNotEmpty(Ph+"")){
                    incomedetailTable.setPh(Ph+"");
                }

                Cell c7=row.getCell(8);
                c7.setCellType(CellType.STRING);
                Object Pm=row.getCell(8).getStringCellValue();
                if (StringUtil.isNotEmpty(Pm+"")){
                    incomedetailTable.setPm(Pm+"");
                }

                Cell c9=row.getCell(9);
                c9.setCellType(CellType.STRING);
                Object Gg=row.getCell(9).getStringCellValue();
                if (StringUtil.isNotEmpty(Gg+"")){
                    incomedetailTable.setGg(Gg+"");
                }

                Cell c10=row.getCell(10);
                c10.setCellType(CellType.STRING);
                Object Jjsl=row.getCell(10).getStringCellValue();
                if (StringUtil.isNotEmpty(Jjsl+"")){
                    incomedetailTable.setJjsl(Integer.parseInt(Jjsl+""));
                }

                Cell c11=row.getCell(11);
                c11.setCellType(CellType.STRING);
                Object Dj=row.getCell(11).getStringCellValue();
                if (StringUtil.isNotEmpty(Dj+"")){
                    incomedetailTable.setDj(MathUtils.objectConvertBigDecimal(Dj));
                }

                Cell c12=row.getCell(12);
                c12.setCellType(CellType.STRING);
                Object Wsje=row.getCell(12).getStringCellValue();
                if (StringUtil.isNotEmpty(Wsje+"")){
                    incomedetailTable.setWsje(MathUtils.objectConvertBigDecimal(Wsje));
                }

                Cell c13=row.getCell(13);
                c13.setCellType(CellType.STRING);
                Object Se=row.getCell(13).getStringCellValue();
                if (StringUtil.isNotEmpty(Se+"")){
                    incomedetailTable.setSe(MathUtils.objectConvertBigDecimal(Se));
                }

                Cell c14=row.getCell(14);
                c14.setCellType(CellType.STRING);
                Object Hj=row.getCell(14).getStringCellValue();
                if (StringUtil.isNotEmpty(Hj+"")){
                    incomedetailTable.setHj(MathUtils.objectConvertBigDecimal(Hj));
                }
                incomedetailTableService.save(incomedetailTable);
//               Thread.sleep(100);
            }

        }catch (Exception e){
            System.out.print(e.getMessage());
        }
        return  "1";

    }
}
