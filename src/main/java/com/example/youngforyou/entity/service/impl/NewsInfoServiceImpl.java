package com.example.youngforyou.entity.service.impl;

import com.example.youngforyou.entity.model.NewsInfo;
import com.example.youngforyou.entity.dao.NewsInfoMapper;
import com.example.youngforyou.entity.service.NewsInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author Administrator
 * @since 2020-03-19
 */
@Service
public class NewsInfoServiceImpl extends ServiceImpl<NewsInfoMapper, NewsInfo> implements NewsInfoService {

}
