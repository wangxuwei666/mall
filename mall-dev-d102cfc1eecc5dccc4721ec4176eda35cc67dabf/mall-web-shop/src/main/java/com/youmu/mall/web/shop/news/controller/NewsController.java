package com.youmu.mall.web.shop.news.controller;


import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.mall.news.domain.News;
import com.youmu.mall.news.service.NewsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 今日头条控制层
 * @author Mr.S
 *
 */
@RestController
@RequestMapping("/News/")
public class NewsController {
	
	@Resource
	private NewsService newsService;
	
	@RequestMapping(value="getNews",method= RequestMethod.POST)
	public JsonResult getNews(){
		List<News> listnews=newsService.getNews();
		return JsonResultUtils.ok(listnews);
	}
	
	@RequestMapping(value="getNewsById",method=RequestMethod.GET)
	public JsonResult getNewsById(int id){
		return JsonResultUtils.ok(newsService.getNewsById(id));
	}
}
