package cn.chenxhcloud.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.chenxhcloud.configs.dbs.DataSourceContextHolder;
import cn.chenxhcloud.models.annotation.SelectDS;

/**
 * 
*   
* 项目名称：chenxh-app  
* 类名称：cn.chenxhcloud.aop.DynamicDataSourceAspect  
* @author : chenxh  
* 创建时间：2017年12月12日 下午5:02:12
* 描述：AOP拦截，进行动态设置DataSource
*
 */
@Aspect
@Order(-10)
@Component
public class DynamicDataSourceAspect {
	private static final Logger log = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

	@Before("@annotation(targetDataSource)")
	public void changeDataSource(JoinPoint point, SelectDS targetDataSource) throws Throwable {
		String dsId = targetDataSource.value();
		if (!DataSourceContextHolder.containsDataSource(dsId)) {
			if (log.isDebugEnabled()) {
				log.debug("Target datasource [{}] not exists,use defaultDataSource  <<<<<< {}", dsId, point.getSignature());
			}
		} else {
			if (log.isDebugEnabled()) {
				log.debug("Use datasource : [{}] <<<<<< {}", dsId, point.getSignature());
			}
			DataSourceContextHolder.setDbType(dsId);
		}
	}

	@After("@annotation(targetDataSource)")
	public void restoreDataSource(JoinPoint point, SelectDS targetDataSource) {
		String dsId = targetDataSource.value();
		if (!DataSourceContextHolder.containsDataSource(dsId)) {
			if (log.isDebugEnabled()) {
				log.debug("Revert DataSource : defaultDataSource <<< {}", point.getSignature());
			}
		} else {
			if (log.isDebugEnabled()) {
				log.debug("Revert DataSource : {} <<<<<< {}", dsId, point.getSignature());
			}
		}
		DataSourceContextHolder.clearDbType();
	}
}
