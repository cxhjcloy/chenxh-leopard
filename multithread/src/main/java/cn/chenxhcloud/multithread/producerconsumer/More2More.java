package cn.chenxhcloud.multithread.producerconsumer;

import org.springframework.stereotype.Component;

/**
 * 
*   
* 项目名称：chenxh-multithread  
* 类名称：cn.chenxhcloud.multithread.producerconsumer.More2More  
* @author：chenxh  
* 创建时间：2017年12月26日 下午6:11:27
* 描述：
*
 */
@Component
public class More2More  {
	
	public void testValue() {
		MyQueue queue = new MyQueue();
		Thread producer1 = new Thread(new Producer(queue), "producer1");
		Thread producer2 = new Thread(new Producer(queue), "producer2");
		Thread producer3 = new Thread(new Producer(queue), "producer3");
		Thread producer4 = new Thread(new Producer(queue), "producer4");
		Thread producer5 = new Thread(new Producer(queue), "producer5");
		Thread producer6 = new Thread(new Producer(queue), "producer6");
		Thread producer7 = new Thread(new Producer(queue), "producer7");
		Thread producer8 = new Thread(new Producer(queue), "producer8");
		Thread producer9 = new Thread(new Producer(queue), "producer9");
		Thread producer10 = new Thread(new Producer(queue), "producer10");
		Thread producer11 = new Thread(new Producer(queue), "producer11");
		Thread producer12 = new Thread(new Producer(queue), "producer12");
		Thread producer13 = new Thread(new Producer(queue), "producer13");
		Thread producer14 = new Thread(new Producer(queue), "producer14");
		Thread producer15 = new Thread(new Producer(queue), "producer15");
		Thread producer16 = new Thread(new Producer(queue), "producer16");
		Thread producer17 = new Thread(new Producer(queue), "producer17");
		Thread producer18 = new Thread(new Producer(queue), "producer18");
		Thread producer19 = new Thread(new Producer(queue), "producer19");
		Thread producer20 = new Thread(new Producer(queue), "producer20");
		Thread producer21 = new Thread(new Producer(queue), "producer21");
		Thread producer22 = new Thread(new Producer(queue), "producer22");
		Thread producer23 = new Thread(new Producer(queue), "producer23");
		Thread producer24 = new Thread(new Producer(queue), "producer24");
		Thread producer25 = new Thread(new Producer(queue), "producer25");
		Thread producer26 = new Thread(new Producer(queue), "producer26");
		Thread producer27 = new Thread(new Producer(queue), "producer27");
		Thread producer28 = new Thread(new Producer(queue), "producer28");
		Thread producer29 = new Thread(new Producer(queue), "producer29");
		Thread producer30 = new Thread(new Producer(queue), "producer30");
		Thread producer31 = new Thread(new Producer(queue), "producer31");
		Thread producer32 = new Thread(new Producer(queue), "producer32");
		Thread producer33 = new Thread(new Producer(queue), "producer33");
		Thread producer34 = new Thread(new Producer(queue), "producer34");
		Thread producer35 = new Thread(new Producer(queue), "producer35");
		Thread producer36 = new Thread(new Producer(queue), "producer36");
		Thread producer37 = new Thread(new Producer(queue), "producer37");
		Thread producer38 = new Thread(new Producer(queue), "producer38");
		Thread producer39 = new Thread(new Producer(queue), "producer39");
		Thread producer40 = new Thread(new Producer(queue), "producer40");
		Thread producer41 = new Thread(new Producer(queue), "producer41");
		Thread producer42 = new Thread(new Producer(queue), "producer42");
		Thread producer43 = new Thread(new Producer(queue), "producer43");
		Thread producer44 = new Thread(new Producer(queue), "producer44");
		Thread producer45 = new Thread(new Producer(queue), "producer45");
		Thread producer46 = new Thread(new Producer(queue), "producer46");
		Thread producer47 = new Thread(new Producer(queue), "producer47");
		Thread producer48 = new Thread(new Producer(queue), "producer48");
		Thread producer49 = new Thread(new Producer(queue), "producer49");
		Thread producer50 = new Thread(new Producer(queue), "producer50");

		Thread customer1 = new Thread(new Customer(queue), "customer1");
		Thread customer2 = new Thread(new Customer(queue), "customer2");
		Thread customer3 = new Thread(new Customer(queue), "customer3");
		Thread customer4 = new Thread(new Customer(queue), "customer4");
		Thread customer5 = new Thread(new Customer(queue), "customer5");
		Thread customer6 = new Thread(new Customer(queue), "customer6");
		Thread customer7 = new Thread(new Customer(queue), "customer7");
		Thread customer8 = new Thread(new Customer(queue), "customer8");
		Thread customer9 = new Thread(new Customer(queue), "customer9");
		Thread customer10 = new Thread(new Customer(queue), "customer10");
		Thread customer11 = new Thread(new Customer(queue), "customer11");
		Thread customer12 = new Thread(new Customer(queue), "customer12");
		Thread customer13 = new Thread(new Customer(queue), "customer13");
		Thread customer14 = new Thread(new Customer(queue), "customer14");
		Thread customer15 = new Thread(new Customer(queue), "customer15");
		Thread customer16 = new Thread(new Customer(queue), "customer16");
		Thread customer17 = new Thread(new Customer(queue), "customer17");
		Thread customer18 = new Thread(new Customer(queue), "customer18");
		Thread customer19 = new Thread(new Customer(queue), "customer19");
		Thread customer20 = new Thread(new Customer(queue), "customer20");
		Thread customer21 = new Thread(new Customer(queue), "customer21");
		Thread customer22 = new Thread(new Customer(queue), "customer22");
		Thread customer23 = new Thread(new Customer(queue), "customer23");
		Thread customer24 = new Thread(new Customer(queue), "customer24");
		Thread customer25 = new Thread(new Customer(queue), "customer25");
		Thread customer26 = new Thread(new Customer(queue), "customer26");
		Thread customer27 = new Thread(new Customer(queue), "customer27");
		Thread customer28 = new Thread(new Customer(queue), "customer28");
		Thread customer29 = new Thread(new Customer(queue), "customer29");
		Thread customer30 = new Thread(new Customer(queue), "customer30");
		Thread customer31 = new Thread(new Customer(queue), "customer31");
		Thread customer32 = new Thread(new Customer(queue), "customer32");
		Thread customer33 = new Thread(new Customer(queue), "customer33");
		Thread customer34 = new Thread(new Customer(queue), "customer34");
		Thread customer35 = new Thread(new Customer(queue), "customer35");
		Thread customer36 = new Thread(new Customer(queue), "customer36");
		Thread customer37 = new Thread(new Customer(queue), "customer37");
		Thread customer38 = new Thread(new Customer(queue), "customer38");
		Thread customer39 = new Thread(new Customer(queue), "customer39");
		Thread customer40 = new Thread(new Customer(queue), "customer40");
		Thread customer41 = new Thread(new Customer(queue), "customer41");
		Thread customer42 = new Thread(new Customer(queue), "customer42");
		Thread customer43 = new Thread(new Customer(queue), "customer43");
		Thread customer44 = new Thread(new Customer(queue), "customer44");
		Thread customer45 = new Thread(new Customer(queue), "customer45");
		Thread customer46 = new Thread(new Customer(queue), "customer46");
		Thread customer47 = new Thread(new Customer(queue), "customer47");
		Thread customer48 = new Thread(new Customer(queue), "customer48");
		Thread customer49 = new Thread(new Customer(queue), "customer49");
		Thread customer50 = new Thread(new Customer(queue), "customer50");

		customer1.start();
		customer2.start();
		customer3.start();
		customer4.start();
		customer5.start();
		customer6.start();
		customer7.start();
		customer8.start();
		customer9.start();
		customer10.start();
		customer11.start();
		customer12.start();
		customer13.start();
		customer14.start();
		customer15.start();
		customer16.start();
		customer17.start();
		customer18.start();
		customer19.start();
		customer20.start();
		customer21.start();
		customer22.start();
		customer23.start();
		customer24.start();
		customer25.start();
		customer26.start();
		customer27.start();
		customer28.start();
		customer29.start();
		customer30.start();
		customer31.start();
		customer32.start();
		customer33.start();
		customer34.start();
		customer35.start();
		customer36.start();
		customer37.start();
		customer38.start();
		customer39.start();
		customer40.start();
		customer41.start();
		customer42.start();
		customer43.start();
		customer44.start();
		customer45.start();
		customer46.start();
		customer47.start();
		customer48.start();
		customer49.start();
		customer50.start();
		
		producer1.start();
		producer2.start();
		producer3.start();
		producer4.start();
		producer5.start();
		producer6.start();
		producer7.start();
		producer8.start();
		producer9.start();
		producer10.start();
		producer11.start();
		producer12.start();
		producer13.start();
		producer14.start();
		producer15.start();
		producer16.start();
		producer17.start();
		producer18.start();
		producer19.start();
		producer20.start();
		producer21.start();
		producer22.start();
		producer23.start();
		producer24.start();
		producer25.start();
		producer26.start();
		producer27.start();
		producer28.start();
		producer29.start();
		producer30.start();
		producer31.start();
		producer32.start();
		producer33.start();
		producer34.start();
		producer35.start();
		producer36.start();
		producer37.start();
		producer38.start();
		producer39.start();
		producer40.start();
		producer41.start();
		producer42.start();
		producer43.start();
		producer44.start();
		producer45.start();
		producer46.start();
		producer47.start();
		producer48.start();
		producer49.start();
		producer50.start();
		try {
			customer1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
