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
		// 1. 서블릿이 요청을 받기 전에 처리할 작업 작성(요청 전처리 작업) 
		HttpServletRequest httpRequest = (HttpServletRequest)request;
        
        String url = httpRequest.getRequestURI(); //요청 주소 가져옴
        if(url.indexOf("index")==-1) { //index.jsp가 아닐때
        	HttpSession session = httpRequest.getSession(true); 
            String sid = (String)session.getAttribute("sid");
           
            if(sid==null||!sid.equals("asdf")){ //로그인되어있지 않다면 loginForm으로 forward
            	RequestDispatcher reqDis = request.getRequestDispatcher("/loginForm.jsp");
				reqDis.forward(request, response);
            }
        }

        // 2. 다음 filter가 작업을 할 수 있게 요청과 응답을 전달(그대로 사용)
        chain.doFilter(request, response);
        // 3. 서블릿이 응답한 직후에 처리할 작업 작성(응답 후처리 작업)
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
