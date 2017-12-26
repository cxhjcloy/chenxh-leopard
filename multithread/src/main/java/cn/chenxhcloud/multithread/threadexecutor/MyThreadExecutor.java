package cn.chenxhcloud.multithread.threadexecutor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyThreadExecutor {
	
	private static final Integer PAGE_SIZE = 150000;

	public static void main(String[] args) {
		Long start = System.currentTimeMillis();
		Long count = DbService.getCount();
		Long taskSize = count/PAGE_SIZE+1;
		ExecutorService pool = Executors.newFixedThreadPool(taskSize.intValue());
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