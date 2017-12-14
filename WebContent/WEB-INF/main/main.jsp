<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

	<table align="center" width=90%>
		<jsp:include page="mhead.jsp"></jsp:include>
		<tr>
			<td>
				<table border="1" width="100%">
					<c:forEach var="bk" items="${books}">
						<tr>
							<td rowspan="3"><img width=100 height=100 src="<%=basePath%>PicSvl?isbn=${bk.isbn}"></td>
							<td colspan="2" align="center" style="color: red"><a href="<%=basePath%>BkDetailSvl?isbn=${bk.isbn}">${bk.bname}</a></td>
						</tr>
						<tr>
							<td>price</td>
							<td>${bk.price}</td>
						</tr>
						<tr>
							<td>press</td>
							<td>${bk.press}</td>
						</tr>
				</c:forEach>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>