package com.test.user.ws;

import javax.annotation.PostConstruct;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.test.user.domain.User;
import com.test.user.service.UserService;

@Component("userServiceWS")
@WebService(serviceName = "User", portName = "UserPort")
@SOAPBinding(style = Style.RPC, use = Use.LITERAL)
public class UserServiceWS implements UserService {

    @Autowired
    @Qualifier("userServivceImpl")
    private UserService service;

    @WebMethod
    public String ping() {
	return service.ping();
    }

    @WebMethod
    public User getUser(String id) {
	return service.getUser(id);
    }

    @WebMethod
    public void updateUser(User user) {
	service.updateUser(user);
    }

    @WebMethod
    public void addUser(User user) {
	service.addUser(user);
    }

    @PostConstruct
    public void init() {
	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
}
