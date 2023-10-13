package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.model.userModel;

public interface IUserService {
	userModel findUserByUserNameAndPasswordAndStatus(String userName, String passWord, int status);
}
