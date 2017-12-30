#Executor框架学习笔记

Java中的线程即是工作单元也是执行机制，从JDK 5后，工作单元与执行机制被分离。工作单元包括Runnable和Callable，执行机制由JDK 5中增加的java.util.concurrent包中Executor框架提供。
HotSpot VM的线程模型中将java的线程映射为本地操作系统的线程，java线程的启动意味着一个本地操作系统线程的创建，而java线程的终止也就意味着对应的系统线程的回收。

 

##Executor框架的三个部分

* 任务：
	包括Runnable和Callable，其中Runnable表示一个可以异步执行的任务，而Callable表示一个会产生结果的任务

* 任务的执行：
	包括Executor框架的核心接口Executor以及其子接口ExecutorService。在Executor框架中有两个关键类ThreadPoolExecutor和ScheduledThreadPoolExecutor实现了ExecutorService接口。

* 异步计算的结果：
	包括接口Future和其实现类FutureTask。

 

##Executor接口

下面是对Executor框架中的一些关键接口与类的简介

Executor接口(java.util.concurrent.Executor)

它是Executor的基础与核心，其定义如下：
	```
		public interface Executor {
		    void execute(Runnable command);
		}
	``` 

它包含了一个方法execute，参数为一个Runnable接口引用。

Executor接口将任务的提交与执行分离开来。
 

##ThreadPoolExecutor类(java.util.concurrent.ThreadPoolExecutor)

它是线程池的核心实现类，用来执行被提交的任务。

它通常由工厂类Executors来创建，Executors可以创建SingleThreadExecutor，FixedThreadPool以及CachedThreadPool等不同的ThreadPoolExecutor。

SingleThreadExecutor使用单线程执行任务，Executors提供的API有如下两个
* public static ExecutorService newSingleThreadExecutor();
* public static ExecutorService newSingleThreadExecutor(ThreadFactory threadFactory);
SingleThreadExecutor保证了任务执行的顺序，不会存在多线程活动。

FixedThreadPool是使用固定线程数的线程池,Executors提供的API有如下两个
* public static ExecutorService newFixedThreadPool(int nThreads);
* public static ExecutorService newFixedThreadPool(int nThreads, ThreadFactory threadFactory);
FixedThreadPool满足了资源管理的需求，可以限制当前线程数量。适用于负载较重的服务器环境。

CachedThreadPool是无界线程池，Executors提供的API有如下两个
* public static ExecutorService newCachedThreadPool();
* public static ExecutorService newCachedThreadPool(ThreadFactory threadFactory);
CachedThreadPool适用于执行很多短期异步任务的小程序，适用于负载较轻的服务器。

 

##ScheduledThreadPoolExecutor类

它是ThreadPoolExecutor的子类且实现了ScheduledExecutorService接口，它可以在给定的延迟时间后执行命令，或者定期执行命令，它比Timer更强大更灵活。

Executors可以创建的ScheduledThreadPoolExecutor的类型有ScheduledThreadPoolExecutor和SingleThreadScheduledExecutor等
ScheduledThreadPoolExecutor具有固定线程个数，适用于需要多个后台线程执行周期任务，并且为了满足资源管理需求而限制后台线程数量的场景,Executors中提供的API有如下两个：
* public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize);
* public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize, ThreadFactory threadFactory);
 

SingleThreadScheduledExecutor具有单个线程，Executors提供的创建API有如下两个：
* public static ScheduledExecutorService newSingleThreadScheduledExecutor();
* public static ScheduledExecutorService newSingleThreadScheduledExecutor(ThreadFactory threadFactory);
它适用于单个后台线程执行周期任务，并且保证顺序一致执行的场景。

 
上述的ThreadPoolExecutor和ScheduledThreadPoolExecutor都可以用于执行Runnable与Callable接口的实现类
 

## Future接口(Java.concurrent.Future)

Future代表着提交的任务的计算状态与结果，可以对其进行取消，查询是否取消，查询是否完成，查询结果等操作。

首先来看一下Future接口的定义：

```
	public interface Future<V> {
	
	    boolean cancel(boolean mayInterruptIfRunning);
	
	    boolean isCancelled();
	
	    boolean isDone();
	
	    V get() throws InterruptedException, ExecutionException;
	
	    V get(long timeout, TimeUnit unit)
	        throws InterruptedException, ExecutionException, TimeoutException;
	
	}
```

## FutureTask类

FutureTask类间接实现了Future接口，它用来表示异步计算的结果。当ThreadPoolExecutor或者ScheduledThreadPoolExecutor执行Runnable接口或者Callable接口的实现类时，它们会返回一个Future接口引用（实现类为FutureTask）。

## Runnable
Runnable接口或者Callable接口的实现类在被上述两者执行的区别是，前者没有返回结果，而后者可以返回结果。Runnable可以通过Executors提供的API(Executors#callable)包装为Callable。


-----------------------------------------------------------------------------------------------------------------

下面是一些简单的Demo程序

```
  import java.util.concurrent.Executor;
  import java.util.concurrent.Executors;
  
  public class ExecutorTest {
      public static void main(String[] args) {
  
          Runnable hello = () -> {
              for (int i = 0; i < 100; i++) {
                  System.out.println(i + " hello");
             }
         };
         Runnable bye = () -> {
            for (int i = 0; i < 100; i++) {
                 System.out.println(i + " bye");
             }
         };
 
         Executor executor = Executors.newCachedThreadPool();
 
         executor.execute(hello);
         executor.execute(bye);
 
     }
 }
 ```

上面程序使用了两个Runnable任务hello和bye来打印相应语句，程序将会交错打印hello和bye。如果将executor改为SingleThreadExecutor，将会先打印100个"hello"，再打印100个"bye"。

 

```
  import java.util.ArrayList;
  import java.util.List;
  import java.util.Random;
  import java.util.concurrent.Callable;
  import java.util.concurrent.ExecutionException;
  import java.util.concurrent.ExecutorService;
  import java.util.concurrent.Executors;
  import java.util.concurrent.Future;
  
  public class ExecutorTest {
      public static void main(String[] args) {
          Random random = new Random();
          List<Integer> numbers = new ArrayList<>();
          for (int i = 0; i < 100000; i++) {
              numbers.add(random.nextInt(100000));
          }
          int result = calculate(numbers, 3);
          System.out.println(result);
      }
  
      public static int calculate(List<Integer> numbers,int digit) {
          List<Callable<Integer>> tasks = new ArrayList<>();
          for (Integer x : numbers) {
              tasks.add(() -> {
                  int count=0;
                  int y=x;
                  do {
                      if (y % 10 == digit) {
                          count++;
                      }
                      y /= 10;
                  } while (y > 0);
                  return count;
              });
          }
          ExecutorService service = Executors.newFixedThreadPool(10);
           int answer=0;
          try {
              List<Future<Integer>> results = service.invokeAll(tasks);
              for (Future<Integer> result : results) {
                  try {
                      answer+=result.get();
                  } catch (ExecutionException e) {
                      e.printStackTrace();
                  }
              }
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          return answer;
      }
  }
```
上面的程序随机生成了100000个随机数，然后统计这些数字中每个数字10进制中具有多少个数位3的数量和。使用具有10个线程的线程池进行计算，最终通过Future引用对象的结果来统计答案。