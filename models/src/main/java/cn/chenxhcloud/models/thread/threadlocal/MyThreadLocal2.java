package cn.chenxhcloud.models.thread.threadlocal;



/**
 * 
*   
* 项目名称：chenxh-models  
* 类名称：cn.chenxhcloud.models.thread.threadlocal.MyThreadLocal2  
* @author：chenxh  
* 创建时间：2017年12月26日 下午6:12:39
* 描述：
*
 */
public class MyThreadLocal2 {
	private final static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();
	public static void set(Long data) {
		threadLocal.set(data);
	}
	public static Long get() {
		return threadLocal.get();
	}
}
