package cn.chenxhcloud.utils.collections;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.TreeMap;

/**
 * 
*   
* 项目名称：chenxh-utils  
* 类名称：cn.chenxhcloud.utils.collections.HashMapUtils  
* @author：chenxh  
* 创建时间：2018年1月10日 上午10:02:20
* 描述：Java为数据结构中的映射定义了一个接口java.util.Map，它有四个实现类，分别是HashMap、HashTable、LinkedHashMap和TreeMap
*
 */
public class HashMapUtils {
	
	/**
	 * HashMap是一个最常用的Map，它根据键的hashCode值存储数据，根据键可以直接获取它的值，具有很快的访问速度。
	 * HashMap最多只允许一条记录的键为null，不允许多条记录的值为null。HashMap不支持线程的同步，即任一时刻可以有多个线程同时写HashMap，
	 * 可能会导致数据的不一致。如果需要同步，可以用Collections.synchronizedMap(HashMap map)方法使HashMap具有同步的能力
	 */
	public static void testHashMap() {
		HashMap<String,Object> hashMap = new HashMap<>(16);
		hashMap.put("key1", "value1");
		hashMap.put("key2", "value2");
		hashMap.put("key3", "value3");
		hashMap.put("key4", 4);
		hashMap.put("key5", 5);
		hashMap.put("key5", 'h');
		hashMap.keySet().forEach(key -> System.out.println("map.get(" + key + ") = " + hashMap.get(key)));
		hashMap.entrySet().iterator().forEachRemaining(item -> System.out.println("key:value=" + item.getKey() + ":" + item.getValue()));
		hashMap.entrySet().forEach(entry -> System.out.println("key:value = " + entry.getKey() + ":" + entry.getValue()));
	}
	
	/**
	 * Hashtable与HashMap类似，不同的是：它不允许记录的键或者值为空；它支持线程的同步，即任一时刻只有一个线程能写Hashtable，然而，这也导致了Hashtable在写入时会比较慢
	 */
	public static void testHashTable() {
		Hashtable<String,Object> hashTable = new Hashtable<>(16);
		hashTable.put("key1", "value1");
		hashTable.put("key2", "value2");
		hashTable.put("key3", "value3");
		hashTable.put("key4", 4);
		hashTable.put("key5", 5);
		hashTable.put("key5", 'h');
		hashTable.keySet().forEach(key -> System.out.println("map.get(" + key + ") = " + hashTable.get(key)));
		hashTable.entrySet().iterator().forEachRemaining(item -> System.out.println("key:value=" + item.getKey() + ":" + item.getValue()));
		hashTable.entrySet().forEach(entry -> System.out.println("key:value = " + entry.getKey() + ":" + entry.getValue()));
	}

	/**
	 * LinkedHashMap保存了记录的插入顺序，在用Iteraor遍历LinkedHashMap时，先得到的记录肯定是先插入的。在遍历的时候会比HashMap慢。有HashMap的全部特性。
	 */
	public static void testLinkedHashMap() {
		LinkedHashMap<String,Object> linkedHashMap = new LinkedHashMap<>(16);
		linkedHashMap.put("key1", "value1");
		linkedHashMap.put("key2", "value2");
		linkedHashMap.put("key3", "value3");
		linkedHashMap.put("key4", 4);
		linkedHashMap.put("key5", 5);
		linkedHashMap.put("key5", 'h');
		linkedHashMap.keySet().forEach(key -> System.out.println("map.get(" + key + ") = " + linkedHashMap.get(key)));
		linkedHashMap.entrySet().iterator().forEachRemaining(item -> System.out.println("key:value=" + item.getKey() + ":" + item.getValue()));
		linkedHashMap.entrySet().forEach(entry -> System.out.println("key:value = " + entry.getKey() + ":" + entry.getValue()));
	}
	
	/**
	 * TreeMap能够把它保存的记录根据键排序，默认是按升序排序，也可以指定排序的比较器。当用Iteraor遍历TreeMap时，得到的记录是排过序的。TreeMap的键和值都不能为空。
	 */
	public static void testTreeMap() {
		TreeMap<String,Object> treeMap = new TreeMap<>();
		treeMap.put("key0", "value0");
		treeMap.put("key1", "value1");
		treeMap.put("aey1", "value1");
		treeMap.put("key2", "value2");
		treeMap.put("key3", "value3");
		treeMap.put("key4", 4);
		treeMap.put("key5", 5);
		treeMap.put("key5", 'h');
		treeMap.put("abc", null);
		treeMap.keySet().forEach(key -> System.out.println("map.get(" + key + ") = " + treeMap.get(key)));
		treeMap.entrySet().iterator().forEachRemaining(item -> System.out.println("key:value=" + item.getKey() + ":" + item.getValue()));
		treeMap.entrySet().forEach(entry -> System.out.println("key:value = " + entry.getKey() + ":" + entry.getValue()));
	}
	
	public static void main(String[] args) {
		testTreeMap();
	}
}
