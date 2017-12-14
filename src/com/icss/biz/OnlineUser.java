package com.icss.biz;

import java.util.HashMap;
import java.util.Map;

import com.icss.entity.TUser;
/**
 * sum of the online users
 * @author Administrator
 *
 */
public class OnlineUser {

	private static Map<String, TUser> users;
	
	public static Map<String, TUser> getUsers() {
		return users;
	}
	static {
		users=new HashMap<String, TUser>();
	}
	public static void addUser(String sessionid,TUser tuser) {
		users.put(sessionid, tuser);
	}
	public static void removeUser(String sessionid) {
		users.remove(sessionid);
	}
	
}
