package cn.chenxhcloud.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * 
 * 项目名称：chenxh-app 
 * 类名称：cn.chenxhcloud.configs.Swagger2Config
 * @author : chenxh 
 * 创建时间：2017年12月12日 下午5:03:13 
 * 描述：配置SwaggerAPI文档
 *
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Value("${spring.profiles.active}")
	private String env;

	/**
	 * prod环境中不展示API的swagger文档内容
	 * @return
	 */
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).paths("prod".equals(env)?PathSelectors.none():PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("API接口").description("Leopard")
				.contact(new Contact("chenxh", "https://www.chenxhcloud.cn", "cxh_jcloy@163.com")).version("1.0.0")
				.build();
	}

}
