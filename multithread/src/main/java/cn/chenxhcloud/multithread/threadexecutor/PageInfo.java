package cn.chenxhcloud.multithread.threadexecutor;


/**
 * 
*   
* 项目名称：chenxh-leopard  
* 类名称：.PageInfo  
* @author：chenxh  
* 创建时间：2017年12月27日 下午6:02:27
* 描述：
*
 */
public class PageInfo {
	private Integer start;
	private Integer end;
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	@Override
	public String toString() {
		return "PageInfo [start=" + start + ", end=" + end + "]";
	}
}
