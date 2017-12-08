package cn.chenxhcloud.models.common;

import java.util.Date;

public class Greeting {

    private final long id;
    private final String content;
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
