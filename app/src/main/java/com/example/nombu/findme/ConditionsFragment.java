package com.example.nombu.findme;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nombu.findme.model.Conditions;
import com.example.nombu.findme.model.Insurance;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConditionsFragment extends Fragment {
    DatabaseReference reference;
    FirebaseAuth.AuthStateListener auth;
    String userId;

    public ConditionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conditions, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reference= FirebaseDatabase.getInstance().getReference().child("conditions");
        connectToFirebase();
        //from text boxes

        ArrayList<String> otherConditions = new ArrayList<>();
        otherConditions.add("Pre-existing asthma");
        otherConditions.add("Diabetes");

        Conditions conditions=new Conditions(userId,"AB","-rh", otherConditions);
        pushToFirebase(conditions);

    }
    void connectToFirebase(){
        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        userId=firebaseUser.getUid();
    }
    void pushToFirebase( Conditions conditions){
        reference.push().setValue(conditions);
    }

}


