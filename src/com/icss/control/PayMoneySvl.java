package com.icss.control;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.UserBiz;
import com.icss.entity.TUser;


@WebServlet("/user/PayMoneySvl")
public class PayMoneySvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public PayMoneySvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String allmoneys=request.getParameter("allmoney");
		if(allmoneys !=null) {
			UserBiz biz=new UserBiz();
			Integer allmoney=Integer.parseInt(allmoneys);
			TUser user=(TUser) request.getSession().getAttribute("user");
			Map<String, Integer> shopcar=(Map<String, Integer>) request.getSession().getAttribute("shopcar");
	System.out.println(user.getAccount());
			try {
				if(user.getAccount()>=allmoney) {
					biz.buyBooks(user.getUname(), allmoney, shopcar);
					System.out.println(allmoney);

					user.setAccount(user.getAccount()-allmoney);
					request.setAttribute("allmoney", allmoney);
					request.getRequestDispatcher("/WEB-INF/main/payMoneyOK.jsp").forward(request, response);
	
				}else {
					request.setAttribute("msg", "account  is less");
					request.getRequestDispatcher("/error.jsp").forward(request, response);

				}
			
			} catch (Exception e) {
				e.printStackTrace();
				request.getRequestDispatcher("/error.jsp").forward(request, response);

			}
		}else {
			request.setAttribute("msg", "server error");
			request.getRequestDispatcher("/error.jsp").forward(request, response);

		}
	}

}
