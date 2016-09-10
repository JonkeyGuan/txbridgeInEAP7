package com.test.user.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.test.user.domain.User;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
public interface UserService {

    @WebMethod
    public String ping();

    @WebMethod
    public User getUser(String id);

    @WebMethod
    public void updateUser(User user);

    @WebMethod
    public void addUser(User user);

}
