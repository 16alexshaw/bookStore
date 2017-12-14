package com.icss.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.icss.util.DbInfo;

public class BaseDao {

	protected Connection conn;

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public void openConn() throws ClassNotFoundException, SQLException, Exception  {
		if(this.conn==null || this.conn.isClosed()) {
			Class.forName(DbInfo.instance().getDbDriver());
			conn=DriverManager.getConnection(DbInfo.instance().getDbURL(), DbInfo.instance().getUsername(), DbInfo.instance().getPassword());
			
		}
	}
	public void beginTransaction() throws Exception {
		this.openConn();
		if(this.conn !=null) {
			this.conn.setAutoCommit(false);
		}
	}
	public void commit() throws Exception {
		if(this.conn !=null) {
			this.conn.commit();
		}
	}
	public void rollback() throws Exception {
		if(this.conn !=null) {
			this.conn.rollback();
		}
	}
	public void closeConn()  {
		if(this.conn !=null) {
			try {
				this.conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public String getTurnPage(String sql,String keyStr,int start,int end) {
		String newsql=sql+" order by " + keyStr +" limit "+start+" , "+ end;
		return newsql;
	}
	public int getCount(String tb) throws Exception {
		int allrows=0;
		String sql="select count(*) rows from (" + tb + ")";
		this.openConn();
		PreparedStatement ps=this.conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			allrows=rs.getInt("rows");
		}
		rs.close();
		ps.close();
		return allrows;
		
	}
}
