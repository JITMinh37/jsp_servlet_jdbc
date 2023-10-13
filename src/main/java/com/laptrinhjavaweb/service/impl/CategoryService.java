package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.ICategoryDAO;

import com.laptrinhjavaweb.model.categoryModel;
import com.laptrinhjavaweb.service.ICategoryService;

public class CategoryService implements ICategoryService{
	
	@Inject
	private ICategoryDAO categoryDao;
	
	
	@Override
	public ArrayList<categoryModel> findListCategory() {
		return categoryDao.findListCategory();
	}
}
