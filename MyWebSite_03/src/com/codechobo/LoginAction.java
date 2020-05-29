package com.codechobo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginAction
 */
@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet {

	boolean loginCheck(String id, String pwd) {
		if (!(id.equals("asdf") && pwd.equals("1234"))) {//로그인 실패면 false
			return false;
		}
		return true;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. 입력
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String check = request.getParameter("remember");// on,null
		String url = request.getHeader("referer"); //
		// 2. 작업
		
		String[] urlList = url.split("/");
		String urlToken = urlList[urlList.length-1];
		System.out.println(urlToken);
		
		// 3. 출력
//		PrintWriter out = response.getWriter();
		
		if (!loginCheck(id, pwd)) {// 로그인 실패
			response.sendRedirect(urlToken);
		} else {// 로그인 성공
			
			if (check != null && check.equals("on")) {
				// 1-1 체크박스 y -쿠키생성

				Cookie[] cookies = request.getCookies();
				Cookie cookie = null;

				for (Cookie c : cookies) { // 쿠키있으면 해당쿠키저장
					if (c.getName().equals("cid")) {
						cookie = c;
					}
				}
				if (cookie == null) { // 쿠키없으면 쿠키 생성
					cookie = new Cookie("cid", id);
				}
				
				response.addCookie(cookie);
				cookie.setMaxAge(60 * 60); // 쿠키유효시간 60초 * 60 =1시간

			} else {
				// 1-2 체크박스 n -쿠키삭제
				Cookie[] cookies = request.getCookies(); // cookies가 null 수 있음에
															// 주의
				// 1. 쿠키가 이미 존재하는지 확인.
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals("cid")) {
						Cookie cookie = new Cookie("cid", id); // 2. 쿠키를 생성
						cookie.setMaxAge(0); // 3. 쿠키의 유효시간을 0으로 변경(쿠키삭제)
						response.addCookie(cookie); // 4. 쿠키를 응답에 포함시킨다.
						break;
					}
				}
				
			}
			HttpSession session = request.getSession();//세션 생성
			session.setAttribute("sid", id);
			System.out.println("세션생성성공!");
			if(urlToken.indexOf("Form")!=-1){ // index에서 로그인했을 때
				response.sendRedirect("index.jsp");
			}else{
				response.sendRedirect(urlToken);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);// 자신이 받은 요청을 doGet()에게 넘긴다.
	}

}
