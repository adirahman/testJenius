package com.arc.testjenius;

import com.arc.jenius_api.ContactModel;

import java.util.List;

public interface MainContract {
    interface View{
        void showGetAllContact(List<ContactModel> listContact);
        void showPostContactResult(String message);
        void showGetContactById(ContactModel contactModelById);
        void showDeleteContactById(String message);
    }

    interface Presenter{
        void getAllContact();
        void postContact(String firstName,String lastName,int age,String photo);
        void getContactById(String id);
        void deleteContact(String id);
    }
}
