package cn.chenxhcloud.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;

import cn.chenxhcloud.models.bacth.Person;

public class MyItemProcessor extends ValidatingItemProcessor<Person>  implements ItemProcessor<Person, Person>{

    @Override
    public Person process(Person person) {
    	super.process(person);
        String firstName = person.getFirstName().toUpperCase();
        person.setFirstName(firstName);
        return person;
    }
}
