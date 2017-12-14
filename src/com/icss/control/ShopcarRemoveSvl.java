package com.icss.control;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShopcarRemoveSvl
 */
@WebServlet("/user/ShopcarRemoveSvl")
public class ShopcarRemoveSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopcarRemoveSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String isbn=request.getParameter("isbn");
	if(isbn!=null) {
		Map<String, Object> obj = (Map<String, Object>) request.getSession().getAttribute("shopcar");
		obj.remove(isbn);
		request.getRequestDispatcher("/user/ShopcarSvl").forward(request, response);

	}else {
		//e.printStackTrace();
		request.setAttribute("msg", "isbn is null");
		request.getRequestDispatcher("/error.jsp").forward(request, response);

	}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
