package com.dareu.web.service;

import com.dareu.web.dto.response.entity.Page;
import com.dareu.web.dto.response.entity.PaginationData;

/**
 *
 * @author Alberto Rubalcaba <arubalcaba@24hourfit.com>
 */
public interface PaginationService {
    
    /**
     * Get pagination data to display on every page
     * @param page
     * @return 
     */
    public PaginationData getPaginationData(Page<? extends Object> page); 
    
}
