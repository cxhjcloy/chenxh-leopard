package cn.chenxhcloud.models.thread.threadlocal;



/**
 * 
*   
* 项目名称：chenxh-models  
* 类名称：cn.chenxhcloud.models.thread.threadlocal.MyProducerThreadLocal  
* @author：chenxh  
* 创建时间：2017年12月27日 上午9:36:54
* 描述：生产者Threadlocal记录着  在多个生产者情况下，每个生产者生产的个数
*
 */
public class MyProducerThreadLocal {
	private final static ThreadLocal<Long> MY_THREADLOCAL = new ThreadLocal<Long>() {
		/**
		 * 设置初始值
		 */
		@Override
		protected Long initialValue() {
			return 0L;
		}
	};
	public static void set(Long data) {
		MY_THREADLOCAL.set(data);
	}
	public static Long get() {
		return MY_THREADLOCAL.get();
	}
}
