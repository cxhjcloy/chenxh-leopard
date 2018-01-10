package cn.chenxhcloud.utils.collections;

import java.io.IOException;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 * 
*   
* 项目名称：chenxh-utils  
* 类名称：cn.chenxhcloud.utils.collections.HashSetUtils  
* @author：chenxh  
* 创建时间：2018年1月10日 上午10:48:57
* 描述：HashSet LinkedHastSet TreeSet EnumSet
*
 */
public class HashSetUtils {
	
	/**
	 * HashSet类直接实现了Set接口， 其底层其实是包装了一个HashMap去实现的。
	 * HashSet采用HashCode算法来存取集合中的元素，因此具有比较好的读取和查找性能
	 */
	public static void testHashSet() {
		 HashSet<Item> hs = new HashSet<>();  
         hs.add(new Item(1,"tom"));  
         hs.add(new Item(2,"jerry"));
         hs.add(new Item(3,"cat"));  
         hs.add(new Item(4,"too"));  
         hs.add(new Item(5,"bar"));
         hs.add(new Item(6,"too")); 
         hs.add(new Item(6,"too")); 
         hs.stream().forEach(item->System.out.println(item));
         System.out.println(hs);
	}
	
	
	/**
	 * LinkedHashSet是HashSet的一个子类，LinkedHashSet也根据HashCode的值来决定元素的存储位置，
	 * 但同时它还用一个链表来维护元素的插入顺序，插入的时候即要计算hashCode又要维护链表，
	 * 而遍历的时候只需要按链表来访问元素
	 */
	public static void testLinkedHashSet() {
		LinkedHashSet<Item> linkedHashSet = new LinkedHashSet<>();
		linkedHashSet.add(new Item(1,"tom"));  
		linkedHashSet.add(new Item(2,"jerry"));
		linkedHashSet.add(new Item(3,"cat"));  
		linkedHashSet.add(new Item(4,"too"));  
		linkedHashSet.add(new Item(5,"bar"));
		linkedHashSet.add(new Item(6,"too")); 
		linkedHashSet.add(new Item(6,"too")); 
		linkedHashSet.iterator().forEachRemaining(System.out::println);
        System.out.println(linkedHashSet);
		
	}

	/**
	 * TreeSet实现了SortedSet接口，顾名思义这是一种排序的Set集合，查看jdk源码发现底层是用TreeMap实现的，本质上是一个红黑树原理。 
	 * 正因为它是排序了的，所以相对HashSet来说，TreeSet提供了一些额外的按排序位置访问元素的方法，
	 * 例如first(), last(), lower(), higher(), subSet(), headSet(), tailSet().
	 * TreeSet的排序分两种类型，一种是自然排序，另一种是定制排序
	 */
	public static void testTreeSet() {
		TreeSet<Item> tressSet = new TreeSet<>();
		tressSet.add(new Item(1, "tom"));
		tressSet.add(new Item(2, "jerry"));
		tressSet.add(new Item(3, "cat"));
		tressSet.add(new Item(4, "too"));
		tressSet.add(new Item(5, "bar"));
		tressSet.add(new Item(6, "too"));
		tressSet.add(new Item(6, "too"));
		tressSet.iterator().forEachRemaining(System.out::println);
		System.out.println(tressSet);
	}

	/**
	 * EnumSet顾名思义就是专为枚举类型设计的集合，因此集合元素必须是枚举类型，否则会抛出异常。 
	 * EnumSet集合也是有序的，其顺序就是Enum类内元素定义的顺序。
	 * EnumSet存取的速度非常快，批量操作的速度也很快。
	 * EnumSet主要提供以下方法，allOf, complementOf, copyOf, noneOf, of, range等。
	 * 注意到EnumSet并没有提供任何构造函数，要创建一个EnumSet集合对象，只需要调用allOf等方法
	 */
	public static void testEnumSet() {
		EnumSet<Season> es1 = EnumSet.allOf(Season.class);
		System.out.println(es1);
		EnumSet<Season> es2 = EnumSet.noneOf(Season.class);
		es2.add(Season.WINTER);
		es2.add(Season.SUMMER);
		System.out.println(es2);
		es2.parallelStream().iterator().forEachRemaining(System.out::println);
		EnumSet<Season> es3 = EnumSet.of(Season.WINTER, Season.SUMMER);
		System.out.println(es3);
		EnumSet<Season> es4 = EnumSet.range(Season.SUMMER, Season.WINTER);
		System.out.println(es4);
		EnumSet<Season> es5 = EnumSet.complementOf(es4);
		System.out.println(es5);
	}

	public static void main(String[] args) throws Exception {
		//获取当前系统的CPU 数目 
		int cpuNums = Runtime.getRuntime().availableProcessors();
		System.out.println(cpuNums);
		Runtime.getRuntime().exec("mstsc");		
		testEnumSet();
	}
}

enum Season  
{  
	/**
	 * 
	 */
    SPRING, SUMMER, FALL, WINTER  
}  


class Item implements Comparable<Item>{
	private Integer id;
	private String name;

	public Item(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + "]";
	}
	
	/**
	 * 
	 */
	@Override
	public int compareTo(Item o) {
		if(o.id!=null && this.id!=null) {
			return  o.id.compareTo(this.id);
		}
		if(o.id==null && this.id!=null) {
			return 1;
		}
		if(o.id!=null && this.id==null) {
			return -1;
		}
		return 0;
	}
	
	
}