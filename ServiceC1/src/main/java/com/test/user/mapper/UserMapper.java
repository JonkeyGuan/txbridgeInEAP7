package com.test.user.mapper;

import com.test.user.domain.User;

public interface UserMapper {

	User findByID(String id);

	void updateByID(User user);
	
	void add(User user);

}
