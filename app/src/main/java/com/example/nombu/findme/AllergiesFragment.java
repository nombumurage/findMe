package com.example.nombu.findme;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nombu.findme.model.Allergies;
import com.example.nombu.findme.model.Conditions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AllergiesFragment extends Fragment {
    DatabaseReference reference;
    FirebaseAuth.AuthStateListener auth;
    String userId;

    public AllergiesFragment() {
            // Required empty public constructor
    }

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_allergies, container, false);
        }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reference= FirebaseDatabase.getInstance().getReference().child("allergies");
        connectToFirebase();
        //from text boxes

        ArrayList<String> allergyList = new ArrayList<>();
        allergyList.add("ibuprofen");
        allergyList.add("naproxen");

        Allergies allergies=new Allergies(userId,allergyList);
        pushToFirebase(allergies);

    }
    void connectToFirebase(){
        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        userId=firebaseUser.getUid();
    }
    void pushToFirebase( Allergies allergies){
        reference.push().setValue(allergies);
    }
}

