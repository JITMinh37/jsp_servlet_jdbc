package com.laptrinhjavaweb.model;

public class newsModel extends abstractModel<newsModel>{
	private String title;
	private String thumbnail;
	private String shortdescription;
	private String content;
	private long categoryId;
	private long [] ids;
	private String categoryCode;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getShortDescription() {
		return shortdescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortdescription = shortDescription;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public long [] getIds() {
		return ids;
	}
	public void setIds(long [] ids) {
		this.ids = ids;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
}
