package com.test.user.service;

import javax.jws.HandlerChain;
import javax.jws.WebService;

@WebService(portName = "OutbounUserPort", serviceName = "OutboundUserService", endpointInterface = "com.test.user.service.UserService")
@HandlerChain(file = "/jaxws-handlers-outbound.xml")
public class OutboundUserServiceImpl extends UserServiceImpl {

}
