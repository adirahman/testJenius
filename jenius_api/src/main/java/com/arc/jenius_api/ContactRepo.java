package com.arc.jenius_api;

import com.arc.jenius_api.response.DeleteContactByIdResponse;
import com.arc.jenius_api.response.GetContactByIdResponse;
import com.arc.jenius_api.response.GetContactResponse;
import com.arc.jenius_api.response.PostContactResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactRepo extends BaseRepo{

    public ContactRepo(ResponseCourier courier) {
        super(courier);
    }

    public void getContact(){
        JsonObject request = new JsonObject();

        try{
            JeniusClient.getInstance().getContactService().getContact(headerRequest).enqueue(new Callback<GetContactResponse>() {
                @Override
                public void onResponse(Call<GetContactResponse> call, Response<GetContactResponse> response) {
                    if(response.body() != null){
                        courier.getDataResponse(response.body(),"sukses");
                    }else{
                        courier.getDataResponse(null,"failed");
                    }
                }

                @Override
                public void onFailure(Call<GetContactResponse> call, Throwable t) {
                    courier.getDataResponse(null,t.getMessage());
                }
            });
        }catch (Exception e){
            courier.getDataResponse(null,e.getMessage());
        }
    }

    public void postContact(String firstName,String lastName,int age,String photo){
        JsonObject request = new JsonObject();
        request.addProperty("firstName",firstName);
        request.addProperty("lastName",lastName);
        request.addProperty("age",age);
        request.addProperty("photo",photo);

        try{
            JeniusClient.getInstance().getContactService().postContact(headerRequest,request.toString()).enqueue(new Callback<PostContactResponse>() {
                @Override
                public void onResponse(Call<PostContactResponse> call, Response<PostContactResponse> response) {
                    if(response.body() != null){
                        courier.getDataResponse(response.body(),"sukses");
                    }else{
                        courier.getDataResponse(null,"failed");
                    }
                }

                @Override
                public void onFailure(Call<PostContactResponse> call, Throwable t) {
                    courier.getDataResponse(null,t.getMessage());
                }
            });
        }catch (Exception e){
            courier.getDataResponse(null, e.getMessage());
        }
    }

    public void deleteContact(String id){
        try{
            JeniusClient.getInstance().getContactService().deleteContactById(headerRequest,id).enqueue(new Callback<DeleteContactByIdResponse>() {
                @Override
                public void onResponse(Call<DeleteContactByIdResponse> call, Response<DeleteContactByIdResponse> response) {
                    if(response.body() != null){
                        courier.getDataResponse(response.body(),"sukses");
                    }else{
                        courier.getDataResponse(null,"failed");
                    }
                }

                @Override
                public void onFailure(Call<DeleteContactByIdResponse> call, Throwable t) {
                    courier.getDataResponse(null,t.getMessage());
                }
            });
        }catch (Exception e){

        }
    }

    public void getContactById(String id){
        try{
            JeniusClient.getInstance().getContactService().getContactById(headerRequest,id).enqueue(new Callback<GetContactByIdResponse>() {
                @Override
                public void onResponse(Call<GetContactByIdResponse> call, Response<GetContactByIdResponse> response) {
                    if(response.body() != null){
                        courier.getDataResponse(response.body(),"sukses");
                    }else{
                        courier.getDataResponse(null,"failed");
                    }
                }

                @Override
                public void onFailure(Call<GetContactByIdResponse> call, Throwable t) {
                    courier.getDataResponse(null,t.getMessage());
                }
            });
        }catch (Exception e){
            courier.getDataResponse(null, e.getMessage());
        }
    }
}
