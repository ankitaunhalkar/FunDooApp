package com.bridgelabz.fundoonotes.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WebScrapping {

	public static void main(String[] args) throws IOException {
		 String url = "https://www.ndtv.com/india-news/will-you-back-bjp-after-2019-polls-congress-asks-mamata-banerjees-party-1907883";
			String urlregex = "^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+)([).!';/?:,][[:blank:]])?$";
			List<String> listofurl = new ArrayList<String>();
			Pattern p = Pattern.compile(urlregex);

		 Matcher match = p.matcher(url);
		 while(match.find()) {
		        String s = match.group(); 
		        System.out.println("content"+s);
		        listofurl.add(s);
		    }
	        Document doc = Jsoup.connect(url).get();
	        System.out.println(doc.toString().substring(0, 8000));
	        String title = doc.title();

	        System.out.println(title);
//	        
//	        String description = doc.select("meta[name=description").first().attr("content");
//	        System.out.println("Description : " + description);
//	        
	        String urlDescription = url.split("://")[1].split("/")[0];
			System.out.println("des "+urlDescription);

	        String keywords = doc.select("meta[property=og:image]").first().attr("content");
	        System.out.println("Keywords : " + keywords);
	       
	}
}
