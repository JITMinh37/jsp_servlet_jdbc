package com.laptrinhjavaweb.service.impl;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.IUserDAO;
import com.laptrinhjavaweb.model.userModel;
import com.laptrinhjavaweb.service.IUserService;

public class UserService implements IUserService{
	
	@Inject
	public IUserDAO userDAO;
	
	@Override
	public userModel findUserByUserNameAndPasswordAndStatus(String userName, String passWord, int status) {
		return userDAO.findUserByUserNameAndPasswordAndStatus(userName, passWord, status);
	}
	
}
