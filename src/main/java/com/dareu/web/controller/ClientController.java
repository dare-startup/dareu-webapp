package com.dareu.web.controller;

import com.dareu.web.dto.response.entity.FriendSearchDescription;
import com.dareu.web.dto.response.entity.Page;
import com.dareu.web.service.RestClientService;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alberto Rubalcaba <arubalcaba@24hourfit.com>
 */
@RestController
@RequestMapping(value = "rest/client", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
    
    @Autowired
    private RestClientService restService; 
    
    @RequestMapping("friends/find")
    public @ResponseBody Page<FriendSearchDescription> findFriends(@RequestParam("pageNumber")int pageNumber, 
                        @RequestParam("query")String query){
        return restService.findFriends(pageNumber, query);
    }
}
