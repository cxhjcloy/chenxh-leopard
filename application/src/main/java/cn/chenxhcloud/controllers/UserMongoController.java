package cn.chenxhcloud.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.chenxhcloud.aop.Auth;
import cn.chenxhcloud.nosql.mongo.LogService;
import cn.chenxhcloud.nosql.mongo.UserService;
import cn.chenxhcloud.nosql.mongo.entity.LogEntity;
import cn.chenxhcloud.nosql.mongo.entity.UserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "mongo测试", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping("mongo")
@RestController
public class UserMongoController {
	private static final Logger logger = LoggerFactory.getLogger(UserMongoController.class);

	@Autowired
	private UserService userService;
	
	
	@Autowired
	private LogService logService;

	@RequestMapping(value = "add", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "新增user")
	public void addUser(@RequestBody UserEntity user) {
		try {
			userService.saveUser(user);
		} catch (Exception e) {
			logger.error("UserMongoController.addUser 系统异常：" + e.getMessage());
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "update", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "修改user")
	public void updateUser(@RequestBody UserEntity user) {
		try {
			userService.updateUser(user);
		} catch (Exception e) {
			logger.error("UserMongoController.updateUser 系统异常：" + e.getMessage());
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "删除user")
	public void delUser(@ApiParam(name = "id", value = "id", example = "100",required=true) @PathVariable(name = "id") long id) {
		try {
			userService.deleteUserById(id);
		} catch (Exception e) {
			logger.error("UserMongoController.delUser 系统异常：" + e.getMessage());
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "find", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "查找user")
	public UserEntity findUserByUserName(@ApiParam(value = "用户名",required=true, name = "userName", example = "chenxh") @RequestParam(name = "userName") String userName) {
		try {
			return userService.findUserByUserName(userName);
		} catch (Exception e) {
			logger.error("UserMongoController.findUserByUserName 系统异常：" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	@Auth(enable=false,isLogin=true,isLog=true)
	@RequestMapping(value = "findAll", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "查找所有user")
	public List<UserEntity> findAll(HttpServletRequest request) {
		try {
			return userService.findAll();
		} catch (Exception e) {
			logger.error("UserMongoController.findAll 系统异常：" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "findAllLog", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "查找所有log")
	public List<LogEntity> findAllLog() {
		try {
			return logService.findAll();
		} catch (Exception e) {
			logger.error("UserMongoController.findAllLog 系统异常：" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
