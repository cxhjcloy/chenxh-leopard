package cn.chenxhcloud.multithread.threadexecutor;

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
