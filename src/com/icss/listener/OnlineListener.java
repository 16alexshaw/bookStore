package com.icss.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineListener implements HttpSessionListener{

	private Integer usercount=0;
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		//arg0.getSession().setMaxInactiveInterval(60);
		usercount=usercount+1;
		//System.out.println("online person:"+usercount);
		//System.out.println(arg0.getSession().getId()+"enter system"+this.hashCode());

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		usercount=usercount-1;
		//System.out.println(arg0.getSession().getId()+"exit system"+this.hashCode());

		//System.out.println("online person:"+usercount);

	}

}
