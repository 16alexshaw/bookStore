package com.icss.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShopcarAddSvl
 */
@WebServlet("/user/ShopcarAddSvl")
public class ShopcarAddSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShopcarAddSvl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			String isbn = request.getParameter("isbn");
			if (isbn == null) {
				request.setAttribute("msg", "isbn is null");
			} else {
				Object obj = request.getSession().getAttribute("shopcar");
				Map<String, Integer> shopMap;
				if (obj == null) {
					shopMap = new HashMap<>();
					request.getSession().setAttribute("shopcar", shopMap);
				} else {
					shopMap = (Map<String, Integer>) obj;
				}
				shopMap.put(isbn, 1);
				request.getRequestDispatcher("/user/ShopcarSvl").forward(request, response);

			}

		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
