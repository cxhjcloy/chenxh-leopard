package cn.chenxhcloud.configs.dbs;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
*   
* 项目名称：chenxh-app  
* 类名称：cn.chenxhcloud.configs.dbs.DataSourceContextHolder  
* @author : chenxh  
* 创建时间：2017年12月12日 下午5:02:39
* 描述：采用ThreadLocal保存动态数据源的ID，进行数据源的切换
*
 */
public class DataSourceContextHolder {
	public static final Logger log = LoggerFactory.getLogger(DataSourceContextHolder.class);
	//ThreadLocal 
	/**
	 * CONTEXT_HOLDER
	 */
	private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<String>();
	
	public static List<String> dataSourceIds = new ArrayList<String>();  
	
    public static void setDbType(String dbType) {
    	CONTEXT_HOLDER.set(dbType);  
    }  
  
    public static String getDbType() {  
        return CONTEXT_HOLDER.get(); 
    }  
  
    public static void clearDbType() {  
    	CONTEXT_HOLDER.remove();  
    }
    
    public static boolean containsDataSource(String dataSourceId){
        return dataSourceIds.contains(dataSourceId);
    }
}
