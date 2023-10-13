package com.laptrinhjavaweb.paging;

import com.laptrinhjavaweb.sort.Sorter;

public class PageRequest implements Pageble{
	
	private int page;
	private int maxPageItem;
	private int totalItem;
	private Sorter sorter;
	
	public PageRequest(int page, int maxPageItem,int totalItem, Sorter sorter) {
		this.page = page;
		this.maxPageItem = maxPageItem;
		this.totalItem = totalItem;
		
			this.sorter = sorter;
	}
	
	@Override
	public int getPage() {
		return this.page;
	}

	@Override
	public int getMaxPageItem() {
		return this.maxPageItem;
	}

	@Override
	public int getTotalPage() {
		return (int) Math.ceil((double)(this.getTotalItem() / getMaxPageItem()));
	}

	@Override
	public Sorter getSort() {
		return this.sorter;
	}

	@Override
	public int getOffset() {
		return (getPage() - 1) * getMaxPageItem();
	}

	@Override
	public int getTotalItem() {
		return this.totalItem;
	}

}
