package com.src.service;

import com.src.model.ShortLinks;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 短网址 服务类
 * </p>
 *
 * @author 陌君颜
 * @since 2023-07-25
 */
public interface IShortLinksService extends IService<ShortLinks> {
    String create(String link);
    void getLongLink(HttpServletResponse request, String hasCode);
}
