package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.icss.dto.Buyinfo;
import com.icss.entity.TUser;

public class UserDao extends BaseDao{
	//多表查询
	public List<Buyinfo> lookUserBuyinfo(String uname,String begindate,String endDate) throws Exception, SQLException, Exception{
		List<Buyinfo> inforList=null;
		String sql="select o.username,o.oid,o.allmoney,o.paytime,d.aid,d.bookcount,d.salePrice,b.isbn,b.bname,b.author,b.pubdate,b.press,b.price,b.descr,b.pic,b.bkaccount \r\n" + 
				"				     from torder o,torderdetail d,tbook b \r\n" + 
				"				     where o.oid = d.oid   and b.isbn = d.isbn";
		if(uname !=null && !uname.trim().equals("")) {
			sql = sql + " and o.username like '%" + uname + "%'";
		}
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		if(begindate != null && !begindate.trim().equals("")) {
			System.out.println(begindate);
			//String bDate=sd.format(begindate);
			sql=sql+" and o.paytime >= str_to_date('"+begindate+"','%Y-%m-%d %H:%i:%s')";
		}
		if(endDate != null && !endDate.trim().equals("")) {
			//String eDate=sd.format(endDate);
			sql=sql+" and o.paytime < str_to_date('"+endDate+"','%Y-%m-%d %H:%i:%s')";
		}
		this.openConn();
		//System.out.println(sql);
		PreparedStatement ps=this.conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		inforList=new ArrayList<Buyinfo>();
		while(rs.next()) {
			Buyinfo info=new Buyinfo();
			info.setAid(rs.getInt("aid"));
			info.setAllmoney(rs.getInt("allmoney"));
			info.setAuthor(rs.getString("author"));
			info.setBkcount(rs.getInt("bkaccount"));
			info.setBname(rs.getString("bname"));
			info.setDealprice(rs.getInt("salePrice"));
			info.setIsbn(rs.getString("isbn"));
			info.setOid(rs.getString("oid"));
			info.setPaytime(rs.getTimestamp("paytime"));
			info.setPress(rs.getString("press"));
			info.setUname(rs.getString("username"));
			inforList.add(info);

		}
		rs.close();
		ps.close();
		return inforList;
		
	}

	public void updateAccount(String uname,int money) throws Exception{
		String sql="update tuser set account = account - ? where uname = ?";
		this.openConn();
		PreparedStatement ps=this.conn.prepareStatement(sql);
		ps.setInt(1, money);
		ps.setString(2, uname);
		
		ps.executeUpdate();	
		ps.close();
	}

	public TUser login(String uname,String pwd) throws ClassNotFoundException, Exception   {
		TUser tUser=null;
		String sql="select * from tuser where uname = ? and pwd = ? ";
		//System.out.println(this.conn.getClientInfo());
		this.openConn();
		PreparedStatement ps=this.conn.prepareStatement(sql);
		ps.setString(1, uname);
		ps.setString(2, pwd);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			tUser=new TUser();
			tUser.setUname(uname);
			tUser.setPwd(pwd);
			tUser.setRole(rs.getInt("role"));
			tUser.setAccount(rs.getInt("account"));
			break;
		}
		rs.close();
		ps.close();
		return tUser;
	}
}
