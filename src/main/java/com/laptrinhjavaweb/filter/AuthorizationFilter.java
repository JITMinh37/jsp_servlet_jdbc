package com.laptrinhjavaweb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.model.userModel;
import com.laptrinhjavaweb.utils.SessionUtil;

public class AuthorizationFilter implements Filter{

	private ServletContext context;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext(); // Cấu hình (set up) ServletFilter
	}

	@Override
	public void doFilter(ServletRequest requesServletRequest, ServletResponse responsServletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) requesServletRequest;
		HttpServletResponse response = (HttpServletResponse) responsServletResponse;
		String url = request.getRequestURI();
		if(url.startsWith(request.getContextPath()+"/admin")) {
			userModel model = (userModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
			if(model != null) {
				if(model.getRole().getCode().equals("0")) {
					chain.doFilter(requesServletRequest, responsServletResponse);	
				}else if(model.getRole().getCode().equals("1")) {
					response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=not_premission&alert=danger");
				}
			}else {
				response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=not_login&alert=danger");
			}	
		}
		else {
			chain.doFilter(requesServletRequest, responsServletResponse); // Cho phép truy cập vào url
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
