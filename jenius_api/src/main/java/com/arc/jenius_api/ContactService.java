package com.arc.jenius_api;

import com.arc.jenius_api.response.DeleteContactByIdResponse;
import com.arc.jenius_api.response.GetContactByIdResponse;
import com.arc.jenius_api.response.GetContactResponse;
import com.arc.jenius_api.response.PostContactResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ContactService {

    @GET("contact")
    Call<GetContactResponse> getContact(@HeaderMap Map<String,String> header);

    @POST("contact")
    Call<PostContactResponse> postContact(@HeaderMap Map<String,String> header, @Body String request);

    @GET("contact/{id}")
    Call<GetContactByIdResponse> getContactById(@HeaderMap Map<String, String> header, @Path("id") String id);

    @DELETE("contact/{id}")
    Call<DeleteContactByIdResponse> deleteContactById(@HeaderMap Map<String,String> header, @Path("id") String id);
}
