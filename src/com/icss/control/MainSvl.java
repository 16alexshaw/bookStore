package com.icss.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.BookBiz;
import com.icss.biz.UserBiz;
import com.icss.entity.TBook;
import com.icss.entity.TUser;
import com.icss.util.Log;

/**
 * Servlet implementation class MainSvl
 */
@WebServlet("/MainSvl")
public class MainSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//auto login
		Object object=req.getSession();
		if(object ==null) {
			Cookie[] cookies=req.getCookies();
			if(cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie=cookies[i];
				if(cookie.getName().equals("user")) {
					String value=cookie.getValue();
					String[] upwd=value.split(",");
					UserBiz biz=new UserBiz();
					try {
						TUser tUser=biz.login(upwd[0], upwd[1]);
						if(tUser != null) {
							req.getSession().setAttribute("user", tUser);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			}
		}
		BookBiz biz=new BookBiz();
		try {
			List<TBook> books=biz.getAllBooks();
			//System.out.println(books.size());
			req.setAttribute("books", books);
			req.getRequestDispatcher("/WEB-INF/main/main.jsp").forward(req, resp);
			
		} catch (Exception e) {
	e.printStackTrace();
			req.setAttribute("errormsg", "server error");
			req.getRequestDispatcher("/error.jsp").forward(req, resp);

		}
		
	}
	

}
