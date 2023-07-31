package com.src.service.impl;

import cn.hutool.core.codec.Base64;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.src.config.exception.BusinessException;
import com.src.enums.ExceptionEnum;
import com.src.model.ShortLinks;
import com.src.mapper.ShortLinksMapper;
import com.src.service.IShortLinksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 短网址 服务实现类
 * </p>
 *
 * @author 陌君颜
 * @since 2023-07-25
 */
@Service
public class ShortLinksServiceImpl extends ServiceImpl<ShortLinksMapper, ShortLinks> implements IShortLinksService {

    @Autowired
    private ShortLinksMapper shortLinksMapper;
    @Value("${short-links.host}")
    private String host;


    @SneakyThrows
    @Override
    public String create(String link) {
        // 生成短码
        String md5Link = DigestUtils.md5DigestAsHex(link.getBytes());
        // 将哈希值进行Base64编码，得到22个或者43个Base64字符，再截取前面的6个字符，就得到短URL
        String shortLink = Base64.encode(md5Link).substring(0, 6);

        // 匹配库里是否存在短码 并且 长链接完全匹配 否则 return
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("short_link", shortLink);
        ShortLinks links =  shortLinksMapper.selectOne(queryWrapper);
        if (links != null) {
            if (link.equals(links.getOriginalLink())) {
                return host+shortLink;
            }
            throw new BusinessException("短码生成异常，请再试一次！", ExceptionEnum.NOT_FOUND.getResultCode());
        }

        ShortLinks shortLinks = new ShortLinks();
        shortLinks.setShortLink(shortLink);
        shortLinks.setOriginalLink(link);
        // 入库
        shortLinksMapper.insert(shortLinks);

        return host+shortLink;
    }

    @SneakyThrows
    @Override
    public void getLongLink(HttpServletResponse resp, String hasCode) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("short_link", hasCode);
        ShortLinks links =  shortLinksMapper.selectOne(queryWrapper);
        if (links == null) {
            throw new BusinessException("链接异常，请再试一次！", ExceptionEnum.NOT_FOUND.getResultCode());
        }
        resp.sendRedirect(links.getOriginalLink());
    }
}
