package com.arc.jenius_api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class JeniusClient {

    public static final String BASE_URL = "https://simple-contact-crud.herokuapp.com/";

    private static JeniusClient instance;
    private static Retrofit retrofit;

    private static ContactService contactService;

    private JeniusClient(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggingInterceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }

    public static JeniusClient getInstance(){
        if(instance == null){
            instance = new JeniusClient();
        }
        return instance;
    }

    public static ContactService getContactService(){
        contactService = retrofit.create(ContactService.class);
        return contactService;
    }
}
