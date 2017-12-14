<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<tr>
			<td align="right">
			
			<c:if test="${user==null}">
		 <a href="<%=basePath%>LoginSvl">login</a> 
			
			</c:if>
			<c:if test="${user!=null}">
			
			welcome you ${user.uname} &nbsp;<a href="<%=basePath%>user/ShopcarSvl">cart</a> &nbsp;
			<a href="<%=basePath%>LogoutSvl">logout</a>
			<c:if test="${user.role==1}">
			<a href="<%=basePath%>back/BuyinforSvl">backend</a>
			</c:if>
			</c:if>
			</td>
		</tr>