package cn.chenxhcloud.batch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import cn.chenxhcloud.models.bacth.Person;

/**
 * 
*   
* 项目名称：chenxh-batch  
* 类名称：cn.chenxhcloud.batch.PersonItemProcessor  
* @author：chenxh  
* 创建时间：2017年12月13日 下午3:24:10
* 描述：A common paradigm in batch processing is to ingest data, transform it, 
* and then pipe it out somewhere else. 
* Here you write a simple transformer that converts the names to uppercase
*
 */
public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

    @Override
    public Person process(final Person person) throws Exception {
        final String firstName = person.getFirstName().toUpperCase();
        final String lastName = person.getLastName().toUpperCase();
        final Person transformedPerson = new Person(firstName, lastName);
        log.info("Converting (" + person + ") into (" + transformedPerson + ")");
        return transformedPerson;
    }
}