package com.youmu.mall.news.domain;


/**
 * 今日头条对象
 * @author sy
 *
 */
public class News{
	
	/** 头条id */
	private int id;
	
	/** 头条标题  */
	private String title;
	
	/** 头条链接  */
	private String link;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", link=" + link + "]";
	}

	public News(int id, String title, String link) {
		super();
		this.id = id;
		this.title = title;
		this.link = link;
	}

	public News() {
		super();
	}
	
	

}
