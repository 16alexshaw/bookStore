package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.icss.entity.TBook;
import com.icss.entity.TOrderDetail;

public class BookDao extends BaseDao{

	public void addBook(TBook tbook) throws Exception{
		String sql = "insert into tbook  values(?,?,?,?,?,?,?,?,?)";
		this.openConn();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1,tbook.getIsbn());
		ps.setString(2,tbook.getBname());
		ps.setString(3,tbook.getAuthor());
		if(tbook.getPubdate() == null){
			ps.setString(4, null);
		}else{
			ps.setString(4,tbook.getPubdate());
		}
		ps.setString(5,tbook.getPress());
		ps.setDouble(6, tbook.getPrice());
		ps.setString(7,tbook.getDescr());
		ps.setBytes(8, tbook.getPic());
		ps.setInt(9, 0);
		ps.executeUpdate();
		ps.close();

	}
	
	public String createOrder(String uname,int allmoney) throws Exception, SQLException, Exception {
		SimpleDateFormat sd=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String orderid="d-"+sd.format(new Date());
		String sql="insert into torder values(?,?,?,?)";
		this.openConn();
	PreparedStatement ps=this.conn.prepareStatement(sql);
		ps.setString(1, orderid);
		ps.setString(2, uname);
		ps.setInt(3, allmoney);
		ps.setTimestamp(4, new java.sql.Timestamp(new Date().getTime()));
		ps.executeUpdate();
		ps.close();
		return orderid;
		
	}
	public void addOrderDetail(TOrderDetail detail) throws Exception{
		String sql="insert into torderdetail (aid,oid,isbn,bookcount,salePrice) values  ((select IFNULL(max(t.aid),0)+1 from torderdetail t),?,?,?,?)";
		this.openConn();
		PreparedStatement ps=this.conn.prepareStatement(sql);
			ps.setString(1, detail.getOid());
			ps.setString(2, detail.getIsbn());
			ps.setInt(3, detail.getBuycount());
			ps.setInt(4, detail.getDealprice());
			ps.executeUpdate();
		
			ps.close();
			cutBookCount(detail.getIsbn(),detail.getBuycount());
	}
	private void cutBookCount(String isbn,int buycount) throws Exception{
		String sql="update tbook set bkaccount=bkaccount - ? where isbn = ?";
		this.openConn();
		PreparedStatement ps=this.conn.prepareStatement(sql);
			ps.setInt(1,buycount);
			ps.setString(2, isbn);
			ps.executeUpdate();
			ps.close();
			
	}
	public List<TBook> getShopcarBooks(Set<String> isbns) throws Exception{
		String isbnString=null;
		int i=0;
		for(String isbn : isbns) {
			if(i==0) {
				isbnString="'"+isbn+"'";
			}else {
				isbnString=isbnString+",'"+isbn+"'";
			}
			i=i+1;
		}
		String sql = "select isbn,bname,author,press,price from tbook where isbn in ("+isbnString+")";
		this.openConn();
		PreparedStatement ps=this.conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		ArrayList<TBook> books = new ArrayList<TBook>();
		while(rs.next()) {
			TBook bk=new TBook();
			bk.setAuthor(rs.getString("author"));
			bk.setBname(rs.getString("bname"));
			//bk.setDescr(rs.getString("descr"));
			bk.setIsbn(rs.getString("isbn"));
			bk.setPress(rs.getString("press"));
			bk.setPrice(rs.getInt("price"));
			//bk.setPubdate(rs.getDate("pubdate"));
		books.add(bk);
		}
		rs.close();
		ps.close();
		
		return books;
		
	}
	public TBook getBookDetail(String isbn) throws Exception{
		String sql = "select isbn,bname,author,pubdate,press,price,descr from tbook where isbn = ?";
		TBook bk=null;
		this.openConn();
		PreparedStatement ps=this.conn.prepareStatement(sql);
		ps.setString(1, isbn);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			bk=new TBook();
			bk.setAuthor(rs.getString("author"));
			bk.setBname(rs.getString("bname"));
			bk.setDescr(rs.getString("descr"));
			bk.setIsbn(rs.getString("isbn"));
			bk.setPress(rs.getString("press"));
			bk.setPrice(rs.getInt("price"));
			bk.setPubdate(rs.getString("pubdate"));
		}
		rs.close();
		ps.close();
		return bk;
	}
	public byte[] getBookPic(String isbn) throws Exception{
		byte[] pic=null;

		String sql="select pic from tbook where isbn = ?";
		this.openConn();
		PreparedStatement ps=this.conn.prepareStatement(sql);
		ps.setString(1, isbn);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			pic=rs.getBytes("pic");
			break;
		}
		rs.close();
		ps.close();
		return pic;
		
	}

	public List<TBook> getAllBooks() throws Exception{
	List<TBook> books=null;
	String sql="select isbn,bname,author,pubdate,press,price,descr from tbook";
	this.openConn();
	PreparedStatement ps=this.conn.prepareStatement(sql);
	ResultSet rs=ps.executeQuery();
	books=new ArrayList<TBook>(20);
	while(rs.next()) {
		TBook bk=new TBook();
		bk.setAuthor(rs.getString("author"));
		bk.setBname(rs.getString("bname"));
		bk.setDescr(rs.getString("descr"));
		bk.setIsbn(rs.getString("isbn"));
		bk.setPress(rs.getString("press"));
		bk.setPrice(rs.getInt("price"));
		bk.setPubdate(rs.getString("pubdate"));
		//System.out.println("price:"+rs.getInt("price"));
		books.add(bk);
	}
	//System.out.println(books.size());
	rs.close();
	ps.close();
		return books;
	}
}
