package com.laptrinhjavaweb.service.impl;

import java.sql.Date;
import java.util.ArrayList;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.model.categoryModel;
import com.laptrinhjavaweb.model.newsModel;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.utils.SessionUtil;

public class NewService implements INewService{
	
	@Inject 
	private INewDAO newDao;
	@Inject
	private ICategoryDAO categoryDao;
//	public NewService() {
//		newDao.getTotalItem();
//	}
	
	@Override
	public ArrayList<newsModel> addByCategoryId(long categoryId) {
		// TODO Auto-generated method stub
		return newDao.addByCategoryId(categoryId);
	}

	@Override
	public newsModel save(newsModel newModel) {
		newModel.setCreatedDate(new Date(System.currentTimeMillis()));
		categoryModel category = categoryDao.findOneByCategoryCode(newModel.getCategoryCode());
		newModel.setCategoryId(category.getId());
		Long id = newDao.save(newModel);
		return newDao.findOne(id);
	}

	@Override
	public newsModel update(newsModel updateNew) {
		// TODO Auto-generated method stub
		newsModel oldNew = newDao.findOne(updateNew.getId());
		categoryModel category = categoryDao.findOneByCategoryCode(updateNew.getCategoryCode());
		updateNew.setCategoryId(category.getId());
		updateNew.setCreatedBy(oldNew.getCreatedBy());
		updateNew.setCreatedDate(oldNew.getCreatedDate());
		updateNew.setModifiedDate(new Date(System.currentTimeMillis()));
		newDao.update(updateNew);
		return newDao.findOne(updateNew.getId());
	}

	@Override
	public void delete(long[] ids) {
		// TODO Auto-generated method stub
		for(long id : ids) {
			newDao.delete(id);
		}
	}

	@Override
	public ArrayList<newsModel> listNew(Pageble pageble) {
		return newDao.listNews(pageble);
	}

	@Override
	public int getTotalItem() {
		return newDao.getTotalItem();
	}

	@Override
	public newsModel findOne(Long id) {
		newsModel news = newDao.findOne(id);
		categoryModel category = categoryDao.findOne(news.getCategoryId());
		news.setCategoryCode(category.getCode());
		return news;
	}

	
}
