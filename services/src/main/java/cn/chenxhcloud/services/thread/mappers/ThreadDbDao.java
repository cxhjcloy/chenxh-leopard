package cn.chenxhcloud.services.thread.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import cn.chenxhcloud.models.thread.ThreadInfo;

/**
 * 
*   
* 项目名称：chenxh-services  
* 类名称：cn.chenxhcloud.services.thread.mappers.ThreadDbDao  
* @author：chenxh  
* 创建时间：2017年12月26日 下午6:12:06
* 描述：
*
 */
@Mapper
@Repository
public class ThreadDbDao {

	public int save(ThreadInfo threadInfo) {
		return 0;
	}

}
