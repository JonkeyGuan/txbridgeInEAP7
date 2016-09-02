package com.test.user;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;

import org.jboss.jbossts.txbridge.outbound.JaxWSTxOutboundBridgeHandler;

import com.arjuna.mw.wst11.client.JaxWSHeaderContextProcessor;
import com.test.user.service.UserService;

public class ClientFactory {

    private static URL loalServiceURL;
    private static URL remoteServiceURL;
    static {
	try {
	    loalServiceURL = new URL("http://localhost:8380/ServiceC1/UserService?wsdl");
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	}
	try {
	    remoteServiceURL = new URL("http://localhost:8480/ServiceD1/inboundUserService?wsdl");
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	}
    }

    private static QName localQName = new QName("http://service.user.test.com/", "UserService");
    private static QName remoteQName = new QName("http://service.user.test.com/", "inboundUserService");

    public static UserService getClient() {
	return getClient(loalServiceURL, localQName);
    }

    public static UserService getRemoteClient() {
	UserService client = getClient(remoteServiceURL, remoteQName);
	BindingProvider bindingProvider = (BindingProvider) client;
	bindingProvider.getBinding().setHandlerChain(getOutboundHandlers());
	return client;
    }

    private static UserService getClient(URL url, QName qName) {
	UserService client = null;
	Service service = Service.create(url, qName);
	client = service.getPort(UserService.class);
	return client;
    }

    @SuppressWarnings("rawtypes")
    private static List<Handler> getOutboundHandlers() {
	List<Handler> handlers = new ArrayList<Handler>();
	handlers.add(new JaxWSTxOutboundBridgeHandler());
	handlers.add(new JaxWSHeaderContextProcessor());
	return handlers;
    }

}
