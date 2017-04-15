package com.example.nombu.findme;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nombu.findme.model.Insurance;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class InsuranceFragment extends Fragment {
    DatabaseReference reference;
    FirebaseAuth.AuthStateListener auth;
    String userId;

    public InsuranceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_insurance, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reference= FirebaseDatabase.getInstance().getReference().child("insurances");
        connectToFirebase();
        //from text boxes
        Insurance insurance=new Insurance(userId,"UAP","29353324","A102934","Aga Khan (Main)");
        pushToFirebase(insurance);

    }
    void connectToFirebase(){
      FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        userId=firebaseUser.getUid();

    }
    void pushToFirebase(Insurance insurance){
        reference.push().setValue(insurance);
    }

}
