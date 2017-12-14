package com.icss.control.back;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.UserBiz;
import com.icss.dto.Buyinfo;

/**
 * Servlet implementation class BuyinforSvl
 */
@WebServlet("/back/BuyinforSvl")
public class BuyinforSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyinforSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	request.getRequestDispatcher("/WEB-INF/back/BuyinfoList.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uname=request.getParameter("uname");
		String beginDate=request.getParameter("beginDate");
		String endDate=request.getParameter("endDate");
		Date bDate=null,eDate=null;
	 UserBiz biz=new UserBiz();
		System.out.println("beginDate:"+beginDate);

	 try {
			SimpleDateFormat sd=new SimpleDateFormat("yyyy/MM/dd");
			//SimpleDateFormat sd2=new SimpleDateFormat("yyyy-MM-dd");

		/*	if(beginDate !=null && !beginDate.trim().equals("")) {
				
				bDate=(Date) sd.parseObject(beginDate);
				
				System.out.println("bdate:"+bDate);

			}
			if(endDate !=null && !endDate.trim().equals("")) {
				eDate=sd.parse(endDate);
			}*/
			List<Buyinfo> infoList=biz.lookUserBuyinfo(uname, beginDate, endDate);
			request.setAttribute("infoList", infoList);
			request.setAttribute("uname", uname);
			request.getRequestDispatcher("/WEB-INF/back/BuyinfoList.jsp").forward(request, response);
		
	} catch (Exception e) {
		e.printStackTrace();
		request.setAttribute("msg", "server error");
		request.getRequestDispatcher("/error.jsp").forward(request, response);
	}
		
	}

}
