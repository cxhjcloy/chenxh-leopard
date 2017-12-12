package cn.chenxhcloud.models.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;

/**
 * 
*   
* 项目名称：chenxh-models  
* 类名称：cn.chenxhcloud.models.annotation.SelectDS  
* @author : chenxh  
* 创建时间：2017年12月12日 下午5:12:49
* 描述：注解方式切换数据源，默认为test数据源
*
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SelectDS {
    String value() default "test";
}
