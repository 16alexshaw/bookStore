package com.icss.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DbInfo {

	private static DbInfo dbInfo;
	private String dbURL;
	private String dbDriver;
	private String username;
	private String password;
	
	public String getDbURL() {
		return dbURL;
	}
	public String getDbDriver() {
		return dbDriver;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	
	private DbInfo() {
		
	}
	public static DbInfo instance() throws Exception {
	if(dbInfo==null) {
		dbInfo=new DbInfo();
		dbInfo.init();
	}
	return dbInfo;
			
	}
	private void init() throws Exception {
		// TODO Auto-generated method stub
		Properties prop=new Properties();
		String path=DbInfo.class.getResource("/").getPath()+"db.properties";
		prop.load(new FileInputStream(new File(path)));
		this.dbURL=prop.getProperty("db_url");
		this.dbDriver=prop.getProperty("db_driver");
		this.password=prop.getProperty("db_password");
		this.username=prop.getProperty("db_username");

	}
	
}
