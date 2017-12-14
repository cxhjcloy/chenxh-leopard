package cn.chenxhcloud.batch.demo;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;

/**
 * 
*   
* 项目名称：chenxh-batch  
* 类名称：cn.chenxhcloud.batch.demo.DemoProcessor  
* @author：chenxh  
* 创建时间：2017年12月14日 下午2:05:45
* 描述：
*
 */
public class DemoProcessor extends ValidatingItemProcessor<Object>  implements ItemProcessor<Object, Object>{

    @Override
    public Object process(Object obj) {
    	super.process(obj);
        return obj;
    }
}
