# 多线程框架Executor

## 为什么引入Executor线程池框架

首先每次new Thread()耗费性能,调用new Thread()创建的线程缺乏管理，被称为野线程，而且可以无限制创建，之间相互竞争，会导致过多占用系统资源导致系统瘫痪。不利于扩展，比如如定时执行、定期执行、线程中断。
采用线程池可以重用存在的线程，减少对象创建、消亡的开销，可有效控制最大并发线程数，提高系统资源的使用率，同时避免过多资源竞争，避免堵塞。提供定时执行、定期执行、单线程、并发数控制等功能

## Executor的介绍

在Java 5之后，并发编程引入了一堆新的启动、调度和管理线程的API。Executor框架便是Java 5中引入的，其内部使用了线程池机制，它在java.util.cocurrent 包下，通过该框架来控制线程的启动、执行和关闭，可以简化并发编程的操作。因此，在Java 5之后，通过Executor来启动线程比使用Thread的start方法更好，更易管理，效率更好（用线程池实现，节约开销）

## Executor框架包括：

* 线程池
* Executor
* Executors
* ExecutorService
* CompletionService
* Future
* Callable等

## Executors方法介绍

### Executors工厂类

通过Executors提供四种线程池，newFixedThreadPool、newCachedThreadPool、newSingleThreadExecutor、newScheduledThreadPool。

* 1.public static ExecutorService newFixedThreadPool(int nThreads)
	创建固定数目线程的线程池。

* 2.public static ExecutorService newCachedThreadPool()
	创建一个可缓存的线程池，调用execute将重用以前构造的线程（如果线程可用）。如果现有线程没有可用的，则创建一个新线 程并添加到池中。终止并从缓存中移除那些已有 60 秒钟未被使用的线程。

* 3.public static ExecutorService newSingleThreadExecutor()
	创建一个单线程化的Executor。

* 4.public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize)
	创建一个支持定时及周期性的任务执行的线程池，多数情况下可用来替代Timer类。

## ExecutorService说明

ExecutorService接口继承自Executor接口，它提供了更丰富的实现多线程的方法，比如，ExecutorService提供了关闭自己的方法，以及可为跟踪一个或多个异步任务执行状况而生成 Future 的方法。 可以调用ExecutorService的shutdown（）方法来平滑地关闭。ExecutorService，调用该方法后，将导致ExecutorService停止接受任何新的任务且等待已经提交的任务执行完成(已经提交的任务会分两类：一类是已经在执行的，另一类是还没有开始执行的)，当所有已经提交的任务执行完毕后将会关闭ExecutorService。因此一般用该接口来实现和管理多线程。

ExecutorService的生命周期包括三种状态：运行、关闭、终止。创建后便进入运行状态，当调用了shutdown（）方法时，便进入关闭状态，此时意味着ExecutorService不再接受新的任务，但它还在执行已经提交了的任务，当素有已经提交了的任务执行完后，便到达终止状态。如果不调用shutdown（）方法，ExecutorService会一直处在运行状态，不断接收新的任务，执行新的任务，服务器端一般不需要关闭它，保持一直运行即可。

Executor执行Runnable任务，一旦Runnable任务传递到execute（）方法，该方法便会自动在一个线程上执行


## Executor执行Callable任务
在Java 5之后，任务分两类：一类是实现了Runnable接口的类，一类是实现了Callable接口的类。两者都可以被ExecutorService执行，但是Runnable任务没有返回值，而Callable任务有返回值。并且Callable的call()方法只能通过ExecutorService的submit(Callable<T> task) 方法来执行，并且返回一个 <T>Future<T>，是表示任务等待完成的 Future。


