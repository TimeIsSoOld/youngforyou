package com.example.youngforyou.service.impl;


import com.example.youngforyou.commom.base.BasePage;
import com.example.youngforyou.dao.IndexDao;
import com.example.youngforyou.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IndexDao indexDao;

    @Override
    public BasePage<Map<String, Object>> getUserList(BasePage<Map<String, Object>> page) {
        return indexDao.getUserList(page);
    }
}
