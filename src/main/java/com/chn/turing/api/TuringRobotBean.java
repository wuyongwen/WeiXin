package com.chn.turing.api;

import java.util.List;

import com.chn.common.StringUtils;

public class TuringRobotBean {
	private int code;
	private String text;
	private String url;
	private List<TuringNews> list;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<TuringNews> getList() {
		return list;
	}
	public void setList(List<TuringNews> list) {
		this.list = list;
	}
	public String getTextInfo(){
		StringBuffer sb = new StringBuffer();
		sb.append(text);
		sb.append(toHtml(url));
		sb.append(getNews(list));
		return sb.toString();
	}
	private String getNews(List<TuringNews> list) {
		if(list!=null){
			StringBuffer sb = new StringBuffer();
			for(TuringNews news: list){
				sb.append(news.getArticle());
				sb.append(news.getSource());
				sb.append(toHtml(news.getDetailurl()));
			}
			return sb.toString();
		}
		return "";
	}
	private String toHtml(String url) {
		if(!StringUtils.isEmpty(url))
			return "<a href='"+url+"'>链接</a>";
		return "";
	}
}
