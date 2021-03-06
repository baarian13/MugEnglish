package com.linnca.pelicann.connectors;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WikiDataAPISearchConnector extends WikiBaseEndpointConnector {
	public static final String ENTITY_TAG = "entity";
	/*
	 * 日本語用
	 * name in kana を利用して、お気に入りを見る時に
	 * できるだけ、あいうえお順になるようにする
	 */
	
	public WikiDataAPISearchConnector(){
		super();
	}
	
	public WikiDataAPISearchConnector(String language){
		super(language);
	}

	//we want both the search query and the number of results to search
	//so we format the string like
	// number|query
	protected String formatURL(String... parameterValue) throws Exception{
		String encodedSearchText = URLEncoder.encode(parameterValue[0], StandardCharsets.UTF_8.name());
		int numResults = Integer.parseInt(parameterValue[1]);
		//default search limit is 7
		String url = "https://wikidata.org/w/api.php?action=wbsearchentities&format=xml" +
			"&uselang=" + WikiBaseEndpointConnector.LANGUAGE_PLACEHOLDER + //so the description will be in JP
			"&search=" + encodedSearchText +
			"&language=" + WikiBaseEndpointConnector.LANGUAGE_PLACEHOLDER +
			"&limit=" + numResults;
			//"&utf8=1";//the description will be in encodeed unicode otherwise. only for json
		url = super.formatRequestLanguage(url);
		return url;
	}
}
