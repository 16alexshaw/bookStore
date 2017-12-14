package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.icss.util.DbInfo;

public class test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BaseDao dao=new BaseDao();
		dao.openConn();
		String sql="select now";
		/*PreparedStatement ps=dao.conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getString(1));
		}*/
		System.out.println(dao.getConn());
		System.out.println(DbInfo.instance().getDbDriver());
	}

}
