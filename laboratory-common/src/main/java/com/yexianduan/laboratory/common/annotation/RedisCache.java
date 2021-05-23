package com.yexianduan.laboratory.common.annotation;



import java.lang.annotation.*;

/**
 * @author yolanda
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface RedisCache {

    /**
     * 缓存key
     * 若destine为true，存入redis的键为cacheKey的值
     * 若destine为false，存入redis的的键为service方法的 cacheKey:userId_id_packageName.className.methodName
     */
    String cacheKey() ;

    /**
     * 数据缓存时间单位s秒 默认5分钟
     */
    int cacheTime() default 300;

}
