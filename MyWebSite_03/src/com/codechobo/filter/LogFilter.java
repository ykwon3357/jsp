package com.codechobo.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LogFilter
 */
@WebFilter(filterName="LogFilter", urlPatterns= { "*.jsp" })
public class LogFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LogFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		// 1. ������ ��û�� �ޱ� ���� ó���� �۾� �ۼ�(��û ��ó�� �۾�) 
//        System.out.println("[start]" + new Date());
		String loginBtn="Login"; //�α��ι�ư �̸�

		HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpSession session = httpRequest.getSession(true);
        String sid = (String)session.getAttribute("sid");
		if(sid!=null&&sid.equals("asdf")){ //���������� �α��ι�ư �α׾ƿ����� �ٲ�
			System.out.println("��������!");
			loginBtn="Logout";
		}else{//���Ǿ�����
			sid="��";
		}
		

        // 2. ���� filter�� �۾��� �� �� �ְ� ��û�� ������ ����(�״�� ���)
        chain.doFilter(request, response);

        // 3. ������ ������ ���Ŀ� ó���� �۾� �ۼ�(���� ��ó�� �۾�)
        System.out.println("[end]"+new Date());         
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
