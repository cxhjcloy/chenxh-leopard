package cn.chenxhcloud.batch.person;

import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

import cn.chenxhcloud.models.bacth.Person;


/**
 * 
*   
* 项目名称：chenxh-batch  
* 类名称：cn.chenxhcloud.batch.person.PersonProcessor  
* @author：chenxh  
* 创建时间：2017年12月14日 下午5:27:31
* 描述：
*
 */
public class PersonProcessor  extends ValidatingItemProcessor<Person>{
	private static final String NATION ="汉族";
	
    @Override
    public Person process(Person item) throws ValidationException {
    	//需要执行super.process(item)才会调用自定义校验器
        super.process(item); 
        //对数据做简单的处理，若民族为汉族，则数据转换成01，其余转换成02
        if(NATION.equals(item.getNation())){ 
            item.setNation("01");
        }else{
            item.setNation("02");
        }
        return item;
    }
}