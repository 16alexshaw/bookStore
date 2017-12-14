package com.icss.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class PageViewListner implements ServletRequestListener{

	private Integer allpvcount=0;

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub
		allpvcount=allpvcount+1;
		//System.out.println("allpvcount is:"+allpvcount);
		//System.out.println(arg0.getServletRequest().getLocalAddr());
	}
	
}
