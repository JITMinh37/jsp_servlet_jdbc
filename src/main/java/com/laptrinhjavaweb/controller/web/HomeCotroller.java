package com.laptrinhjavaweb.controller.web;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.model.userModel;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.SessionUtil;


@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/trang-chu", "/dang-nhap", "/thoat"}) //khi một yêu cầu HTTP được gửi đến địa chỉ web của ứng dụng với đường dẫn "/trang-chu", Servlet này sẽ được gọi để xử lý yêu cầu.

public class HomeCotroller extends HttpServlet{
	@Inject
	private ICategoryService categoryService;
	@Inject
	private INewService newService;
	@Inject
	private IUserService userService;
	
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Xử lý yêu cầu HTTP GET tại đây và tạo phản hồi cho trang "/trang-chu".
//		userModel usermodel = new userModel();
//		usermodel.setFullName("Hello World");
//		request.setAttribute("model", usermodel);
		//request.setAttribute("listCategory", categoryService.addListCategory());
//		long categoryId = 1;
//		request.setAttribute("listNewsByCategoryId", newService.addByCategoryId(categoryId));
//		String title = "Bài viết 6";
//		String content = "Bai viet 6";
//		Long categoryId = 3L;
//		newsModel news = new newsModel();
//		news.setTitle(title);
//		news.setContent(content);
//		news.setCategoryId(categoryId);
//		request.setAttribute("saveNews",newService.save(news));
		String action = request.getParameter("action");
		if(action != null && action.equals("login")) {
			String message = request.getParameter("message");
        	String alert = request.getParameter("alert");
        	if(message != null && alert != null) {
        		request.setAttribute("messages", resourceBundle.getString(message)); // Những key như mesages và alert là những key được đánh dấu trên login.jsp. Khi ta setAtriibute tức là gửi giá trị cho các key đó hiển thị lên jsp.
        		request.setAttribute("alert", alert);
        	}
			RequestDispatcher rd = request.getRequestDispatcher("/views/login/login.jsp");
	        rd.forward(request, response);
		}else if(action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(request, "USERMODEL");
			response.sendRedirect(request.getContextPath() + "/trang-chu");
		}else {			
			RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(request, response);
		}
    }
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if(action != null && action.equals("login")) {       	
        	userModel user= FormUtil.toModel(userModel.class, request); // Lấy tất cả các parametter ở phía client gửi(Khi nhấn nút submit) và map vào usermodel 
        	user = userService.findUserByUserNameAndPasswordAndStatus(user.getUserName(), user.getPassWord(), 1);
        	if(user != null) {
        		SessionUtil.getInstance().putValue(request, "USERMODEL", user);
        		if(user.getRole().getCode().equals("1")) {
        			response.sendRedirect(request.getContextPath() + "/trang-chu");
        		}else if(user.getRole().getCode().equals("0")) {
        			response.sendRedirect(request.getContextPath() + "/admin-home");
        		}
        	}else {
        		response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=username_password_invalid&alert=danger"); // Nếu user == null(tức là không tìm thấy user và password hợp lệ) thì gửi lại form đăng nhập
        	}
        }        
    }
}
