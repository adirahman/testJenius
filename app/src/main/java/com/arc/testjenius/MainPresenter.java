package com.arc.testjenius;

import android.util.Log;

import com.arc.jenius_api.response.BaseResponse;
import com.arc.jenius_api.ContactRepo;
import com.arc.jenius_api.response.DeleteContactByIdResponse;
import com.arc.jenius_api.response.GetContactByIdResponse;
import com.arc.jenius_api.response.GetContactResponse;
import com.arc.jenius_api.response.PostContactResponse;
import com.arc.jenius_api.ResponseCourier;

public class MainPresenter implements MainContract.Presenter, ResponseCourier {

    MainContract.View view;
    ContactRepo repo;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        repo = new ContactRepo(this);
    }

    @Override
    public void getAllContact() {
        repo.getContact();
    }

    @Override
    public void postContact(String firstName, String lastName, int age, String photo) {
        repo.postContact(firstName,lastName,age,photo);
    }

    @Override
    public void getContactById(String id) {
        repo.getContactById(id);
    }

    @Override
    public void deleteContact(String id) {
        repo.deleteContact(id);
    }

    @Override
    public void getDataResponse(BaseResponse response, String message) {
        if(response != null && response instanceof GetContactResponse){
            GetContactResponse dataRaw = (GetContactResponse) response;
            view.showGetAllContact(dataRaw.data);
        }else if(response != null && response instanceof PostContactResponse){
            PostContactResponse dataRaw = (PostContactResponse) response;
            view.showPostContactResult(dataRaw.message);
        } else if(response != null && response instanceof DeleteContactByIdResponse){
            DeleteContactByIdResponse dataRaw = (DeleteContactByIdResponse) response;
            view.showDeleteContactById(dataRaw.message);
        }else if(response != null && response instanceof GetContactByIdResponse){
            GetContactByIdResponse dataRaw = (GetContactByIdResponse) response;
            view.showGetContactById(dataRaw.data);
        }else{
            Log.d("MainPresenter",message);
        }
    }
}
