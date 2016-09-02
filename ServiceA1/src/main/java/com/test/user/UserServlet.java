package com.test.user;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.user.domain.User;

public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = -7003525307704686462L;

    private static AtomicLong userSeq = new AtomicLong(1);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	String action = request.getParameter("action");
	if (action == null) {
	    action = "";
	}

	String id = request.getParameter("id");
	if (id == null) {
	    id = "";
	}

	if (action.equalsIgnoreCase("add")) {
	    addUser(id);
	} else {
	    updateUser(id);
	}
    }

    private void addUser(String id) {
	String userId = "";
	if (id.isEmpty()) {
	    userId = "user" + userSeq.incrementAndGet();
	} else {
	    userId = id;
	}
	ClientFactory.getClient().addUser(new User(userId, "name:" + System.currentTimeMillis()));
    }

    private void updateUser(String id) {
	String userId = "";
	if (id.isEmpty()) {
	    userId = "user" + userSeq.incrementAndGet();
	} else {
	    userId = id;
	}
	ClientFactory.getClient().updateUser(new User(userId, "name:" + System.currentTimeMillis()));
    }

}
