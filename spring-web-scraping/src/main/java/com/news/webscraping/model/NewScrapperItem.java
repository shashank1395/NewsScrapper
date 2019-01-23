package com.news.webscraping.model;

public class NewScrapperItem {
	private String title;
	private String url;
	private String author;
	private String description;

	public NewScrapperItem() {
		super();
	}
	public NewScrapperItem(String title, String url, String author, String description) {
		super();
		this.title = title;
		this.url = url;
		this.author = author;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
