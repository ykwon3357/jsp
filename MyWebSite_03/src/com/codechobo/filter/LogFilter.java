package com.codechobo.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName="LogFilter", urlPatterns= { "*.jsp" })
public class LogFilter implements Filter {

    public LogFilter() {
    }
	public void destroy() {
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 1. ������ ��û�� �ޱ� ���� ó���� �۾� �ۼ�(��û ��ó�� �۾�) 
		HttpServletRequest httpRequest = (HttpServletRequest)request;
        
        String url = httpRequest.getRequestURI(); //��û �ּ� ������
        if(url.indexOf("index")==-1) { //index.jsp�� �ƴҶ�
        	HttpSession session = httpRequest.getSession(true); 
            String sid = (String)session.getAttribute("sid");
           
            if(sid==null||!sid.equals("asdf")){ //�α��εǾ����� �ʴٸ� loginForm���� forward
            	RequestDispatcher reqDis = request.getRequestDispatcher("/loginForm.jsp");
				reqDis.forward(request, response);
            }
        }

        // 2. ���� filter�� �۾��� �� �� �ְ� ��û�� ������ ����(�״�� ���)
        chain.doFilter(request, response);
        // 3. ������ ������ ���Ŀ� ó���� �۾� �ۼ�(���� ��ó�� �۾�)
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
