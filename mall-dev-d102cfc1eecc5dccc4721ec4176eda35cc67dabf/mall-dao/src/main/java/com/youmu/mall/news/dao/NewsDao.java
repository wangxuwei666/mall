package com.youmu.mall.news.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.youmu.mall.news.domain.News;

/**
 * 今日头条
 * @author Mr.S
 *
 */
public interface NewsDao {

	/**
	 * 获取最近4条新闻
	 * @param num 获取数量
	 * @return
	 */
	List<News> getNews();
	
	/**
	 * 根据id获取新闻
	 * @param id
	 * @return
	 */
	News getNewsById(@Param("id")int id);
}
