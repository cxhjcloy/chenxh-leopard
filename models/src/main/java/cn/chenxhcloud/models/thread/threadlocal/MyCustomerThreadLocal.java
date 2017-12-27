package cn.chenxhcloud.models.thread.threadlocal;




/**
 * 
*   
* 项目名称：chenxh-models  
* 类名称：cn.chenxhcloud.models.thread.threadlocal.MyCustomerThreadLocal  
* @author：chenxh  
* 创建时间：2017年12月27日 上午9:48:15
* 描述：记录消费者的Threadlocal  在多个消费者情况下，每个消费者消费的个数
*
 */
public class MyCustomerThreadLocal {
	private final static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>() {
		/**
		 * 初始化
		 */
		@Override
		protected Long initialValue() {
			return 0L;
		}
	};
	public static void set(Long data) {
		threadLocal.set(data);
	}
	public static Long get() {
		return threadLocal.get();
	}
}
