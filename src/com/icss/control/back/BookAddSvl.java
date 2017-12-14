package com.icss.control.back;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.BookBiz;
import com.icss.entity.TBook;
import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

/**
 * Servlet implementation class BookAddSvl
 */
@WebServlet("/back/BookAddSvl")
public class BookAddSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookAddSvl() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/back/BookAdd.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request2, HttpServletResponse response2)
			throws ServletException, IOException {

		SmartUpload smu = new SmartUpload();
		smu.initialize(this.getServletConfig(), request2, response2);
		smu.setCharset("gbk");
		smu.setAllowedFilesList("gif,jpg,png,bmp");
		smu.setMaxFileSize(100 * 1024); // 最大允许100K
		String isbn = null;
		TBook book = new TBook();
		try {
			smu.upload();
			com.jspsmart.upload.Request request = smu.getRequest();

			isbn = request.getParameter("isbn");
			String bname = request.getParameter("bname");
			String author = request.getParameter("author");
			String press = request.getParameter("press");
			String pubdate = request.getParameter("pubdate");
			String price = request.getParameter("price");
			book.setAuthor(author);
			book.setIsbn(isbn);
			book.setBname(bname);
			book.setPress(press);
			/*
			 * if(pubdate!=null && !pubdate.trim().equals("")){ SimpleDateFormat sd = new
			 * SimpleDateFormat("yy-MM-dd HH:mm:ss");
			 * book.setPubdate(sd.parse(pubdate.toString())); }
			 */
			book.setPubdate(pubdate);
			System.out.println(pubdate);
			// System.out.println(new SimpleDateFormat("%Y-%m-%d").parse(pubdate));

			if (price != null && !price.trim().equals("")) {
				book.setPrice(Integer.parseInt(price));
			}
			File file = smu.getFiles().getFile(0); // 支持多个文件同时上传
			if (file != null) {
				int size = file.getSize();
				byte[] bytesPic = new byte[size];
				for (int i = 0; i < size; i++) {
					bytesPic[i] = file.getBinaryData(i);
				}
				book.setPic(bytesPic);
			}
			BookBiz biz = new BookBiz();
			biz.addBook(book);
			request2.setAttribute("msg", bname+ "录入成功");
			request2.getRequestDispatcher("/WEB-INF/back/BookAdd.jsp").forward(request2, response2);

		} catch (MySQLIntegrityConstraintViolationException e) {
			//e.printStackTrace();
			request2.setAttribute("book", book);

			request2.setAttribute("msg", "isbn exist!");
			request2.getRequestDispatcher("/WEB-INF/back/BookAdd.jsp").forward(request2, response2);
		} catch (java.lang.SecurityException e) {
			request2.setAttribute("book", book);
			request2.setAttribute("msg", e.getMessage());
			request2.getRequestDispatcher("/WEB-INF/back/BookAdd.jsp").forward(request2, response2);
		}

		catch (Exception e) {
		e.printStackTrace();

			request2.setAttribute("msg", "网络异常，请和管理员联系");
			request2.getRequestDispatcher("/error.jsp").forward(request2, response2);

		}
		
	}

}
