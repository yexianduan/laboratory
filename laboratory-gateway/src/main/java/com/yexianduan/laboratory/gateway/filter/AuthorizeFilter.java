/**
 * @file: AuthorizeFilter.java
 * @author: yolanda
 * @date: 2021/5/21 16:32
 */

package com.yexianduan.laboratory.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

/**
 * @author yolanda
 * @version 1.0
 * @className AuthorizeFilter
 * @date 2021/5/21  16:32
 * @see
 * @since
 */
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        //2.判断当前的请求是否为登录，如果是，直接放行
        if(request.getURI().getPath().contains("/auth/login")){
            //放行
            return chain.filter(exchange);
        }
        HttpHeaders headers = request.getHeaders();
        String token=headers.getFirst("token");


        Boolean flag = redisTemplate.hasKey(token);

        if(!flag) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }else{
            redisTemplate.expire(token,10, TimeUnit.MINUTES);
        }

        return  chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
