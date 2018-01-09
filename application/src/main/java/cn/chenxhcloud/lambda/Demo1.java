package cn.chenxhcloud.lambda;

public class Demo1 {
	public static void main(String[] args) {
		
		Converter<String, Integer> converter = new Converter<String, Integer>() {
			@Override
			public Integer convert(String from) {
				return Integer.valueOf(from);
			}
		};
		
		System.out.println(converter.convert("100"));
		Converter<String, Integer> con = param -> Integer.valueOf(param);
		System.out.println(con.convert("101"));
	}
}

@FunctionalInterface
interface Converter<F, T> {
	T convert(F from);
}