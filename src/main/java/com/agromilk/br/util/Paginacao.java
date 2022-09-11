package com.agromilk.br.util;

import org.springframework.data.domain.Pageable;

public class Paginacao {

    public Paginacao() {

    }

    public static final int DEFAULT_PAGE_NUMBER = 0;
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final String DEFAULT_SORT_DIRECTION = "ASC";


    /**
     * adjust for offset on database, that must start with ZERO
     * @param pageable
     * @return
     * @throws Exception
     */
    public static Integer getPageOffsetFromPageable(Pageable pageable) throws Exception {
        Integer pageSize = 0;
        Integer currentPageSize = pageable.getPageNumber();
        if(currentPageSize < 0){
            throw new Exception("Page must not be negative!");
        }else if(currentPageSize > 0){
            pageSize = currentPageSize - 1;
        }
        return pageSize;
    }
}
