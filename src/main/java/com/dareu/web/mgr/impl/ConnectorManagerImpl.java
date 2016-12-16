package com.dareu.web.mgr.impl;

import com.dareu.web.conn.AdminMethodName;
import com.dareu.web.conn.ApacheConnectorService;
import com.dareu.web.conn.ApacheResponseWrapper;
import com.dareu.web.conn.cxt.JsonParserService;
import com.dareu.web.dto.request.CreateCategoryRequest;
import com.dareu.web.dto.response.EntityRegistrationResponse;
import com.dareu.web.dto.response.entity.CategoryDescription;
import com.dareu.web.dto.response.entity.DareDescription;
import com.dareu.web.dto.response.entity.DiscoverUserAccount;
import com.dareu.web.dto.response.entity.Page;
import com.dareu.web.dto.response.entity.UserAccount;
import com.dareu.web.exception.ConnectorManagerException;
import com.dareu.web.mgr.ConnectorManager;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jose.rubalcaba
 *
 */
@Component
public class ConnectorManagerImpl implements ConnectorManager {

    private static final Logger log = Logger.getLogger(ConnectorManager.class.getName());

    @Autowired
    private ApacheConnectorService connector;

    @Autowired
    private JsonParserService jsonParser;

    private static final String SERVER_ERROR = "The server encountered an error and returned status 500\nBody: %s";
    private static final String NOT_FOUND = "Not Found: %s";
    private static final String NOT_REGISTERED_STATUS_CODE = "Not registered on statusCode: StatusCode %d\nResponse: %s\nContextPath: %s";
    private static final String NOT_REGISTERED_EXCEPTION_MESSAGE = "Got not registered status code";
    private static final String IOEXCEPTION_MESSAGE = "ConnectorManagerException: %s";

    public ConnectorManagerImpl() {
    }

    public Page<CategoryDescription> getCategories(int pageNumber) throws ConnectorManagerException {
        try {
            //get categories 
            String categoriesContextPath = AdminMethodName.CATEGORIES.toString();
            ApacheResponseWrapper wrapper = connector.performSuperAdminGetOperation(categoriesContextPath);
            Type type = new TypeToken<Page<CategoryDescription>>() {
            }.getType();
            switch (wrapper.getStatusCode()) {
                case 200:
                    Page<CategoryDescription> categories = jsonParser
                            .<Page<CategoryDescription>>parseJson(wrapper.getJsonResponse(), type);
                    return categories;
                case 404:
                    throw new ConnectorManagerException(String.format(NOT_FOUND, categoriesContextPath));
                case 500:
                    throw new ConnectorManagerException(String.format(SERVER_ERROR, wrapper.getJsonResponse()));
                default:
                    log.info(String.format(NOT_REGISTERED_STATUS_CODE,
                            wrapper.getStatusCode(), wrapper.getJsonResponse(), wrapper.getContextPath()));
                    throw new ConnectorManagerException(NOT_REGISTERED_EXCEPTION_MESSAGE);
            }
        } catch (IOException ex) {
            throw new ConnectorManagerException(String.format(IOEXCEPTION_MESSAGE, ex.getMessage()), ex);
        }
    }

    public Page<UserAccount> getUsersByPage(int pageNumber) throws ConnectorManagerException {
        String contextPath = String.format(AdminMethodName.USERS_BY_PAGE.toString(), pageNumber);
        //get a list of users
        try {
            ApacheResponseWrapper wrapper = connector.performSuperAdminGetOperation(contextPath);
            switch (wrapper.getStatusCode()) {
                case 200:
                    //create a page 
                    Page<UserAccount> account = jsonParser.parseJson(wrapper.getJsonResponse(),
                            new TypeToken<Page<UserAccount>>() {
                            }.getType());
                    return account;
                case 404:
                    throw new ConnectorManagerException(String.format(NOT_FOUND, contextPath));
                case 500:
                    throw new ConnectorManagerException(String.format(SERVER_ERROR, wrapper.getJsonResponse()));
                default:
                    log.info(String.format(NOT_REGISTERED_STATUS_CODE,
                            wrapper.getStatusCode(), wrapper.getJsonResponse(), wrapper.getContextPath()));
                    throw new ConnectorManagerException(NOT_REGISTERED_EXCEPTION_MESSAGE);
            }
        } catch (IOException ex) {
            throw new ConnectorManagerException(String.format(IOEXCEPTION_MESSAGE, ex.getMessage()), ex);
        }
    }

    public Page<DareDescription> getUnapprovedDares(int pageNumber) throws ConnectorManagerException {
        try {
            //create request 
            String contextPath = String.format(AdminMethodName.UNAPPROVED_DARES.toString(), pageNumber); 

            ApacheResponseWrapper wrapper = connector.performSuperAdminGetOperation(contextPath);
            Type type = new TypeToken<Page<DareDescription>>() {
            }.getType();
            switch (wrapper.getStatusCode()) {
                case 200:
                    //get the list of dares
                    Page<DareDescription> page = jsonParser.parseJson(wrapper.getJsonResponse(), type);
                    return page; 
                case 404:
                    throw new ConnectorManagerException(String.format(NOT_FOUND, contextPath));
                case 500:
                    throw new ConnectorManagerException(String.format(SERVER_ERROR, wrapper.getJsonResponse()));
                default:
                    log.info(String.format(NOT_REGISTERED_STATUS_CODE,
                            wrapper.getStatusCode(), wrapper.getJsonResponse(), wrapper.getContextPath()));
                    throw new ConnectorManagerException(NOT_REGISTERED_EXCEPTION_MESSAGE);
            }
        } catch (IOException ex) {
            throw new ConnectorManagerException(String.format(IOEXCEPTION_MESSAGE, ex.getMessage()), ex);
        }
    }

    public EntityRegistrationResponse createCategory(CreateCategoryRequest request) throws ConnectorManagerException {
        //create a category description 
        String newCategoryCxtPath = AdminMethodName.NEW_CATEGORY.toString();
        try {
            ApacheResponseWrapper wrapper = connector.performSuperAdminPostOperation(newCategoryCxtPath, request);
            switch (wrapper.getStatusCode()) {
                case 200:
                    EntityRegistrationResponse response = jsonParser
                            .parseJson(wrapper.getJsonResponse(), EntityRegistrationResponse.class);
                    log.info(String.format("Registered a new %s with id %s", response.getRegistrationType(), response.getId()));
                    return response; 
                case 404:
                    throw new ConnectorManagerException(String.format(NOT_FOUND, newCategoryCxtPath));
                case 500:
                    throw new ConnectorManagerException(String.format(SERVER_ERROR, wrapper.getJsonResponse()));
                default:
                    log.info(String.format(NOT_REGISTERED_STATUS_CODE,
                            wrapper.getStatusCode(), wrapper.getJsonResponse(), wrapper.getContextPath()));
                    throw new ConnectorManagerException(NOT_REGISTERED_EXCEPTION_MESSAGE);
            }
        } catch (IOException ex) {
            throw new ConnectorManagerException(String.format(IOEXCEPTION_MESSAGE, ex.getMessage()), ex);
        }
    }

    @Override
    public Page<DiscoverUserAccount> getDiscoverUsers(int pageNumber, String token) throws ConnectorManagerException {
        String contextPath = String.format(AdminMethodName.DISCOVER_USERS.toString(), pageNumber);
        try {
            ApacheResponseWrapper wrapper = connector.performProtectedGetOperation(contextPath, token);
            Type type = new TypeToken<Page<DiscoverUserAccount>>(){}.getType(); 
            switch (wrapper.getStatusCode()) {
                case 200:
                    Page<DiscoverUserAccount> response = jsonParser
                            .parseJson(wrapper.getJsonResponse(), type);
                    
                    return response; 
                case 404:
                    throw new ConnectorManagerException(String.format(NOT_FOUND, contextPath));
                case 500:
                    throw new ConnectorManagerException(String.format(SERVER_ERROR, wrapper.getJsonResponse()));
                default:
                    log.info(String.format(NOT_REGISTERED_STATUS_CODE,
                            wrapper.getStatusCode(), wrapper.getJsonResponse(), wrapper.getContextPath()));
                    throw new ConnectorManagerException(NOT_REGISTERED_EXCEPTION_MESSAGE);
            }
        } catch (IOException ex) {
            throw new ConnectorManagerException(String.format(IOEXCEPTION_MESSAGE, ex.getMessage()), ex);
        }
    }

}
