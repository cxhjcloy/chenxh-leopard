package cn.chenxhcloud.nosql.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import cn.chenxhcloud.nosql.mongo.entity.LogEntity;


/**
 * 
*   
* 项目名称：chenxh-nosql  
* 类名称：cn.chenxhcloud.nosql.mongo.LogService  
* @author：chenxh  
* 创建时间：2018年1月3日 下午5:08:24
* 描述：
*
 */
@Service
public class LogService {
	@Autowired
	private MongoTemplate mongoTemplate;

	
	public List<LogEntity> findAll() {
		return mongoTemplate.findAll(LogEntity.class);
	}
}
