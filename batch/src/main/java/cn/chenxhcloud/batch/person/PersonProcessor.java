package cn.chenxhcloud.batch.person;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;

import cn.chenxhcloud.models.bacth.Person;

public class PersonProcessor extends ValidatingItemProcessor<Person>  implements ItemProcessor<Person, Person>{

    @Override
    public Person process(Person person) {
    	super.process(person);
        return person;
    }
}
