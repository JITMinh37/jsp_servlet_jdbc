package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/admin-home"}) //khi một yêu cầu HTTP được gửi đến địa chỉ web của ứng dụng với đường dẫn "/admin-home", Servlet này sẽ được gọi để xử lý yêu cầu.

public class HomeCotroller extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Xử lý yêu cầu HTTP GET tại đây và tạo phản hồi cho trang "/trang-chu".
        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/home.jsp");
        rd.forward(request, response);
    }
}
