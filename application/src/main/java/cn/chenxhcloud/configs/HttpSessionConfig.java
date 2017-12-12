package cn.chenxhcloud.configs;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 
*   
* 项目名称：chenxh-app  
* 类名称：cn.chenxhcloud.configs.HttpSessionConfig  
* @author : chenxh  
* 创建时间：2017年12月12日 下午5:03:04
* 描述：配置Httpsession的Redis缓存设置(设置缓存时长为24H)
*
 */
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=86400)
public class HttpSessionConfig {
	
}
