package com.icss.control;

import java.io.IOException;
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
 * Servlet implementation class CheckoutSvl
 */
@WebServlet("/user/CheckoutSvl")
public class CheckoutSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutSvl() {
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
		Map<String, Object> shopMap = (Map<String, Object>) request.getSession().getAttribute("shopcar");
		BookBiz biz=new BookBiz();
		try {
			List<TBook> books=biz.getShopcarBooks(shopMap.keySet());
			int allmoney=0;
			for(TBook bk:books) {
				//int buycount=(int) shopMap.get(bk.getIsbn());
				String bcount=request.getParameter(bk.getIsbn());
				int buycount=Integer.parseInt(bcount);
				bk.setBuycount(buycount);
				shopMap.put(bk.getIsbn(), buycount);
				allmoney=allmoney+bk.getPrice()*buycount;
						
			}
			request.setAttribute("allmoney", allmoney);

			request.setAttribute("books", books);
			request.getRequestDispatcher("/WEB-INF/main/checkout.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "server error");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		
		}
	}

}
