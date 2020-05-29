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
		// 1. 서블릿이 요청을 받기 전에 처리할 작업 작성(요청 전처리 작업) 
//        System.out.println("[start]" + new Date());
		String loginBtn="Login"; //로그인버튼 이름

		HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpSession session = httpRequest.getSession(true);
        String sid = (String)session.getAttribute("sid");
		if(sid!=null&&sid.equals("asdf")){ //세션있으면 로그인버튼 로그아웃으로 바뀜
			System.out.println("세션있음!");
			loginBtn="Logout";
		}else{//세션없으면
			sid="고객";
		}
		

        // 2. 다음 filter가 작업을 할 수 있게 요청과 응답을 전달(그대로 사용)
        chain.doFilter(request, response);

        // 3. 서블릿이 응답한 직후에 처리할 작업 작성(응답 후처리 작업)
        System.out.println("[end]"+new Date());         
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
