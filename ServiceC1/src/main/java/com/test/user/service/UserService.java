package com.test.user.service;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.test.user.domain.User;

@WebService
@SOAPBinding(style = Style.RPC)
public interface UserService {

    String ping();

    User getUser(String id);

    void updateUser(User user);

    void addUser(User user);
}
