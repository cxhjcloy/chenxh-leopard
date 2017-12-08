package cn.chenxhcloud.configs.dbs;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DataSourceContextHolder {
	public static final Logger log = LoggerFactory.getLogger(DataSourceContextHolder.class);
	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();//ThreadLocal 
	
	public static List<String> dataSourceIds = new ArrayList<String>();  
	
    public static void setDbType(String dbType) {
        contextHolder.set(dbType);  
    }  
  
    public static String getDbType() {  
        return contextHolder.get(); 
    }  
  
    public static void clearDbType() {  
        contextHolder.remove();  
    }
    
    public static boolean containsDataSource(String dataSourceId){
        return dataSourceIds.contains(dataSourceId);
    }
}
