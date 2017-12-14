package com.icss.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.OnlineUser;
import com.icss.biz.UserBiz;
import com.icss.entity.TUser;
@WebServlet("/LoginSvl")
public class LoginSvl extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uname=req.getParameter("uname");
		String pwd=req.getParameter("pwd");
		UserBiz biz=new UserBiz();
		try {
			TUser user=biz.login(uname, pwd);
			if(user!=null) {
				req.getSession().setAttribute("user", user);
				//write cookie
				OnlineUser.addUser(req.getSession().getId(), user);
				System.out.println("login user:"+OnlineUser.getUsers().size());

				Cookie cookie=new Cookie("user", uname+","+pwd);
				cookie.setMaxAge(60*60);
				resp.addCookie(cookie);
				//req.setAttribute("user", user);
				req.getRequestDispatcher("/MainSvl").forward(req, resp);
			}else {
				req.setAttribute("msg", "name or pwd is wrong");
				req.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(req, resp);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("msg", "server error");
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		
		}
	}

	
}
