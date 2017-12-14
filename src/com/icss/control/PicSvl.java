package com.icss.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.BookBiz;

/**
 * Servlet implementation class PicSvl
 */
@WebServlet("/PicSvl")
public class PicSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PicSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn=request.getParameter("isbn");

	if(isbn!=null) {
		BookBiz biz=new BookBiz();
		try {
			byte[] pic=biz.getBookPic(isbn);
			if(pic!=null) {
			ServletOutputStream out=response.getOutputStream();
			out.write(pic);
			out.flush();
			out.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errMsg", "server error");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
				}
	}else {
		request.setAttribute("msg", "isbn is wrong");
		request.getRequestDispatcher("error.jsp").forward(request, response);

	}

	}
}
