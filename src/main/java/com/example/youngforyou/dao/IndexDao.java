package com.example.youngforyou.dao;

import com.example.youngforyou.commom.base.BasePage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface IndexDao {
    BasePage<Map<String, Object>> getUserList(@Param("page") BasePage<Map<String,Object>> page);

    List<Map<String, Object>> getsphm(@Param("p")Map<String,Object> page);
}
