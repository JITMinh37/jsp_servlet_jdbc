package com.laptrinhjavaweb.paging;

import com.laptrinhjavaweb.sort.Sorter;

public interface Pageble {
	int getPage();
	int getMaxPageItem();
	int getTotalItem();
	int getTotalPage();
	int getOffset();
	Sorter getSort();
}
