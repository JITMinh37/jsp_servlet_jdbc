package com.laptrinhjavaweb.dao;

import java.util.ArrayList;

import com.laptrinhjavaweb.model.categoryModel;

public interface ICategoryDAO extends GenericDAO<categoryModel>{
	ArrayList<categoryModel> findListCategory();
	categoryModel findOne(Long id);
	categoryModel findOneByCategoryCode(String code);
}
