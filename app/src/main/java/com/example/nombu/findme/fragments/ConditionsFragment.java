package com.example.nombu.findme.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nombu.findme.R;
import com.example.nombu.findme.model.Conditions;
import com.example.nombu.findme.model.Insurance;
import com.example.nombu.findme.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConditionsFragment extends Fragment implements View.OnClickListener {
    DatabaseReference reference;
    FirebaseAuth.AuthStateListener auth;
    String userId;

    @Bind(R.id.bloodGroup_Spinner)
    Spinner mbloodGroupSpinner;
    @Bind(R.id.rhesus_Spinner)
    Spinner mrhesusSpinner;
    @Bind(R.id.otherConditions)
    EditText mOtherConditions;
    @Bind(R.id.userConditionButton)
    Button mConditionButton;

    public ConditionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_conditions, container, false);
        ButterKnife.bind(this, view);
        mConditionButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ArrayList<String> otherConditions = new ArrayList<>();
//        otherConditions.add("Pre-existing asthma");
//        otherConditions.add("Diabetes");
//
//        Conditions conditions=new Conditions(userId,"AB","-rh", otherConditions);
//        pushToFirebase(conditions);
    }

    @Override
    public void onClick (View view) {
        saveToFirebase();
        mOtherConditions.setVisibility(View.INVISIBLE);
        Fragment fragment_allergies = new AllergiesFragment();
        changeFragments(fragment_allergies);
    }
    void saveToFirebase() {
        Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();

        String bloodGroup = mbloodGroupSpinner.getItemAtPosition(mbloodGroupSpinner.getSelectedItemPosition()).toString();
        String rhesus = mrhesusSpinner.getItemAtPosition(mrhesusSpinner.getSelectedItemPosition()).toString();
        String otherCondition = mOtherConditions.getText().toString();

        FirebaseUser users=FirebaseAuth.getInstance().getCurrentUser();
        String uid=users.getUid();
        reference= FirebaseDatabase.getInstance().getReference().child("conditions").child(uid);
        Conditions conditions= new Conditions (uid, bloodGroup, rhesus, otherCondition);
        reference.setValue(conditions);
    }
    void changeFragments(Fragment fragment){

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}


