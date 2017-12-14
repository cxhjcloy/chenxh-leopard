package cn.chenxhcloud.services.people.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.chenxhcloud.models.bacth.Person;

/**
 * 
*   
* 项目名称：chenxh-services  
* 类名称：cn.chenxhcloud.services.people.mapper.PeopleMapper  
* @author：chenxh  
* 创建时间：2017年12月14日 上午11:43:56
* 描述：
*
 */
@Mapper
public interface PeopleMapper {
	
	/**
	 * 获取所有person
	 * @return
	 */
	@Select("select * from people")
	public List<Person> getAllPerson();

	
	/**
	 * 新增一个person
	 * @param person
	 * @return
	 */
	@Insert("INSERT INTO `people` (`id`, `name`, `age`, `nation`, `address`) VALUES (#{person.id},#{person.name},#{person.age},#{person.nation},#{person.address})")
	public Integer savePeople(@Param("person") Person person);

}
