package com.youmu.mall.news.service;

import java.util.List;

import com.youmu.mall.news.domain.News;
	/**
	 * 今日头条服务
	 * @author Mr.S
	 *
	 */
public interface NewsService {

	/**
	 * 获取最近四条新闻
	 * @return
	 */
	public List<News> getNews();
	
	/**
	 * 根据Id查询新闻
	 * @param id
	 * @return
	 */
	public News getNewsById(int id);
}
