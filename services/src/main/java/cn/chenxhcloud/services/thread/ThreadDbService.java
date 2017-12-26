package cn.chenxhcloud.services.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.chenxhcloud.models.thread.ThreadInfo;
import cn.chenxhcloud.services.thread.mappers.ThreadDbDao;



/**
 * 
*   
* 项目名称：chenxh-services  
* 类名称：cn.chenxhcloud.services.thread.ThreadDbService  
* @author：chenxh  
* 创建时间：2017年12月26日 下午6:12:11
* 描述：
*
 */
@Service
public class ThreadDbService {
	
	private static final Logger log = LoggerFactory.getLogger(ThreadDbService.class);
	
	@Autowired
    private ThreadDbDao threadDbDao;
	public  int  save(ThreadInfo threadInfo){
		log.debug("--");
		return threadDbDao.save(threadInfo);
	}
}
