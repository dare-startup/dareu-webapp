package com.dareu.web.mgr.impl;

import com.dareu.web.conn.AdminMethodName;
import com.dareu.web.conn.ApacheConnectorService;
import com.dareu.web.conn.ApacheResponseWrapper;
import com.dareu.web.conn.ProtectedMethodName;
import com.dareu.web.conn.cxt.JsonParserService;
import com.dareu.web.dto.request.CreateCategoryRequest;
import com.dareu.web.dto.request.CreateDareRequest;
import com.dareu.web.dto.request.DareConfirmationRequest;
import com.dareu.web.dto.response.EntityRegistrationResponse;
import com.dareu.web.dto.response.UpdatedEntityResponse;
import com.dareu.web.dto.response.entity.ActiveDare;
import com.dareu.web.dto.response.entity.CategoryDescription;
import com.dareu.web.dto.response.entity.DareDescription;
import com.dareu.web.dto.response.entity.DiscoverUserAccount;
import com.dareu.web.dto.response.entity.FriendSearchDescription;
import com.dareu.web.dto.response.entity.Page;
import com.dareu.web.dto.response.entity.UnacceptedDare;
import com.dareu.web.dto.response.entity.UserAccount;
import com.dareu.web.exception.ConnectorManagerException;
import com.dareu.web.mgr.ConnectorManager;
import com.dareu.web.mgr.FileManager;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
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
    private FileManager fileManager;

    @Autowired
    private JsonParserService jsonParser;

    private static final String SERVER_ERROR = "The server encountered an error and returned status 500\nBody: %s";
    private static final String NOT_FOUND = "Not Found: %s";
    private static final String NOT_REGISTERED_STATUS_CODE = "Not registered on statusCode: StatusCode %d\nResponse: %s\nContextPath: %s";
    private static final String NOT_REGISTERED_EXCEPTION_MESSAGE = "Got not registered status code";
    private static final String IOEXCEPTION_MESSAGE = "ConnectorManagerException: %s";

    public ConnectorManagerImpl() {
    }

    @Override
    public Page<CategoryDescription> getCategories(int pageNumber) throws ConnectorManagerException {
        try {
            //get categories 
            String categoriesContextPath = AdminMethodName.CATEGORIES.toString();
            ApacheResponseWrapper wrapper = connector.performSuperAdminGetOperation(categoriesContextPath);
            Type type = new TypeToken<Page<CategoryDescription>>() {
            }.getType();
            return processResponse(wrapper, type, categoriesContextPath);
        } catch (IOException ex) {
            throw new ConnectorManagerException(String.format(IOEXCEPTION_MESSAGE, ex.getMessage()), ex);
        }
    }

    @Override
    public Page<UserAccount> getUsersByPage(int pageNumber) throws ConnectorManagerException {
        String contextPath = String.format(AdminMethodName.USERS_BY_PAGE.toString(), pageNumber);
        //get a list of users
        try {
            ApacheResponseWrapper wrapper = connector.performSuperAdminGetOperation(contextPath);
            Type type = new TypeToken<Page<UserAccount>>() {
            }.getType();
            return processResponse(wrapper, type, contextPath);
        } catch (IOException ex) {
            throw new ConnectorManagerException(String.format(IOEXCEPTION_MESSAGE, ex.getMessage()), ex);
        }
    }

    @Override
    public Page<DareDescription> getUnapprovedDares(int pageNumber) throws ConnectorManagerException {
        try {
            //create request 
            String contextPath = String.format(AdminMethodName.UNAPPROVED_DARES.toString(), pageNumber);

            ApacheResponseWrapper wrapper = connector.performSuperAdminGetOperation(contextPath);
            Type type = new TypeToken<Page<DareDescription>>() {
            }.getType();
            return processResponse(wrapper, type, contextPath);
        } catch (IOException ex) {
            throw new ConnectorManagerException(String.format(IOEXCEPTION_MESSAGE, ex.getMessage()), ex);
        }
    }

    @Override
    public EntityRegistrationResponse createCategory(CreateCategoryRequest request) throws ConnectorManagerException {
        //create a category description 
        String newCategoryCxtPath = AdminMethodName.NEW_CATEGORY.toString();
        try {
            ApacheResponseWrapper wrapper = connector.performSuperAdminPostOperation(newCategoryCxtPath, request);
            return processResponse(wrapper, EntityRegistrationResponse.class, newCategoryCxtPath);
        } catch (IOException ex) {
            throw new ConnectorManagerException(String.format(IOEXCEPTION_MESSAGE, ex.getMessage()), ex);
        }
    }

    @Override
    public Page<DiscoverUserAccount> getDiscoverUsers(int pageNumber, String token) throws ConnectorManagerException {
        String contextPath = String.format(AdminMethodName.DISCOVER_USERS.toString(), pageNumber);
        try {
            ApacheResponseWrapper wrapper = connector.performProtectedGetOperation(contextPath, token);
            Type type = new TypeToken<Page<DiscoverUserAccount>>() {
            }.getType();
            return processResponse(wrapper, type, contextPath);
        } catch (IOException ex) {
            throw new ConnectorManagerException(String.format(IOEXCEPTION_MESSAGE, ex.getMessage()), ex);
        }
    }

    @Override
    public EntityRegistrationResponse requestFriendship(String requestUserId, String token) throws ConnectorManagerException {
        String contextPath = String.format(ProtectedMethodName.REQUEST_FRIENDSHIP.toString(), requestUserId);
        try {
            ApacheResponseWrapper wrapper = connector.performProtectedPostOperation(contextPath, null, token);

            return processResponse(wrapper, EntityRegistrationResponse.class, contextPath);
        } catch (IOException ex) {
            throw new ConnectorManagerException(String.format(IOEXCEPTION_MESSAGE, ex.getMessage()), ex);
        }
    }

    @Override
    public Page<FriendSearchDescription> findAvailableFriends(int pageNumber, String token) throws ConnectorManagerException {
        String contextPath = String.format(ProtectedMethodName.FIND_FRIENDS.toString(), pageNumber);
        try {
            ApacheResponseWrapper wrapper = connector.performProtectedGetOperation(contextPath, token);
            Type type = new TypeToken<Page<FriendSearchDescription>>() {
            }.getType();
            return processResponse(wrapper, type, contextPath);
        } catch (IOException ex) {
            throw new ConnectorManagerException(String.format(IOEXCEPTION_MESSAGE, ex.getMessage()), ex);
        }
    }

    private <T> T processResponse(ApacheResponseWrapper wrapper, Class<T> type, String cxtPath) throws ConnectorManagerException {
        switch (wrapper.getStatusCode()) {
            case 200:
                if (!wrapper.getJsonResponse().isEmpty()) {
                    return jsonParser.parseJson(wrapper.getJsonResponse(), type);
                } else {
                    return null;
                }
            case 404:
                throw new ConnectorManagerException(String.format(NOT_FOUND, cxtPath));
            case 500:
                throw new ConnectorManagerException(String.format(SERVER_ERROR, wrapper.getJsonResponse()));
            default:
                log.info(String.format(NOT_REGISTERED_STATUS_CODE,
                        wrapper.getStatusCode(), wrapper.getJsonResponse(), wrapper.getContextPath()));
                throw new ConnectorManagerException(NOT_REGISTERED_EXCEPTION_MESSAGE);
        }
    }

    private <T> T processResponse(ApacheResponseWrapper wrapper, Type type, String cxtPath) throws ConnectorManagerException {
        switch (wrapper.getStatusCode()) {
            case 200:
                T t = jsonParser.parseJson(wrapper.getJsonResponse(), type);
                return t;
            case 404:
                throw new ConnectorManagerException(String.format(NOT_FOUND, cxtPath));
            case 500:
                throw new ConnectorManagerException(String.format(SERVER_ERROR, wrapper.getJsonResponse()));
            default:
                log.info(String.format(NOT_REGISTERED_STATUS_CODE,
                        wrapper.getStatusCode(), wrapper.getJsonResponse(), wrapper.getContextPath()));
                throw new ConnectorManagerException(NOT_REGISTERED_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public Page<FriendSearchDescription> findAvailableFriends(int pageNumber, String query, String token) throws ConnectorManagerException {
        String contextPath = String.format(ProtectedMethodName.FIND_FRIENDS_BY_QUERY.toString(), query, pageNumber);
        try {
            ApacheResponseWrapper wrapper = connector.performProtectedGetOperation(contextPath, token);
            Type type = new TypeToken<Page<FriendSearchDescription>>() {
            }.getType();
            return processResponse(wrapper, type, contextPath);
        } catch (IOException ex) {
            throw new ConnectorManagerException(String.format(IOEXCEPTION_MESSAGE, ex.getMessage()), ex);
        }
    }

    @Override
    public EntityRegistrationResponse updateFriendshipRequest(String userId, Boolean accepted, String token) throws ConnectorManagerException {
        String contextPath = String.format(ProtectedMethodName.UPDATE_FRIENDSHIP.toString(), userId, accepted.toString());
        try {
            ApacheResponseWrapper wrapper = connector.performProtectedPostOperation(contextPath, null, token);
            return processResponse(wrapper, EntityRegistrationResponse.class, contextPath);
        } catch (IOException ex) {
            throw new ConnectorManagerException("Could not update friendship: " + ex.getMessage());
        }
    }

    @Override
    public EntityRegistrationResponse createDare(CreateDareRequest request, String token) throws ConnectorManagerException {
        String contextPath = ProtectedMethodName.CREATE_DARE.toString();
        try {
            ApacheResponseWrapper wrapper = connector.performProtectedPostOperation(contextPath, request, token);
            return processResponse(wrapper, EntityRegistrationResponse.class, contextPath);
        } catch (IOException ex) {
            throw new ConnectorManagerException("Could not create new dare: " + ex.getMessage(), ex);
        }
    }

    @Override
    public UnacceptedDare getUnacceptedDare(String token) throws ConnectorManagerException {
        String contextPath = ProtectedMethodName.UNACCEPTED_DARE.toString();
        try {
            ApacheResponseWrapper wrapper = connector.performProtectedGetOperation(contextPath, token);
            return processResponse(wrapper, UnacceptedDare.class, contextPath);
        } catch (IOException ex) {
            throw new ConnectorManagerException("Could not get unaccepted dare: " + ex.getMessage(), ex);
        }
    }

    @Override
    public UpdatedEntityResponse confirmDareRequest(DareConfirmationRequest request, String token) throws ConnectorManagerException {
        String contextPath = ProtectedMethodName.DARE_CONFIRMATION.toString();
        try {
            ApacheResponseWrapper wrapper = connector.performProtectedPostOperation(contextPath, request, token);
            return processResponse(wrapper, UpdatedEntityResponse.class, contextPath);
        } catch (IOException ex) {
            throw new ConnectorManagerException("Could not confirm dare: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Page<DareDescription> discoverDares(int pageNumber, String token) throws ConnectorManagerException {
        String cxtPath = String.format(ProtectedMethodName.DISCOVER_DARES.toString(), pageNumber);
        Type type = new TypeToken<Page<DareDescription>>() {
        }.getType();
        try {
            ApacheResponseWrapper wrapper = connector.performProtectedGetOperation(cxtPath, token);
            return processResponse(wrapper, type, cxtPath);
        } catch (IOException ex) {
            throw new ConnectorManagerException("Could not get discoverable dares: " + ex.getMessage());
        }
    }

    @Override
    public ActiveDare getCurrentActiveDare(String token) throws ConnectorManagerException {
        String path = ProtectedMethodName.ACTIVE_DARE.toString();
        try {
            ApacheResponseWrapper wrapper = connector.performProtectedGetOperation(path, token);
            return processResponse(wrapper, ActiveDare.class, path);
        } catch (IOException ex) {
            throw new ConnectorManagerException("Could not get currently active dare: " + ex.getMessage());
        }
    }

    @Override
    public byte[] getProfileImage(String userId, String token) throws ConnectorManagerException {
        String path;
        if (userId == null || userId.isEmpty()) {
            path = ProtectedMethodName.GET_IMAGE_PROFILE.toString();
        } else {
            path = ProtectedMethodName.GET_IMAGE_PROFILE.toString() + "?userId=" + userId;
        }

        try {
            byte[] bytes = connector.loadImageProfile(path, token);
            return bytes;
        } catch (IOException ex) {
            throw new ConnectorManagerException(ex.getMessage());
        }
    }

    @Override
    public DareDescription findDareDescription(String dareId, String token) throws ConnectorManagerException {
        String path = ProtectedMethodName.FIND_DARE_DESCRIPTION.toString() + dareId;
        try {
            ApacheResponseWrapper wrapper = connector.performProtectedGetOperation(path, token);
            return processResponse(wrapper, DareDescription.class, path);
        } catch (IOException ex) {
            throw new ConnectorManagerException(ex.getMessage());
        }
    }

    @Override
    public EntityRegistrationResponse uploadDareResponse(InputStream stream, String dareId, String comment, String token) throws ConnectorManagerException {
        String path = ProtectedMethodName.UPLOAD_DARE_RESPONSE.toString();
        try {
            
            //save file before sending 
            String fileName = fileManager.saveTmpFile(stream, dareId);
            //create map 
            Map<String, String> params = new HashMap();
            params.put("comment", comment);
            params.put("dareId", dareId); 
            //perform request
            ApacheResponseWrapper wrapper = connector.performProtectedMultipartPostOperation(path, fileName, params, token);
            //delete tmp file 
            fileManager.deleteTmpFile(fileName);
            //return wrapper
            return processResponse(wrapper, EntityRegistrationResponse.class, path);
        } catch (IOException ex) {
            throw new ConnectorManagerException(ex.getMessage());

        }
    }

}
