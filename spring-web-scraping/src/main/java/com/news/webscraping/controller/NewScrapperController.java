package com.news.webscraping.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.news.webscraping.model.NewScrapperItem;
import com.news.webscraping.service.NewScrapperService;

@Controller
@RequestMapping("/webscraping")
public class NewScrapperController {
	private final Logger logger = LoggerFactory.getLogger(NewScrapperController.class);

	@Resource(name = "newScrapperService")
	private NewScrapperService service;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		logger.debug("index()");
		service.getArticles();
		model.addAttribute("attr1", new NewScrapperItem());
		model.addAttribute("attr2", new NewScrapperItem());
		model.addAttribute("attr3", new NewScrapperItem());
		return "main";
	}

	@RequestMapping(value = "/getAuthors", method = RequestMethod.GET)
	public String getAuthors(Model model) {
		model.addAttribute("authorsList", service.getAuthors());
		return "all-authors";
	}
	
	@RequestMapping(value = "/searchAuthors", method = RequestMethod.POST)
	public String searchAuthors(@ModelAttribute("attr2") NewScrapperItem item, Model model) {
		model.addAttribute("titleList", service.searchAuthors(item.getAuthor()));
		return "title";
	}
}
