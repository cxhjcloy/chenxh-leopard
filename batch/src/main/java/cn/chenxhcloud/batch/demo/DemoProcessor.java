package cn.chenxhcloud.batch.demo;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;


public class DemoProcessor extends ValidatingItemProcessor<Object>  implements ItemProcessor<Object, Object>{

    @Override
    public Object process(Object obj) {
    	super.process(obj);
        return obj;
    }
}
