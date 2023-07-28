package com.src.config.mybatis;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.ExecutorType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置分页插件
 *
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


    // 当前环境
    @Value("${spring.profiles.active}")
    private String profile;

    /**
     * 自定义MyBatis的配置规则；给容器中添加一个ConfigurationCustomizer；
     *
     * @return
     */
    @Bean
    public ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return configuration -> {
            configuration.setCacheEnabled(Boolean.TRUE);
            configuration.setLazyLoadingEnabled(Boolean.TRUE);
            configuration.setAggressiveLazyLoading(Boolean.TRUE);
            configuration.setUseGeneratedKeys(Boolean.TRUE);
            configuration.setDefaultExecutorType(ExecutorType.SIMPLE);
            configuration.setMapUnderscoreToCamelCase(Boolean.TRUE);// 开启驼峰映射
            if ("test".equals(profile)) { // 非生产环境打印SQL语句
                configuration.setLogImpl(StdOutImpl.class);
            }
        };
    }

}

