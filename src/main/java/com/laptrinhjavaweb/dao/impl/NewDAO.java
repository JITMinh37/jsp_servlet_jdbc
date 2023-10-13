package com.laptrinhjavaweb.dao.impl;

import java.util.ArrayList;

import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.mapper.NewMapper;
import com.laptrinhjavaweb.model.newsModel;
import com.laptrinhjavaweb.paging.Pageble;


public class NewDAO extends AbstractDAO<newsModel> implements INewDAO{
	
	
	@Override
	public ArrayList<newsModel> addByCategoryId(long categoryId) {
		String sql = "Select * from news where categoryid = ?";
		return query(sql, new NewMapper(), categoryId);
	}

	@Override
	public Long save(newsModel newModel) {
		String sql = "Insert into news (title, content, categoryid, thumbnail, shortdescription, createddate, createdby) values (?, ?, ?, ?, ?, ?, ?)";
		
		return insert(sql, newModel.getTitle(), newModel.getContent(), newModel.getCategoryId(), newModel.getThumbnail(), newModel.getShortDescription(), newModel.getCreatedDate(), newModel.getCreatedBy());	
	}

	@Override
	public newsModel findOne(Long id) {
		ArrayList<newsModel> listNewsModel = new ArrayList<>();
		String sql = "Select * from news where id = ?";
		listNewsModel = query(sql, new NewMapper(), id);
		return listNewsModel.isEmpty() ? null : listNewsModel.get(0);
	}

	@Override
	public void update(newsModel updateNew) {
		String sql = "Update news set title = ?, thumbnail = ?, shortdescription = ?, content = ?, categoryid = ?,"
				+ " createddate = ?, createdby = ?, modifieddate = ?, modifiedby = ? where id = ?";
		update(sql, updateNew.getTitle(), updateNew.getThumbnail(), updateNew.getShortDescription(), updateNew.getContent(),
				updateNew.getCategoryId(), updateNew.getCreatedDate(), updateNew.getCreatedBy(),
				updateNew.getModifiedDate(), updateNew.getModifiedBy(), updateNew.getId());
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		String sql = "Delete from news where id = ?";
		update(sql, id);
	}

	@Override
	public ArrayList<newsModel> listNews(Pageble pageble) {
		StringBuilder strbuild = new StringBuilder();
		strbuild.append("Select * from news");
		if(pageble.getOffset() >= 0 && pageble.getMaxPageItem() > 0 && pageble.getSort().getSortName().length() == 0 || pageble.getSort().getSortBy().length() == 0) {
			strbuild.append(" order by (select null) offset "+pageble.getOffset()+" rows fetch next "+pageble.getMaxPageItem()+" rows only");
		}
		else if(pageble.getOffset() >= 0 && pageble.getMaxPageItem() > 0 && pageble.getSort() != null) {
			strbuild.append(" order by "+pageble.getSort().getSortName()+" "+pageble.getSort().getSortBy()+" offset "+pageble.getOffset()+" rows fetch next "+pageble.getMaxPageItem()+" rows only");
		}
		return query(strbuild.toString(), new NewMapper());
	}
	

	@Override
	public int getTotalItem() {
		String sql = "Select count(*) from news";
		return count(sql);
	}
}
