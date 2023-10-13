package com.laptrinhjavaweb.service;

import java.util.ArrayList;

import com.laptrinhjavaweb.model.newsModel;
import com.laptrinhjavaweb.paging.Pageble;

public interface INewService {
	ArrayList<newsModel> addByCategoryId(long categoryId);
	newsModel save(newsModel newModel);
	newsModel update(newsModel updateNew);
	void delete(long [] ids);
	ArrayList<newsModel> listNew(Pageble pageble);
	int getTotalItem();
	newsModel findOne(Long id);

}
