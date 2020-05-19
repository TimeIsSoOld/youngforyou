package com.example.youngforyou.entity.service.impl;

import com.example.youngforyou.entity.model.BaseUser;
import com.example.youngforyou.entity.dao.BaseUserMapper;
import com.example.youngforyou.entity.service.BaseUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账号信息表 服务实现类
 * </p>
 *
 * @author Administrator
 * @since 2020-03-19
 */
@Service
public class BaseUserServiceImpl extends ServiceImpl<BaseUserMapper, BaseUser> implements BaseUserService {

}
