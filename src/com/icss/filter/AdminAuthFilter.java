package com.icss.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.icss.entity.TUser;

/**
 * Servlet Filter implementation class AdminAuthFilter
 */
@WebFilter("/back/*")
public class AdminAuthFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminAuthFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		Object obj=req.getSession().getAttribute("user");
		if(obj==null) {
			req.setAttribute("msg", "need login");
			request.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, response);

		}else {
			TUser user=(TUser) obj;
			if(user.getRole()==1) {
				chain.doFilter(request, response);

			}else {
				req.setAttribute("msg", "need login as admin ");
				request.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, response);

			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
