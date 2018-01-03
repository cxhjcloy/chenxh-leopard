package cn.chenxhcloud.nosql.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import cn.chenxhcloud.nosql.mongo.entity.UserEntity;




/**
 * 
*   
* 项目名称：chenxh-nosql  
* 类名称：cn.chenxhcloud.nosql.mongo.UserService  
* @author：chenxh  
* 创建时间：2018年1月3日 下午5:08:34
* 描述：
*
 */
@Service
public class UserService {
	@Autowired
	private MongoTemplate mongoTemplate;

	public void saveUser(UserEntity user) {
		mongoTemplate.save(user);
	}
	public UserEntity findUserByUserName(String userName) {
		Query query = new Query(Criteria.where("userName").is(userName));
		UserEntity user = mongoTemplate.findOne(query, UserEntity.class);
		return user;
	}	
	
	public void updateUser(UserEntity user) {
        Query query=new Query(Criteria.where("id").is(user.getId()));
        Update update= new Update().set("userName", user.getUserName()).set("passWord", user.getPassWord());
        mongoTemplate.updateFirst(query,update,UserEntity.class);
    }	
    public void deleteUserById(Long id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,UserEntity.class);
    }
	public List<UserEntity> findAll() {
		return mongoTemplate.findAll(UserEntity.class);
	}
}
