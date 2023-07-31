package com.src.filter;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.src.mapper.ShortLinksMapper;
import com.src.model.ShortLinks;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class RedirectFilter  implements Filter {
    @Autowired
    private ShortLinksMapper shortLinksMapper;

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter start");
        if (servletRequest instanceof HttpServletRequest) {

            String url = ((HttpServletRequest)servletRequest).getRequestURL().toString();

            String queryString = ((HttpServletRequest)servletRequest).getQueryString();
            log.info("url:{} queryString:{}", url, queryString);

            String[] str = url.split("/");
            log.info("len:{}", str.length);
            log.info("len:{}", str[str.length-1]);
            // 处理短链接进来的数据
            if (str.length == 4 && queryString == null) {
                String shortCode = str[str.length-1];
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("short_link", shortCode);
                ShortLinks links =  shortLinksMapper.selectOne(queryWrapper);
                if (links == null) {
                    throw new Exception("链接异常，请再试一次！");
                }
                log.info("重定向 url:{}", links.getOriginalLink());
                HttpServletResponse resp = (HttpServletResponse) servletResponse;
                resp.sendRedirect(links.getOriginalLink());
                log.info("重定向 end");
            } else {
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }


    }
}
