package com.icss.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.BookBiz;
import com.icss.entity.TBook;

/**
 * Servlet implementation class ShopcarSvl
 */
@WebServlet("/user/ShopcarSvl")
public class ShopcarSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopcarSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object obj=request.getSession().getAttribute("shopcar");
		Map<String, Integer> shopMap;
		if(obj==null) {
			shopMap=new HashMap<>();
			request.getSession().setAttribute("shopcar", shopMap);
		}else {
			shopMap=(Map<String, Integer>) obj;
		}
		BookBiz biz=new BookBiz();
		try {
			List<TBook> books=biz.getShopcarBooks(shopMap.keySet());
			request.setAttribute("shopBooks", books);
			request.getRequestDispatcher("/WEB-INF/main/shopCar.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errormsg", "server error");
			request.getRequestDispatcher("/error.jsp").forward(request, response);

		}
	}

	
}
