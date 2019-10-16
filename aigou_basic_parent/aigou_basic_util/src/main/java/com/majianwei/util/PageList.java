package com.majianwei.util;

import java.util.ArrayList;
import java.util.List;

/** 封装分页后的对象
 * @author Administrator
 *
 * @param <T>
 */
public class PageList<T> {
	//总条数
	private Long total = 0L;
	//每页的数据
	private List<T> rows = new ArrayList<>();

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}


	public PageList(Long total, List<T> rows) {
		this.total = total;
		this.rows = rows;
	}

	public PageList() {
	}


	@Override
	public String toString() {
		return "PageList{" +
				"total=" + total +
				", rows=" + rows +
				'}';
	}
}
