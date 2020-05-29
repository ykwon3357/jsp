<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String sid = (String)session.getAttribute("sid");
if(sid!=null&&sid.equals("asdf")){ //세션이 있으면
	HttpSession session1 = request.getSession();
	session1.invalidate(); //세션 삭제
	response.sendRedirect("/index.jsp");
}

Cookie[] cookies = request.getCookies(); // cookies가 null 수 있음에 주의

String id="";
String checked=null; //체크박스 여부
if(cookies!=null){ //쿠키가 있으면
	for(Cookie c:cookies){
		if(c.getName().equals("cid")){ //cid라는 쿠키가 있으면
			id=c.getValue(); //id는 해당쿠키값
			checked="checked"; //id기억체크박스 체크
		}
	}
	
}

%>


<%
	//2. 작업
%>

										

<!-- 3. 출력 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
form {border: 3px solid #f1f1f1;}

input[type=text], input[type=password] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

button {
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

button:hover {
  opacity: 0.8;
}

.cancelbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #f44336;
}

.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
}

img.avatar {
  width: 40%;
  border-radius: 50%;
}

.container {
  padding: 16px;
}

span.psw {
  float: right;
  padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
     display: block;
     float: none;
  }
  .cancelbtn {
     width: 100%;
  }
}
</style>
<title>Login Form</title>
</head>
<body>
	<h2>Login Form</h2>

	<form action="/LoginAction" method="post">

		<div class="container">
			<label for="uname"><b>Username</b></label>
			<input type="text" placeholder="Enter Id" id="id" name="id" required value=<%=id%> >
		    <label for="psw"><b>Password</b></label> 
		    <input type="password" placeholder="Enter Password" name="pwd" required>

			<button type="submit">Login</button>
			<label> <input type="checkbox" id="remember"
				<%=checked %> name="remember"> Remember me
			</label>
		</div>

		<div class="container" style="background-color: #f1f1f1">
			<a href="/index.jsp"><button type="button"
					class="cancelbtn">Cancel</button></a> <span class="psw">Forgot <a
				href="#">password?</a></span>
		</div>
	</form>

</body>
</html>