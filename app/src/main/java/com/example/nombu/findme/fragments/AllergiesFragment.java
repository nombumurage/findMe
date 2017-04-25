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
import android.widget.Toast;

import com.example.nombu.findme.R;
import com.example.nombu.findme.model.Allergies;
import com.example.nombu.findme.model.Conditions;
import com.example.nombu.findme.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AllergiesFragment extends Fragment implements View.OnClickListener{
    DatabaseReference reference;
    FirebaseAuth.AuthStateListener auth;

    @Bind(R.id.userAllergy)
    EditText mUserAllergy;
    @Bind(R.id.allergyAddBtn)
    Button mAllergyAddButton;
    @Bind(R.id.allergyBtn)
    Button mAllergyButton;

    public AllergiesFragment() {
            // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_allergies, container, false);
        ButterKnife.bind(this, view);

        mAllergyAddButton.setOnClickListener(this);
        mAllergyButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
    }

    @Override
    public void onClick (View view) {
        if(view == mAllergyAddButton) {
            savetoFirebase();

        } else if (view == mAllergyButton) {
            Toast.makeText(getActivity(), "Allergy Saved", Toast.LENGTH_SHORT).show();
            Fragment fragment_medication = new MedicationFragment();
            changeFragments(fragment_medication);
        }
    }

    void savetoFirebase() {
        Toast.makeText(getActivity(), "Allergies Added", Toast.LENGTH_SHORT).show();
        String allergy = mUserAllergy.getText().toString();

        FirebaseUser users=FirebaseAuth.getInstance().getCurrentUser();
        String uid=users.getUid();
        reference= FirebaseDatabase.getInstance().getReference().child("allergies").child(uid);
        Allergies allergies = new Allergies (uid, allergy);
        reference.setValue(allergies);
    }
    void changeFragments(Fragment fragment){

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

