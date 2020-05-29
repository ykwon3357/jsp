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
		if (!(id.equals("asdf") && pwd.equals("1234"))) {//�α��� ���и� false
			return false;
		}
		return true;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. �Է�
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String check = request.getParameter("remember");// on,null
		String url = request.getHeader("referer"); //
		// 2. �۾�
		
		String[] urlList = url.split("/");
		String urlToken = urlList[urlList.length-1];
		System.out.println(urlToken);
		
		// 3. ���
//		PrintWriter out = response.getWriter();
		
		if (!loginCheck(id, pwd)) {// �α��� ����
			response.sendRedirect(urlToken);
		} else {// �α��� ����
			
			if (check != null && check.equals("on")) {
				// 1-1 üũ�ڽ� y -��Ű����

				Cookie[] cookies = request.getCookies();
				Cookie cookie = null;

				for (Cookie c : cookies) { // ��Ű������ �ش���Ű����
					if (c.getName().equals("cid")) {
						cookie = c;
					}
				}
				if (cookie == null) { // ��Ű������ ��Ű ����
					cookie = new Cookie("cid", id);
				}
				
				response.addCookie(cookie);
				cookie.setMaxAge(60 * 60); // ��Ű��ȿ�ð� 60�� * 60 =1�ð�

			} else {
				// 1-2 üũ�ڽ� n -��Ű����
				Cookie[] cookies = request.getCookies(); // cookies�� null �� ������
															// ����
				// 1. ��Ű�� �̹� �����ϴ��� Ȯ��.
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals("cid")) {
						Cookie cookie = new Cookie("cid", id); // 2. ��Ű�� ����
						cookie.setMaxAge(0); // 3. ��Ű�� ��ȿ�ð��� 0���� ����(��Ű����)
						response.addCookie(cookie); // 4. ��Ű�� ���信 ���Խ�Ų��.
						break;
					}
				}
				
			}
			HttpSession session = request.getSession();//���� ����
			session.setAttribute("sid", id);
			System.out.println("���ǻ�������!");
			if(urlToken.indexOf("Form")!=-1){ // index���� �α������� ��
				response.sendRedirect("index.jsp");
			}else{
				response.sendRedirect(urlToken);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);// �ڽ��� ���� ��û�� doGet()���� �ѱ��.
	}

}
