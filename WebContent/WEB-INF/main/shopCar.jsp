<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<form action="<%=basePath%>/user/CheckoutSvl" method="post">
<table align="center" width=90% >
<jsp:include page="mhead.jsp"></jsp:include>

<tr>
<td>
<table border="1" width="100%">
<tr><td>name</td><td>author</td><td>price</td><td width="5%">sum</td><td>to do</td></tr>
<c:forEach var="bk" items="${shopBooks}">
<tr><td>${bk.bname}</td><td>${bk.author}</td><td>${bk.price}</td><td><input type="text" name="${bk.isbn}" value="1"/></td><td><a href="<%=basePath%>/user/ShopcarRemoveSvl?isbn=${bk.isbn}">delete</a></td></tr>
</c:forEach>
</table>
</td>
</tr>
<tr>
<td align="center"><input type="submit" value="pay"> &nbsp; <a href="<%=basePath%>MainSvl">back</a></td>
</tr>
</table>
</form>
</body>
</html>