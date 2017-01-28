package com.dareu.web.service.impl;

import com.dareu.web.dto.response.entity.Page;
import com.dareu.web.dto.response.entity.PaginationData;
import com.dareu.web.service.PaginationService;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alberto Rubalcaba <arubalcaba@24hourfit.com>
 */
@Component
public class PaginationServiceImpl implements PaginationService{

    @Override
    public PaginationData getPaginationData(Page<? extends Object> page) {
        PaginationData paginationData = new PaginationData(); 
        paginationData.setBackwardEnabled(page.getPageNumber() > 1);
        paginationData.setForwardEnabled(page.getPageNumber() < page.getPagesAvailable());
        paginationData.setPageNumber(page.getPageNumber());
        paginationData.setPagesAvailable(page.getPagesAvailable());
        return paginationData; 
    }
    
}
