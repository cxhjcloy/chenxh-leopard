package cn.chenxhcloud.services.world.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.chenxhcloud.models.world.WorldCity;

@Mapper
public interface WorldMapper {

	public List<WorldCity> getCityList();

	@Select("select t.id,t.countryCode,t.district,t.name,t.population from city t where t.name like CONCAT('%',#{cityName},'%')")
	public List<WorldCity> getCityByName(@Param("cityName") String cityName);

	@Select("select t.id,t.countryCode,t.district,t.name,t.population from city t where t.id =#{cityId}")
	public WorldCity getCityById( @Param("cityId") int cityId);

}
