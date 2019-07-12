package com.subject.sell_cake.api.admin.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PagingRequestModel {
    private String searchKey;
    private int sortCase;
    private boolean ascSort;
    private int pageNumber;
    private int pageSize;

    public String getSearchKey() {
        return searchKey;
    }

    public int getSortCase() {
        return sortCase;
    }

    public boolean isAscSort() {
        return ascSort;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }
}
