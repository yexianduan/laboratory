/**
 * @file: CorsConfig.java
 * @author: yolanda
 * @date: 2021/3/19 12:46
 */

package com.yexianduan.laboratory.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * @author yolanda
 * @version 1.0
 * @className CorsConfig
 * @date 2021/3/19  12:46
 * @see
 * @since
 */
@Configuration
public class CorsConfig {
    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedMethod("*"); // 允许任何方法（post、get等）
        config.addAllowedOrigin("*"); // 允许任何域名使用
        config.addAllowedHeader("*"); // 允许任何头
        config.setAllowCredentials(true); //允许接受cookie

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}
