<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'BuyinfoList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/themes/icon.css">
	<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>

  </head>
  
  <body>
    <table align="center" width=90%>    	
      <jsp:include page="bhead.jsp"></jsp:include>      
      <!-- ��ѯ���� -->
      <tr>
      	<td>
      	    <form action="<%=basePath%>back/BuyinforSvl" method="post">
      		<table>
      		   <tr><td align=left>�û��� </td><td><input type="text" name="uname" value="${uname}"/></td></tr>
               <tr>
                   <td align=left>��ʼ���� </td><td><input class="easyui-datebox" name="beginDate"  data-options="formatter:ww4,parser:w4" value="${beginDate}"/></td>
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
                   <td align=left>�������� </td><td><input class="easyui-datebox" name="endDate" data-options="formatter:ww4,parser:w4" value="${endDate}"/></td>
                  
                   <td><input type=submit value="��ѯ"/></td>
               </tr>
      		</table>
      		</form>
      	</td>
      </tr>
      
      
      <tr>
      	<td align=left>
      	  <table border="1" width=100%> 
      	   <tr><td>�û���</td><td>����</td><td>�鵥��</td><td>������</td><td>����</td><td>��������</td><td>�ܸ���</td></tr>
      	  	  <c:forEach var="info" items="${infoList}">
      	  	     <tr><td>${info.uname}</td><td>${info.bname}</td><td>${info.dealprice}</td><td>${info.press}</td>
      	  	     <td>${info.author}</td><td>${info.paytime}</td><td>${info.allmoney}</td></tr>
      	  	  </c:forEach>
      	  	  
      	<tr>
      	<td colspan=8>
      		<table id="tblTurnPage" cellSpacing="0" cellPadding="1" width="100%" border="0" style="font-family:arial;color:red;font-size:12px;">	    		
	    			<tr>
	    				<td>�ܼ�¼����${allRows} </td> 
	    				<td>��ҳ����${allPages} </td>
	    				<td>��ǰҳ��${currentPage}</td>
	    				<td><a href="<%=basePath%>back/BuyinfoSvl?page=1&uname=${uname}&beginDate=${beginDate}&endDate=${endDate}">��ҳ||</a>
	    				    <a href="<%=basePath%>back/BuyinfoSvl?page=${currentPage-1}&uname=${uname}&beginDate=${beginDate}&endDate=${endDate}">��ǰҳ&nbsp;||&nbsp;</a>
	    				    <a href="<%=basePath%>back/BuyinfoSvl?page=${currentPage+1}&uname=${uname}&beginDate=${beginDate}&endDate=${endDate}">��ҳ��||</a>
	    				    <a href="<%=basePath%>back/BuyinfoSvl?page=${allPages}&uname=${uname}&beginDate=${beginDate}&endDate=${endDate}">ĩҳ||</a></td>
	    				<td >��ת��:��<input type="text" size="3" >ҳ<input type="button" value="go"></td>
	    			</tr>	    		
	    		</table>	    	
      	</td>
      </tr>
      	  	
      	  	
      	  </table>
    	
      	</td>
      </tr>
    </table>
  </body>
</html>
