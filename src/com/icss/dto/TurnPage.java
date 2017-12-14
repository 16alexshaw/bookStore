package com.icss.dto;

public class TurnPage {
	public int pageno=1;         //当前页的页号,入参         (默认值1)
	public int rows=10;          //每页显示几条记录，入参  (默认值为10)
	public int allRows;          //总记录数，返回值
	public int allPages;         //总页数,返回值
}