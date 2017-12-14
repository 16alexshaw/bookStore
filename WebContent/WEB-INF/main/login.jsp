<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="<%=basePath%>LoginSvl" id="loginfrm">

   
            <h4>用户登录</h4>
            <input type="text" name="uname" id="uname" placeholder="用户名" />
            </br>
            <input type="password" name="pwd" id="pwd" placeholder="密码" />
            </br>
           
                <button type="submit">登录</button>
          
    </form>
     ${msg}
</body>
</html>