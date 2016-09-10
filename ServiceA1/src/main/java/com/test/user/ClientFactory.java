package com.test.user;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.user.service.UserService;

public class ClientFactory {

    public static UserService getClient() {
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "client-beans.xml" });
	UserService client = (UserService) context.getBean("userService");
	return client;
    }

    public static UserService getRemoteClient() {
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "client-beans-remote.xml" });
	UserService client = (UserService) context.getBean("userService");
	return client;
    }

}
