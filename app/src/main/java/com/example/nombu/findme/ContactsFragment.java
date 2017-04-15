package com.example.nombu.findme;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nombu.findme.model.Contacts;
import com.example.nombu.findme.model.Insurance;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {
    DatabaseReference reference;
    FirebaseAuth.AuthStateListener auth;
    String userId;

    public ContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reference= FirebaseDatabase.getInstance().getReference().child("contacts");
        connectToFirebase();
        //from text boxes
        Contacts contacts=new Contacts(userId,"Dad", "0711809296");
        pushToFirebase(contacts);

    }
    void connectToFirebase(){
        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        userId=firebaseUser.getUid();
    }
    void pushToFirebase(Contacts contacts){
        reference.push().setValue(contacts);
    }


}

