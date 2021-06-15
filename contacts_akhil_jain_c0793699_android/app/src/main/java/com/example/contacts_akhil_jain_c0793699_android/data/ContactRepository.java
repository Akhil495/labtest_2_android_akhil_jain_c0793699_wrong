package com.example.contacts_akhil_jain_c0793699_android.data;

import android.app.Application;

import androidx.lifecycle.LiveData;


import com.example.contacts_akhil_jain_c0793699_android.model.Contact;
import com.example.contacts_akhil_jain_c0793699_android.util.ContactRoomDatabase;

import java.util.List;

public class ContactRepository {

    private ContactDao contactDao;
    private LiveData<List<Contact>> allContacts;

    public ContactRepository(Application application){
       ContactRoomDatabase db =  ContactRoomDatabase.getInstance(application);
        contactDao = db.contactDao();
        allContacts = contactDao.getAllContacts();
    }


    public LiveData<List<Contact>> getAllContacts()
    {
        return allContacts;
    }

    public LiveData<Contact> getContact(int id){
        return  contactDao.getContact(id);
    }


    public LiveData<List<Contact>> getSearchContact(String queryData){
        return   contactDao.getSearchContact(queryData);
    }

    public void insert(Contact contact){
        ContactRoomDatabase.databaseWriteExecutor.execute(() -> {
            contactDao.insert(contact);
        });
    }

    public void update(Contact contact){
        ContactRoomDatabase.databaseWriteExecutor.execute(() -> {
            contactDao.update(contact);
        });
    }

    public void delete(Contact contact){
        ContactRoomDatabase.databaseWriteExecutor.execute(() -> {
            contactDao.delete(contact);
        });
    }



}
