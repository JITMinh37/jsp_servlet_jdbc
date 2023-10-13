package com.laptrinhjavaweb.dao;

import com.laptrinhjavaweb.model.userModel;

public interface IUserDAO extends GenericDAO<userModel>{
	userModel findUserByUserNameAndPasswordAndStatus(String userName, String passWord, int status);
}
