package com.example.youngforyou.service;

import com.example.youngforyou.commom.base.BasePage;

import java.util.Map;

public interface IndexService {

    BasePage<Map<String, Object>> getUserList(BasePage<Map<String,Object>> page);
}
