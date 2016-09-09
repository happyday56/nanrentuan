package com.lgh.nanrentuan.model;

public class Paging {
	/**
	 * 开始
	 */
	private int begin;
	/**
	 * 结束
	 */
	private int end;
	/**
	 * 当前页码
	 */
	private int current;
	/**
	 * 总页码
	 */
	private int total;
	/**
	 * 每页记录数
	 */
	private int length;
	/**
	 * 总记录数
	 */
	private long count;

	/**
	 * 开始
	 */
	public int getBegin() {
		return begin;
	}

	/**
	 * 开始
	 */
	public void setBegin(int begin) {
		this.begin = begin;
	}

	/**
	 * 结束
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * 结束
	 */
	public void setEnd(int end) {
		this.end = end;
	}

	/**
	 * 当前页码
	 */
	public int getCurrent() {
		return current;
	}

	/**
	 * 当前页码
	 */
	public void setCurrent(int current) {
		this.current = current;
	}

	/**
	 * 总页码
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * 总页码
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * 总页码
	 */
	public int getLength() {
		return length;
	}

	/**
	 * 总页码
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * 总记录数
	 */
	public long getCount() {
		return count;
	}

	/**
	 * 总记录数
	 */
	public void setCount(long count) {
		this.count = count;
	}

}