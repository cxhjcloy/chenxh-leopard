package cn.chenxhcloud.multithread.threadexecutor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * 
*   
* 项目名称：chenxh-leopard  
* 类名称：.MyThreadExecutor  
* @author：chenxh  
* 创建时间：2017年12月27日 下午6:02:04
* 描述：
*
 */
public class MyThreadExecutor {
	
	private static final Integer PAGE_SIZE = 150000;

	/**
	 * ThreadPoolExecutor的使用
	 * 		1 corePoolSize: 核心线程数 核心线程会一直存活，及时没有任务需要执行 当线程数小于核心线程数时，即使有线程空闲，线程池也会优先创建新线程处理 设置allowCoreThreadTimeout=true（默认false）时，核心线程会超时关闭
	 * 		2 maximumPoolSize: 最大线程数
	 * 		3 keepAliveTime: 线程空闲时间 当线程空闲时间达到keepAliveTime时，线程会退出，直到线程数量=corePoolSize 如果allowCoreThreadTimeout=true，则会直到线程数量=0
	 * 		4 TimeUnit 时间单位
	 * 		5 workQueue
	 * 		6 threadFactory the factory to use when the executor  creates a new thread
	 * @param args
	 */
	public static void main(String[] args) {
		
		Long start = System.currentTimeMillis();
		
		Long count = DbService.getCount();
		
		Long taskSize = count/PAGE_SIZE+1;
		
		ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
		ExecutorService pool = new ThreadPoolExecutor(50, 500, 0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(1024),namedThreadFactory);
		List<Future<List<ThreadInfo>>> list = new ArrayList<Future<List<ThreadInfo>>>();
		for (int i = 0; i < taskSize; i++) {
			PageInfo pageInfo = new PageInfo();
			pageInfo.setStart(i * PAGE_SIZE);
			pageInfo.setEnd(PAGE_SIZE);
			Callable<List<ThreadInfo>> c = new MyCallable(pageInfo);
			Future<List<ThreadInfo>> f = pool.submit(c);
			list.add(f);
		}
		pool.shutdown();
		for (Future<List<ThreadInfo>> future : list) {
			try {
				List<ThreadInfo> data = future.get();
				System.out.println(Thread.currentThread().getName()+"得到数据条数："+data.size());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Long end = System.currentTimeMillis();
		System.out.println("线程" + Thread.currentThread().getName() + "执行了" + (end - start) + "毫秒");
	}
}

class MyCallable implements Callable<List<ThreadInfo>> {
	
	private PageInfo pageInfo;

	MyCallable(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	@Override
	public List<ThreadInfo> call() {
		Long start = System.currentTimeMillis();
		List<ThreadInfo> datas = DbService.getPageData(pageInfo.getStart(), pageInfo.getEnd());
		FileWriter fw =null;
		BufferedWriter writer = null;
		try {
			File file = new File(Thread.currentThread().getName()+"-"+pageInfo.getStart()+"-"+pageInfo.getEnd()+".data");
			System.out.println(file.getAbsolutePath());
			fw = new FileWriter(file);
			writer = new BufferedWriter(fw);
			for (ThreadInfo threadInfo : datas) {
				writer.write(threadInfo.toString());
				writer.write("\n");
			}
            writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				writer.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Long end = System.currentTimeMillis();
		System.out.println("线程" + Thread.currentThread().getName() + "执行了" + (end - start) + "毫秒");
		return datas;
	}
}