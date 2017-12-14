<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'BookAdd.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/themes/icon.css">
	<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
	<script type="text/javascript">
	   function tijiao(){
	      var isbn = document.getElementById("isbn").value;
	      var bname = document.getElementById("bname").value;
	      var tishi = document.getElementById("tishi");
	      if(isbn==''){
	         tishi.innerHTML = "isbn不能为空";
	         return false;
	      }
	      if(bname==''){
	         tishi.innerHTML = "书名不能为空";
	         return false;
	      }
	      var myform = document.getElementById("myform");
	      myform.submit();	   
	   }	
	</script>
  </head>
  
  <body>
    <table align="center" width=90%>    
     <jsp:include page="bhead.jsp"></jsp:include>  	
      <tr><td align="left"><h2>新书上架</h2></td></tr>
      <tr>
      	<td>
      	<form id=myform action="<%=basePath%>back/BookAddSvl" method="post" enctype="multipart/form-data"">
      		<table border="0" width=60% align="center">  		
      		
      			<tr><td>书号ISBN</td><td><input type="text" name="isbn" id="isbn" value="${book.isbn}"/></td></tr>
       			<tr><td>书名</td><td><input type="text" name="bname" id="bname" value="${book.bname}" /><span id="tishi"></span></td></tr>
       			<tr><td>作者</td><td><input type="text" name="author" value="${book.author}" /></td></tr>
       			<tr><td>出版社</td><td><input type="text" name="press" value="${book.press}"/></td></tr>
       			<tr><td>出版日期</td><td><input class="easyui-datebox" name="pubdate" data-options="formatter:ww4,parser:w4"/></td></tr>
       			  <script type="text/javascript">  
        function ww4(date){  
            var y = date.getFullYear();  
            var m = date.getMonth()+1;  
            var d = date.getDate();  
            var h = date.getHours();  
            return  y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);  
              
        }  
        function w4(s){  
        	if (!s) return new Date();
			var ss = (s.split('-'));
			var y = parseInt(ss[0],10);
			var m = parseInt(ss[1],10);
			var d = parseInt(ss[2],10);
			if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
				return new Date(y,m-1,d);
			} else {
				return new Date();
			}
        }  
    </script> 
       			<tr><td>价格</td><td><input class="easyui-numberbox"  name="price" value="0"></td></tr>
       			<tr><td>图片上传</td><td><input type="file" name="pic"/></td></tr>
       			<tr><td colspan=2 align=center><input type=button value=提交 onclick="tijiao()" /></td></tr>
       			<tr><td colspan=2 align=center>${msg}</td></tr>
    		</table>
    	</form>
      	</td>
      </tr>
    </table>
  </body>
</html>
