package cn.chenxhcloud.batch.validators;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.InitializingBean;

/**
 * 
*   
* 项目名称：chenxh-batch  
* 类名称：cn.chenxhcloud.batch.validators.CsvBeanValidator  
* @author：chenxh  
* 创建时间：2017年12月14日 下午4:41:30
* 描述：
*
 */
public class CsvBeanValidator<T> implements Validator<T>, InitializingBean {
	
	private javax.validation.Validator validator;

	@Override
	public void afterPropertiesSet() throws Exception { // 使用JSR-303的Validator来校验我们的数据，在此处进行JSR-303的Validator的初始化
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.usingContext().getValidator();
	}

	@Override
	public void validate(T value) throws ValidationException {
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(value); // 使用Validator的validate方法校验数据
		if (constraintViolations.size() > 0) {

			StringBuilder message = new StringBuilder();
			for (ConstraintViolation<T> constraintViolation : constraintViolations) {
				message.append(constraintViolation.getMessage() + "\n");
			}
			throw new ValidationException(message.toString());
		}
	}
}
