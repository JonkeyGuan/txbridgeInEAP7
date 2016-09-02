package com.test.user.service;

import javax.annotation.PostConstruct;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.test.user.ClientFactory;
import com.test.user.domain.User;
import com.test.user.mapper.UserMapper;

@WebService(portName = "UserPort", serviceName = "UserService", endpointInterface = "com.test.user.service.UserService")
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    protected UserMapper userMapper;

    public String ping() {
	return "Hi there!";
    }

    @Transactional(readOnly = true)
    public User getUser(String id) {
	return userMapper.findByID(id);
	// return new User("aaa", "bb");
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateUser(User user) {
	log.info("updating local user ...");
	userMapper.updateByID(user);
	log.info("updated");

	// for the dups to rollback
	// userMapper.add(user);

	 log.info("updating remote user ...");
	 ClientFactory.getRemoteClient().updateUser(user);
	 log.info("updated");
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addUser(User user) {
	log.info("adding local user ...");
	userMapper.add(user);
	log.info("added");

	log.info("adding remote user ...");
	ClientFactory.getRemoteClient().addUser(user);
	log.info("added");
    }

    @PostConstruct
    public void init() {
	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

}