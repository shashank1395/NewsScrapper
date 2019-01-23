package com.news.webscraping.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.news.webscraping.model.NewScrapperItem;

@Service("newScrapperService")
public class NewScrapperServiceImpl implements NewScrapperService {
	private static List<NewScrapperItem> articles = new ArrayList<>();

	@Override
	public void getArticles() {
		List<String> articlesUrlList = new ArrayList<>();
		AtomicInteger count = new AtomicInteger(0);
		List<Element> eleList = new ArrayList<>();
		try {
			Document doc = Jsoup.connect("https://www.thehindu.com/archive/").get();
			Element element = doc.getElementById("archiveWebContainer");
			Elements elements = element.getElementsByAttribute("href");
			eleList = elements;
			eleList.stream().filter(predicate -> count.get() < 1).forEach(ele -> {
				count.incrementAndGet();
				String link = ele.absUrl("href");
				try {
					Document monthDoc = Jsoup.connect(link).get();
					monthDoc.getElementsByClass("ui-state-default").forEach(month -> {
						String daysLink = month.absUrl("href");
						try {
							Document articleDoc = Jsoup.connect(daysLink).get();
							articleDoc.getElementsByClass("archive-list").forEach(article -> {
								Elements arcEle = article.getElementsByTag("a");
								arcEle.forEach(arc -> {
									String arcLink = arc.absUrl("href");
									articlesUrlList.add(arcLink);
								});
							});
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		count.set(0);
		Set<String> urlSet = new HashSet<>(articlesUrlList);
		List<String> uniqueUrlList = new ArrayList<>(urlSet);
		uniqueUrlList.stream().filter(predicate -> count.get() < 100).forEach(arcLink -> {
			count.incrementAndGet();
			try {
				Document articleDocument = Jsoup.connect(arcLink).get();
				String title = articleDocument.getElementsByClass("title").get(0).text();
				String author = articleDocument.getElementsByClass("author-img-name 1").text();
				String url = arcLink;
				if (!author.isEmpty() && !title.isEmpty()) {
					NewScrapperItem item = new NewScrapperItem(title, url, author, "");
					articles.add(item);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	@Override
	public List<String> getAuthors() {
		List<String> availableAuthorsList = articles.stream().map(action -> action.getAuthor())
				.collect(Collectors.toList());
		Set<String> availableAuthorsSet = new HashSet<>(availableAuthorsList);
		return new ArrayList<>(availableAuthorsSet);
	}

	@Override
	public List<NewScrapperItem> searchAuthors(String author) {
		List<NewScrapperItem> titleList = articles.stream().filter(predicate -> predicate.getAuthor().equals(author))
				.collect(Collectors.toList());
		return titleList;
	}

}
