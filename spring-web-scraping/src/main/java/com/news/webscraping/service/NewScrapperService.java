package com.news.webscraping.service;

import java.util.List;

import com.news.webscraping.model.NewScrapperItem;

public interface NewScrapperService {

	void getArticles();

	List<String> getAuthors();

	List<NewScrapperItem> searchAuthors(String author);

}
