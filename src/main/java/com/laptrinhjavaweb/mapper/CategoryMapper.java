package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.categoryModel;

public class CategoryMapper implements RowMapper<categoryModel>{

	@Override
	public categoryModel mapRow(ResultSet rs) {
		categoryModel category = new categoryModel();
		try {
			category.setId(rs.getLong("id"));
			category.setName(rs.getString("name"));
			category.setCode(rs.getString("code"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return category;
	}
}
