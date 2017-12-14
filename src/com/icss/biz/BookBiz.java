package com.icss.biz;

import java.util.List;
import java.util.Set;

import com.icss.dao.BookDao;
import com.icss.entity.TBook;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class BookBiz {

	public void addBook(TBook tBook) throws MySQLIntegrityConstraintViolationException,Exception{
		BookDao dao=new BookDao();
		try {
			dao.addBook(tBook);
		} catch (Exception e) {
			
			e.printStackTrace();
			throw e;
		}finally {
			dao.closeConn();
		}
	}
	public List<TBook> getShopcarBooks(Set<String> isbns) throws Exception{
		List<TBook> books=null;
		if(isbns !=null && isbns.size()>0) {
			BookDao dao=new BookDao();
			try {
				books=dao.getShopcarBooks(isbns);

			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				dao.closeConn();
			}
		}
		
		return books;
		
	}
	public TBook getBookDetail(String isbn) throws Exception{
		TBook book=null;
		if(isbn!=null) {
			BookDao dao=new BookDao();
			try {
				book=dao.getBookDetail(isbn);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				dao.closeConn();
			}
		}
		
		
		return book;		
	}
	public byte[] getBookPic(String isbn) throws Exception{
		byte[] pic=null;
		if(isbn !=null) {
			BookDao dao=new BookDao();

			try {
				pic=dao.getBookPic(isbn);
			} catch (Exception e) {
				throw e;
			}finally {
				dao.closeConn();
			}
			
		}else {
			throw new Exception("isbn is null");
		}
		return pic;
		
	}
	public List<TBook> getAllBooks() throws Exception{
		List<TBook> books=null;
		BookDao dao=new BookDao();
		try {
			books=dao.getAllBooks();
			//System.out.println(books.size());
		} catch (Exception e) {
			throw e;
		}finally {
			dao.closeConn();
		}
		return books;
	}
}
