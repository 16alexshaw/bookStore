<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

 <tr>
      	<td align=right>
      	    
      	  		����Ա: admin &nbsp;	  		       	         	    	         	          	  	
      		
      			<a href="<%=basePath%>LogoutSvl">�˳�</a>
      		      	
      	</td>       	
      </tr>
      
      <tr>
      	<td align=center>
      	    <a href="<%=basePath%>back/BookAddSvl">�����ϼ�</a> &nbsp;  <a href="#">�����ӿ��</a>  &nbsp;  <a href="#">���¼�</a> &nbsp;  <a href="#">�û�����</a>
      	    &nbsp;  <a href="#">�޸��ۼ�</a> &nbsp; <a href="<%=basePath%>back/BuyinforSvl">�û������¼</a>
      	</td>
      	</tr>    	
      <tr><td align="left"><h2>�����¼</h2></td></tr>