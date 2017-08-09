package com.youmu.mall.news.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.youmu.mall.news.dao.NewsDao;
import com.youmu.mall.news.domain.News;
import com.youmu.mall.news.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService{

	@Resource
	private NewsDao newsDao;
	
	/**
	 * 查询最近4条新闻
	 */
	@Override
	public List<News> getNews() {
		List<News> listnews=newsDao.getNews();
		return listnews;
	}
	
	/**
	 * 根据id获取新闻
	 */
	@Override
	public News getNewsById(int id) {
		News news=newsDao.getNewsById(id);
		return news;
	}
	
}
