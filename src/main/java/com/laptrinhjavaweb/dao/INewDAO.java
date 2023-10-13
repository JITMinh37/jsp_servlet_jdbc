package com.laptrinhjavaweb.dao;

import java.util.ArrayList;

import com.laptrinhjavaweb.model.newsModel;
import com.laptrinhjavaweb.paging.Pageble;

public interface INewDAO extends GenericDAO<newsModel>{
	ArrayList<newsModel> addByCategoryId(long categoryId);
	Long save(newsModel newModel);
	newsModel findOne(Long id);
	void update(newsModel updateNew);
	void delete(long id);
	ArrayList<newsModel> listNews(Pageble pageble);
	int getTotalItem();
}
