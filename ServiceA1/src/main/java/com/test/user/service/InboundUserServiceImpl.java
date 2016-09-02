package com.test.user.service;

import javax.jws.HandlerChain;
import javax.jws.WebService;

@WebService(portName = "inboudUserPort", serviceName = "inboundUserService", endpointInterface = "com.test.user.service.UserService")
@HandlerChain(file = "/jaxws-handlers-inbound.xml")
public class InboundUserServiceImpl extends UserServiceImpl {

}
