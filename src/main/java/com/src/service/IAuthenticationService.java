package com.src.service;

import com.src.model.Authentication;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * api应用 服务类
 * </p>
 *
 * @author 陌君颜
 * @since 2023-08-01
 */
public interface IAuthenticationService extends IService<Authentication> {

    Boolean check(String appId, String appSecret);
}
