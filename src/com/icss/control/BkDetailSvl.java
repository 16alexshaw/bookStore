package com.icss.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.BookBiz;
import com.icss.entity.TBook;

/**
 * Servlet implementation class BkDetailSvl
 */
@WebServlet("/BkDetailSvl")
public class BkDetailSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn=request.getParameter("isbn");
		if(isbn!=null) {
			BookBiz biz=new BookBiz();
			try {
				TBook book=biz.getBookDetail(isbn);
				request.setAttribute("book", book);
				request.getRequestDispatcher("/WEB-INF/main/BookDetail.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errMsg", "server error");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			
			}
		}else {
			request.setAttribute("msg", "isbn is null");
			request.getRequestDispatcher("/error.jsp").forward(request, response);

		}
	}

}
