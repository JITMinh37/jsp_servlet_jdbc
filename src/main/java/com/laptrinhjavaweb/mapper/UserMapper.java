package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.newsModel;
import com.laptrinhjavaweb.model.roleModel;
import com.laptrinhjavaweb.model.userModel;

public class UserMapper implements RowMapper<userModel>{

	@Override
	public userModel mapRow(ResultSet rs) {
		userModel user = new userModel();
		try {
			user.setId(rs.getLong("id"));
			user.setUserName(rs.getString("username"));
			user.setPassWord(rs.getString("password"));
			user.setFullName(rs.getString("fullname"));
			user.setStatus(rs.getInt("status"));
			roleModel role = new roleModel();
			role.setName(rs.getString("name"));
			role.setCode(rs.getString("code"));
			user.setRole(role);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return user;
	}
	
}
