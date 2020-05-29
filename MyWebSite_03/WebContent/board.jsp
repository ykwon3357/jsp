<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//String loginBtn="Logout";
 String id = (String)session.getAttribute("sid");
 if(id==null||!id.equals("asdf")){ //세션이 없을시
	// response.sendRedirect("/loginForm.jsp?menu=board");
 	pageContext.forward("/loginForm.jsp");
 
 }
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<jsp:include page="menuBar.jsp" flush="false"/>
  <h1><%=id %>님의 board 입니다.</h1>
</body>
</html>