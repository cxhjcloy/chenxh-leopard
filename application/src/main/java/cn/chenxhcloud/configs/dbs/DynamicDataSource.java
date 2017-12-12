package cn.chenxhcloud.configs.dbs;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 
*   
* 项目名称：chenxh-app  
* 类名称：cn.chenxhcloud.configs.dbs.DynamicDataSource  
* @author : chenxh  
* 创建时间：2017年12月12日 下午5:02:45
* 描述：实现spring的AbstractRoutingDataSource接口，完成动态数据源的切换功能！
*
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDbType();
    }
}