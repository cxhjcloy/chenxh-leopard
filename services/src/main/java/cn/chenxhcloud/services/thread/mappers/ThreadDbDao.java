package cn.chenxhcloud.services.thread.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
public interface ThreadDbDao {
	/**
	 * save threadinfo
	 * @param threadInfo
	 * @return
	 */
	@Insert("INSERT INTO `tb_threads` ( `data_id`, `name`, `queue_size`, `counter`, `message`) VALUES (#{threadInfo.dataId}, #{threadInfo.name}, #{threadInfo.queueSize}, #{threadInfo.counter}, #{threadInfo.message});")
	int save(@Param("threadInfo") ThreadInfo threadInfo);
}
