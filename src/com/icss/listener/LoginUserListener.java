package com.icss.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.icss.biz.OnlineUser;

public class LoginUserListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getSession().getId()+"enter system"+this.hashCode());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getSession().getId()+"exit system"+this.hashCode());
		OnlineUser.removeUser(arg0.getSession().getId());
		System.out.println("login user:"+OnlineUser.getUsers().size());
	}

}
