package com.dareu.web.mgr;

import com.dareu.web.dto.request.CreateCategoryRequest;
import com.dareu.web.dto.response.EntityRegistrationResponse;
import com.dareu.web.dto.response.entity.CategoryDescription;
import com.dareu.web.dto.response.entity.DareDescription;
import com.dareu.web.dto.response.entity.DiscoverUserAccount;
import com.dareu.web.dto.response.entity.FriendSearchDescription;
import com.dareu.web.dto.response.entity.Page;
import com.dareu.web.dto.response.entity.UserAccount;
import com.dareu.web.exception.ConnectorManagerException;
import org.springframework.stereotype.Component;

/**
 *
 * @author jose.rubalcaba
 */
public interface ConnectorManager {
    
    /**
     * Obtains a page of categories
     * @param pageNumber
     * This operation must be executed by any authenticated user
     * @return 
     * @throws com.dareu.web.exception.ConnectorManagerException 
     */
    public Page<CategoryDescription> getCategories(int pageNumber)throws ConnectorManagerException; 
    
    /**
     * Obtains a page of user account 
     * This operation must be executed by an administrator account
     * @param pageNumber
     * @return
     * @throws ConnectorManagerException 
     */
    public Page<UserAccount> getUsersByPage(int pageNumber) throws ConnectorManagerException;
    
    /**
     * Obtains a list of unapproved dares
     * This operation must be executed by an administrator account
     * @param pageNumber
     * @return
     * @throws ConnectorManagerException 
     */
    public Page<DareDescription> getUnapprovedDares(int pageNumber) throws ConnectorManagerException;
    
    /**
     * Creates a new category
     * This operation must be executed by an administrator account
     * @param request
     * @return
     * @throws ConnectorManagerException 
     */
    public EntityRegistrationResponse createCategory(CreateCategoryRequest request)throws ConnectorManagerException;
    
    /**
     * Get a discoverable page of users 
     * This operation must be executed by any authenticated user
     * @param pageNumber
     * @param token
     * @return
     * @throws ConnectorManagerException 
     */
    public Page<DiscoverUserAccount> getDiscoverUsers(int pageNumber, String token)throws ConnectorManagerException;

    /**
     * Requests a new friendship
     * This operation must be executed by any authenticated user
     * @param requestedUserId
     * @param token
     * @return 
     * @throws com.dareu.web.exception.ConnectorManagerException 
     */
    public EntityRegistrationResponse requestFriendship(String requestedUserId, String token)throws ConnectorManagerException;

    /**
     * Get a list of friends in a short description object
     * This operation must be executed by any authenticated user
     * @param i
     * @param token
     * @return
     * @throws ConnectorManagerException 
     */
    public Page<FriendSearchDescription> findAvailableFriends(int i, String token) throws ConnectorManagerException;
    
    /**
     * Get a list of friends in a short description object
     * This operation must be executed by any authenticated user
     * @param pageNumber
     * @param query
     * @param token
     * @return
     * @throws ConnectorManagerException 
     */
    public Page<FriendSearchDescription> findAvailableFriends(int pageNumber, String query, String token) throws ConnectorManagerException;

    /**
     * Updates a friendship request to the accepted value
     * @param userId
     * @param accepted
     * @param token 
     * @return  
     */
    public EntityRegistrationResponse updateFriendshipRequest(String userId, Boolean accepted, String token)throws ConnectorManagerException;
}
