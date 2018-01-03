package cn.chenxhcloud.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
*   
* 项目名称：chenxh-app  
* 类名称：cn.chenxhcloud.aop.Auth  
* @author：chenxh  
* 创建时间：2018年1月3日 下午5:08:56
* 描述：
*
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Auth {
	/**
	 * 是否需要拦截 总开关
	 * @return
	 */
	boolean enable() default true;
	/**
	 * 是否需要登录
	 * @return
	 */
	boolean isLogin() default false;
	/**
	 * 是否需要权限
	 * @return
	 */
	boolean isPriv() default false;
	/**
	 * 是否需要记录日志
	 * @return
	 */
	boolean isLog() default false;
}
