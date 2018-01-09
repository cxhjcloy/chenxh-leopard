#Lambda简介

Lambda作为函数式编程中的基础部分，在其他编程语言（例如：Scala）中早就广为使用，但在Java领域中发展较慢，直到java8，才开始支持Lambda。


抛开数学定义不看，直接来认识Lambda。Lambda表达式本质上是匿名方法，其底层还是通过invokedynamic指令来生成匿名类来实现。它提供了更为简单的语法和写作方式，允许你通过表达式来代替函数式接口。在一些人看来，Lambda就是可以让你的代码变得更简洁，完全可以不使用——这种看法当然没问题，但重要的是lambda为Java带来了闭包。得益于Lamdba对集合的支持，通过Lambda在多核处理器条件下对集合遍历时的性能提高极大，另外我们可以以数据流的方式处理集合——这是非常有吸引力的。


##Lambda语法

Lambda的语法极为简单，类似如下结构:
	(parameters) -> expression
或者
	(parameters) -> { statements;}


Lambda表达式由三部分组成：

* paramaters：类似方法中的形参列表，这里的参数是函数式接口里的参数。这里的参数类型可以明确的声明也可不声明而由JVM隐含的推断。另外当只有一个推断类型时可以省略掉圆括号。


* ->：可理解为“被用于”的意思


* 方法体：可以是表达式也可以代码块，是函数式接口里方法的实现。代码块可返回一个值或者什么都不反回，这里的代码块块等同于方法的方法体。如果是表达式，也可以返回一个值或者什么都不反回。


我们通过以下几个示例来做说明：

	// 示例1：不需要接收参数，直接返回10
	() ->10
	
	//示例2: 接收两个int类型的参数，并返回这两个参数相加的和
	(int x, int y) -> x+y;
	
	//示例3： 接收一个字符串，并将该字符串打印到控制台，不返回结果
	(String name) -> System.out.println(name);
	
	//示例4：接收一个推断类型的参数name,并将该字符串打印到控制台
	name -> System.out.println(name);
	
	//示例5：接收两个String类型参数，并分别输出，不返回
	(String name, String sex) -> {System.out.println(name);System.out.println(sex);}
	
	//示例6: 接收一个参数x,并返回该参数的两倍
	x->2*x

##Lambda用在哪里

在\[函数式接口\]\[1\]中我们知道Lambda表达式的目标类型是函数性接口——每一个Lambda都能通过一个特定的函数式接口与一个给定的类型进行匹配。因此一个Lambda表达式能被应用在与其目标类型匹配的任何地方，lambda表达式必须和函数式接口的抽象函数描述一样的参数类型，它的返回类型也必须和抽象函数的返回类型兼容，并且他能抛出的异常也仅限于在函数的描述范围中。


接下来，我们看一个自定义的函数式接口示例：
	
```	
	@FunctionalInterface
	interface Converter<F,T>{
		T convert(F from);
	}
```

首先用传统的方式来使用该接口：

```	
	Converter<String,Integer> converter = new Converter<String,Integer>(){
		@Override
		public Integer convert(String from){
			return Integer.valueOf(from);
		}	
	};
	
	Integer result = converter.convert("200");
	System.out.println(result);
```

很显然这没任何问题，那么接下里就是Lambda上场的时刻，用Lambda实现Converter接口：

```
	Converter<String,Integer> converter = (param) -> Integer.valueOf(param);
	Integer result = converter.convert("1001");
	System.out.println(result);
```

通过上例，我想你已经对Lambda的使用有了个简单的认识，下面，我们在用一个常用的Runnable做演示


在以前我们可能会写下这种代码：


```
	new Thread(new Runnale(){
		@Override
		public void run(){
			System.out.println("hello lambda")
		}
	}).start;
```

在某些情况下，大量的匿名类会让代码显得杂乱无章。现在可以用Lambda来使它变得简洁：

```
	new Thread(()->System.out.println("hello lambda")).start;
```


##方法引用

方法引用是Lambda表达式的一个简化写法。所引用的方法其实是Lambda表达式的方法体的实现，其语法结构为:



左边可以是类名或者实例名，中间是方法引用符号”::”，右边是相应的方法名。方法引用被分为三类：


1. 静态方法引用

在某些情况下，我们可能写出这样的代码：



这时候如果用静态引用会使的代码更加简洁：



2. 实例方法引用

我们也可能会写下这样的代码：



同样用实例方法引用会显得更加简洁：



3. 构造方法引用

现在我们来演示构造方法的引用。首先我们定义一个父类Animal:



接下来，我们在定义两个Animal的子类：Dog、Bird



随后我们定义工厂接口：



接下来我们还是用传统的方法来创建Dog类和Bird类的对象：



仅仅为了创建两个对象就写了十多号代码，现在我们用构造函数引用试试：



这样代码就显得干净利落了。通过Dog::new这种方式来穿件对象时，Factory.create函数的签名选择相应的造函数。


Lambda的域以及访问限制

域即作用域，Lambda表达式中的参数列表中的参数在该Lambda表达式范围内（域）有效。在作用Lambda表达式内，可以访问外部的变量：局部变量、类变量和静态变量，但操作受限程度不一。


访问局部变量

在Lambda表达式外部的局部变量会被JVM隐式的编译成final类型，因此只能访问外而不能修改。



访问静态变量和成员变量

在Lambda表达式内部，对静态变量和成员变量可读可写。



Lambda不能访问函数接口的默认方法

java8增强了接口，其中包括接口可添加default关键词定义的默认方法，这里我们需要注意，Lambda表达式内部不支持访问默认方法。


Lambda实践

在\[函数式接口\]\[2\]一节中，我们提到java.util.function包中内置许多函数式接口，现在将对常用的函数式接口做说明。


Predicate接口

输入一个参数，并返回一个Boolean值，其中内置许多用于逻辑判断的默认方法:



Function接口

接收一个参数，返回单一的结果，默认的方法（andThen）可将多个函数串在一起，形成复合Funtion（有输入，有输出）结果，



Supplier接口

返回一个给定类型的结果，与Function不同的是，Supplier不需要接受参数(供应者，有输出无输入)



Consumer接口

代表了在单一的输入参数上需要进行的操作。和Function不同的是，Consumer没有返回值(消费者，有输入，无输出)



以上四个接口的用法代表了java.util.function包中四种类型，理解这四个函数式接口之后，其他的接口也就容易理解了，现在我们来做一下简单的总结：


Predicate用来逻辑判断，Function用在有输入有输出的地方，Supplier用在无输入，有输出的地方，而Consumer用在有输入，无输出的地方。你大可通过其名称的含义来获知其使用场景。


Stream

Lambda为java8带了闭包，这一特性在集合操作中尤为重要：java8中支持对集合对象的stream进行函数式操作，此外，stream api也被集成进了collection api，允许对集合对象进行批量操作。


下面我们来认识Stream。


Stream表示数据流，它没有数据结构，本身也不存储元素，其操作也不会改变源Stream，而是生成新Stream.作为一种操作数据的接口，它提供了过滤、排序、映射、规约等多种操作方法，这些方法按照返回类型被分为两类：凡是返回Stream类型的方法，称之为中间方法（中间操作），其余的都是完结方法（完结操作）。完结方法返回一个某种类型的值，而中间方法则返回新的Stream。中间方法的调用通常是链式的，该过程会形成一个管道，当完结方法被调用时会导致立即从管道中消费值，这里我们要记住：Stream的操作尽可能以“延迟”的方式运行，也就是我们常说的“懒操作”，这样有助于减少资源占用，提高性能。对于所有的中间操作（除sorted外）都是运行在延迟模式下。


Stream不但提供了强大的数据操作能力，更重要的是Stream既支持串行也支持并行，并行使得Stream在多核处理器上有着更好的性能。


Stream的使用过程有着固定的模式：


创建Stream

通过中间操作，对原始Stream进行“变化”并生成新的Stream

使用完结操作，生成最终结果
也就是


创建——>变化——>完结

Stream的创建

对于集合来说，可以通过调用集合的stream()或者parallelStream()来创建，另外这两个方法也在Collection接口中实现了。对于数组来说，可以通过Stream的静态方法of(T … values)来创建，另外，Arrays也提供了有关stream的支持。


除了以上基于集合或者数组来创建Stream,也可以通过Steam.empty()创建空的Stream,或者利用Stream的generate（）来创建无穷的Stream。


下面我们以串行Stream为例，分别说明Stream几种常用的中间方法和完结方法。首先创建一个List集合：



中间方法

过滤器（Filter）

结合Predicate接口，Filter对流对象中的所有元素进行过滤,该操作是一个中间操作，这意味着你可以在操作返回结果的基础上进行其他操作。



排序（Sorted）

结合Comparator接口，该操作返回一个排序过后的流的视图，原始流的顺序不会改变。通过Comparator来指定排序规则，默认是按照自然顺序排序。



映射（Map）

结合Function接口，该操作能将流对象中的每个元素映射为另一种元素，实现元素类型的转换。



在上面简单介绍了三种常用的操作，这三种操作极大简化了集合的处理。接下来，介绍几种完结方法：


完结方法

“变换”过程之后，需要获取结果，即完成操作。下面我们来看相关的操作：


匹配（Match）

用来判断某个predicate是否和流对象相匹配，最终返回Boolean类型结果，例如：



收集（Collect）

在对经过变换之后，我们将变换的Stream的元素收集，比如将这些元素存至集合中，此时便可以使用Stream提供的collect方法，例如：



计数（Count）

类似sql的count，用来统计流中元素的总数，例如：



规约（Reduce）

reduce方法允许我们用自己的方式去计算元素或者将一个Stream中的元素以某种规律关联，例如：



执行结果如下：



并行Stream VS 串行Stream

到目前我们已经将常用的中间操作和完结操作介绍完了。当然所有的的示例都是基于串行Stream。接下来介绍重点戏——并行Stream（parallel Stream）。并行Stream基于Fork-join并行分解框架实现，将大数据集合切分为多个小数据结合交给不同的线程去处理，这样在多核处理情况下，性能会得到很大的提高。这和MapReduce的设计理念一致：大任务化小，小任务再分配到不同的机器执行。只不过这里的小任务是交给不同的处理器。


通过parallelStream（）创建并行Stream。为了验证并行Stream是否真的能提高性能，我们执行以下测试代码：


首先创建一个较大的集合：



测试串行流下排序所用的时间：



测试并行流下排序所用的时间：



结果如下：


串行排序: 13336 ms
并行排序: 6755 ms

看到这里，我们确实发现性能提高了约么50%，你也可能会想以后都用parallel Stream不久行了么？实则不然，如果你现在还是单核处理器，而数据量又不算很大的情况下，串行流仍然是这种不错的选择。你也会发现在某些情况，串行流的性能反而更好，至于具体的使用，需要你根据实际场景先测试后再决定。


懒操作

上面我们谈到Stream尽可能以延迟的方式运行，这里通过创建一个无穷大的Stream来说明：


首先通过Stream的generate方法来一个自然数序列，然后通过map变换Stream：



执行结果为：


元素个数：1000

我们发现开始时对这个无穷大的Stream做任何中间操作（如：filter,map等，但sorted不行）都是可以的，也就是对Stream进行中间操作并生存一个新的Stream的过程并非立刻生效的（不然此例中的map操作会永远的运行下去，被阻塞住），当遇到完结方法时stream才开始计算。通过limit()方法，把这个无穷的Stream转为有穷的Stream。