package com.src.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.src.service.IShortLinksService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 短网址 前端控制器
 * </p>
 *
 * @author 陌君颜
 * @since 2023-07-25
 */
@RestController
public class ShortLinksController {
    @Autowired
    private IShortLinksService shortLinksService;

    @ApiModelProperty(value = "生成短网址")
    @GetMapping("/create")
    public R<String> shortLink(@NotNull(message = "链接不能为空") @RequestParam("link") String link) {
        return R.ok(shortLinksService.create(link));
    }

    @ApiModelProperty(value = "重定向跳转")
    @GetMapping("/s/{hasCode}")
    public R shortLinkLocation(HttpServletResponse req, @PathVariable("hasCode") String hasCode) {
        shortLinksService.getLongLink(req,hasCode);
        return R.ok(1);
    }




}
