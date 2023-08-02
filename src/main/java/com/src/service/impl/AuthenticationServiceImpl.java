package com.src.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.src.model.Authentication;
import com.src.mapper.AuthenticationMapper;
import com.src.service.IAuthenticationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * api应用 服务实现类
 * </p>
 *
 * @author 陌君颜
 * @since 2023-08-01
 */
@Service
public class AuthenticationServiceImpl extends ServiceImpl<AuthenticationMapper, Authentication> implements IAuthenticationService {

    @Autowired
    private AuthenticationMapper authenticationMapper;
    @Override
    public Boolean check(String appId, String appSecret) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("app_id", appId);
        queryWrapper.eq("app_secret", appSecret);
        Integer count = authenticationMapper.selectCount(queryWrapper);

        return count > 0;
    }
}
