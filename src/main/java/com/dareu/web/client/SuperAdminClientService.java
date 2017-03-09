package com.dareu.web.client;

import com.dareu.web.dto.request.CreateCategoryRequest;
import com.dareu.web.dto.response.EntityRegistrationResponse;
import com.dareu.web.dto.response.entity.DareDescription;
import com.dareu.web.dto.response.entity.Page;
import com.dareu.web.dto.response.entity.UserAccount;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 *
 * @author Alberto Rubalcaba <arubalcaba@24hourfit.com>
 */
public interface SuperAdminClientService {
    
    @GET("admin/account/findUserByEmail/{email}")
    Call<UserAccount> findUserByEmail(@Path(value = "email")String email, 
                                      @Header(value = "Authorization")String adminToken);
    
    @GET("admin/account/findUsers")
    Call<Page<UserAccount>> findUsers(@Query(value = "pageNumber")int pageNumber, 
                                      @Header(value = "Authorization") String token);
    
    @GET("admin/dare/unapproved")
    Call<Page<DareDescription>> findUnapprovedDares(@Query(value = "pageNumber")int pageNumber, 
                                                    @Header(value = "Authorization")String token);
    @POST("admin/dare/category/create")
    Call<EntityRegistrationResponse> createCategory(@Body CreateCategoryRequest request, 
                                                    @Header(value = "Authorization")String token);
}
