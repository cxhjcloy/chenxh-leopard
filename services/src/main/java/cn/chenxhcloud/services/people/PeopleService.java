package cn.chenxhcloud.services.people;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.chenxhcloud.models.bacth.Person;
import cn.chenxhcloud.services.people.mapper.PeopleMapper;

/**
 * 
*   
* 项目名称：chenxh-services  
* 类名称：cn.chenxhcloud.services.people.PeopleService  
* @author：chenxh  
* 创建时间：2017年12月14日 上午11:40:53
* 描述：
*
 */
@Service
public class PeopleService {
	private static final Logger log = LoggerFactory.getLogger(PeopleService.class);
	@Autowired
	private PeopleMapper peopleMapper;

	/**
	 * 获取所有person
	 * @return
	 */
	public List<Person> getAllPerson() {
		if (log.isInfoEnabled()) {
			log.info("getAllPerson");
		}
		return peopleMapper.getAllPerson();
	}

	/**
	 * 新增一个person
	 * @param person
	 * @return
	 */
	public Integer savePeople(Person person) {
		if (log.isInfoEnabled()) {
			log.info("savePeople：{} ", person);
		}
		return peopleMapper.savePeople(person);
	}
}
