package com.laptrinhjavaweb.dao.impl;

import java.util.ArrayList;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.mapper.CategoryMapper;
import com.laptrinhjavaweb.mapper.NewMapper;
import com.laptrinhjavaweb.model.categoryModel;
import com.laptrinhjavaweb.model.newsModel;

public class CategoryDAO extends AbstractDAO<categoryModel> implements ICategoryDAO{
	
	@Override
	public ArrayList<categoryModel> findListCategory() {
		String sql = "Select * from category";
		return query(sql, new CategoryMapper());
	}

	@Override
	public categoryModel findOne(Long id) {
		ArrayList<categoryModel> listCategoryModel = new ArrayList<>();
		String sql = "Select * from category where id = ?";
		listCategoryModel = query(sql, new CategoryMapper(), id);
		return listCategoryModel.isEmpty() ? null : listCategoryModel.get(0);
	}

	@Override
	public categoryModel findOneByCategoryCode(String code) {
		ArrayList<categoryModel> listCategoryModel = new ArrayList<>();
		String sql = "Select * from category where code = ?";
		listCategoryModel = query(sql, new CategoryMapper(), code);
		return listCategoryModel.isEmpty() ? null : listCategoryModel.get(0);
	}
}
