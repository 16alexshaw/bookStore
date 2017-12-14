<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
<table align="center" width=90% >
<jsp:include page="mhead.jsp"></jsp:include>

<tr>
<td>
<table border="1" width="100%">

<tr><td rowspan="3"><img width=100 height=100 src="<%=basePath%>PicSvl?isbn=${book.isbn}"></td><td colspan="2" align="center">${book.bname}</td></tr>
<tr><td>press</td><td>${book.press}</td></tr>
<tr><td>price</td><td>${book.price}</td></tr>
<tr><td height=300 colspan="3">${book.descr}</td></tr>
<tr><td colspan="3" align="center"><a href="<%=basePath%>user/ShopcarAddSvl?isbn=${book.isbn}">add to cart</a> &nbsp;<a href="<%=basePath%>MainSvl">back</a> </td></tr>

</table>
</td>
</tr>
</table>
</body>
</html>