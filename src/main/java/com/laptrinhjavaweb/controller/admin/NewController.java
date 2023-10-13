package com.laptrinhjavaweb.controller.admin;
import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.controller.constrant.SourceConstrant;
import com.laptrinhjavaweb.model.newsModel;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.sort.Sorter;
import com.laptrinhjavaweb.utils.FormUtil;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/admin-new-list"}) //khi một yêu cầu HTTP được gửi đến địa chỉ web của ứng dụng với đường dẫn "/admin-home", Servlet này sẽ được gọi để xử lý yêu cầu.



public class NewController extends HttpServlet{
	
	@Inject
	private INewService newService;
	
	@Inject
	private ICategoryService categoryService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		newsModel news = FormUtil.toModel(newsModel.class, request);
		String view = "";
		if(news.getType().equals("edit")) {
			if(news.getId()!=null) {
				news = newService.findOne(news.getId());
			}
			request.setAttribute("categories", categoryService.findListCategory());
			view = "/views/admin/new/edit.jsp";
		}else if(news.getType().equals("list")) {
			news.setTotalItem(newService.getTotalItem());
			Pageble pageble = new PageRequest(news.getPage(), news.getMaxPageItem(), news.getTotalItem(), new Sorter(news.getSortName(), news.getSortBy()));
			news.setListResult(newService.listNew(pageble));
			news.setTotalPage(pageble.getTotalPage());
			view = "/views/admin/new/NewList.jsp";
		}
		
		request.setAttribute(SourceConstrant.MODEL, news);
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
       
    }
}