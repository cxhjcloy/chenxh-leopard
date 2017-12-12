package cn.chenxhcloud.services.world.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.chenxhcloud.models.world.WorldCity;

/**
 * 
*   
* 项目名称：chenxh-services  
* 类名称：cn.chenxhcloud.services.world.mapper.WorldMapper  
* @author : chenxh  
* 创建时间：2017年12月12日 下午5:14:18
* 描述：Mapper类，可以采用Xml格式或者Annotation方式配置sql
*
 */
@Mapper
public interface WorldMapper {
	/**
	 * getCityList
	 * @return 列表
	 */
	public List<WorldCity> getCityList();

	/**
	 * getCityByName
	 * @return 列表
	 * @param cityName
	 */
	@Select("select t.id,t.countryCode,t.district,t.name,t.population from city t where t.name like CONCAT('%',#{cityName},'%')")
	public List<WorldCity> getCityByName(@Param("cityName") String cityName);

	/**
	 * getCityById
	 * @return WorldCity
	 * @param cityId
	 */
	@Select("select t.id,t.countryCode,t.district,t.name,t.population from city t where t.id =#{cityId}")
	public WorldCity getCityById( @Param("cityId") int cityId);

}
