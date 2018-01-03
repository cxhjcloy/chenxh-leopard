package cn.chenxhcloud.aop;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import cn.chenxhcloud.nosql.mongo.entity.LogEntity;

/**
 * 
*   
* 项目名称：chenxh-app  
* 类名称：cn.chenxhcloud.aop.AuthInterceptor  
* @author：chenxh  
* 创建时间：2018年1月3日 下午5:07:45
* 描述：
*
 */
@Aspect
@Component
public class AuthInterceptor {

	private final String SESSION_UID ="SESSION_UID";
	private static final Logger log = LoggerFactory.getLogger(AuthInterceptor.class);
	@Autowired
	private MongoTemplate mongoTemplate;

	@Around("@annotation(auth)")
	public Object auth(final ProceedingJoinPoint pjp, Auth auth) throws Throwable {
		if (log.isInfoEnabled()) {
			log.info("AuthInterceptor.auth invoke.");
		}
		if (!auth.enable()) {
			if (log.isInfoEnabled()) {
				log.info("AuthInterceptor.auth disable.");
			}
			return pjp.proceed();
		}
		if (auth.isLogin()) {
			HttpSession session = null;
			boolean searchFlag = false;
			try {
				Object[] arguments = pjp.getArgs();
				for (Object object : arguments) {
					if (object instanceof HttpServletRequest) {
						HttpServletRequest request = (HttpServletRequest) object;
						session = request.getSession();
						searchFlag = true;
					} else if (object instanceof HttpSession) {
						session = (HttpSession) object;
						searchFlag = true;
					}
					if (searchFlag) {
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("error:{}", e.getMessage());
			}
			if (!searchFlag) {
				Map<String, Object> retData = new HashMap<String, Object>(16);
				retData.put("code", 4001);
				retData.put("message", "获取不到session数据(controller的action方法的缺失HttpServletRequest或HttpSession参数)");
				throw new RuntimeException(JSON.toJSONString(retData));
			}
			if (session != null) {
				if (session.getAttribute(SESSION_UID) == null) {
					Map<String, Object> retData = new HashMap<String, Object>(16);
					retData.put("code", 4002);
					retData.put("message", "session失效 请重新登陆叻");
					throw new RuntimeException(JSON.toJSONString(retData));
				}
			}
		}
		if (auth.isPriv()) {
			Map<String, Object> retData = new HashMap<String, Object>(16);
			retData.put("code", 4003);
			retData.put("message", "无该操作权限");
			throw new RuntimeException(JSON.toJSONString(retData));
		}
		Object retData = pjp.proceed();
		if (auth.isLog()) {
			LogEntity logEntity = new LogEntity();
			logEntity.setTimeStamp(System.currentTimeMillis());
			logEntity.setParams(pjp.getArgs().toString());
			logEntity.setResult(JSON.toJSONString(retData));
			logEntity.setRequest(pjp.toLongString());
			mongoTemplate.save(logEntity);
		}
		return retData;
	}
}
