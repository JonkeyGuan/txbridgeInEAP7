package com.test.user.service;

import org.jboss.logging.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.test.user.ClientFactory;
import com.test.user.domain.User;
import com.test.user.mapper.UserMapper;

public class UserServiceImpl implements UserService {

    private static final Logger log = Logger.getLogger(UserServiceImpl.class);

    protected UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
	this.userMapper = userMapper;
    }

    public String ping() {
	return "Hi there!";
    }

    @Transactional(readOnly = true)
    public User getUser(String id) {
	return userMapper.findByID(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateUser(User user) {
	log.info("updating local user ...");
	userMapper.updateByID(user);
	log.info("updated");

	// for the dups to rollback
	userMapper.add(user);

//	log.info("updating remote user ...");
//	ClientFactory.getRemoteClient().updateUser(user);
//	log.info("updated");
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addUser(User user) {
	log.info("adding local user ...");
	userMapper.add(user);
	log.info("added");

//	log.info("adding remote user ...");
//	ClientFactory.getRemoteClient().addUser(user);
//	log.info("added");
    }

}
