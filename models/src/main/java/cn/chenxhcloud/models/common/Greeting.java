package cn.chenxhcloud.models.common;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
*   
* 项目名称：chenxh-models  
* 类名称：cn.chenxhcloud.models.common.Greeting  
* @author : chenxh  
* 创建时间：2017年12月12日 下午5:13:34
* 描述：
*
 */
@ApiModel(description="greeting信息")
public class Greeting {
	@ApiModelProperty(value = "id",name="id",example="92")
    private final long id;
	@ApiModelProperty(value = "内容",name="content",example="balabalabala")
    private final String content;
    @ApiModelProperty(value = "时间戳",name="timestamp",example="1513059551799")
    private final Date timestamp;
    
    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
        this.timestamp = new Date();
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

	public Date getTimestamp() {
		return timestamp;
	}    
}
