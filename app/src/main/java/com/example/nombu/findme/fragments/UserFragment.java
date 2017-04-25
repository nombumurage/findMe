package com.example.nombu.findme.fragments;


import android.os.Bundle;
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
import com.example.nombu.findme.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;


public class UserFragment extends Fragment implements View.OnClickListener{
    DatabaseReference reference;
    FirebaseAuth.AuthStateListener auth;

    @Bind(R.id.userName) EditText mUserEditText;
    @Bind(R.id.gender_spinner) Spinner mGender;
    @Bind(R.id.userAge) EditText mUserAge;
    @Bind(R.id.userBtn) Button mUserButton;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.bind(this, view);
        mUserButton.setOnClickListener(this);

        return view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        User user =new User(userId,"Susan Njoroge", 29 ,"Female");
//        pushToFirebase(user);

    }
    @Override
    public void onClick (View view) {
        savetoFirebase();
        mUserEditText.setVisibility(View.INVISIBLE);
        mUserAge.setVisibility(View.INVISIBLE);
        Fragment fragment_conditions = new ConditionsFragment();
        changeFragments(fragment_conditions);
    }

    void savetoFirebase() {
        Toast.makeText(getActivity(), "User Saved", Toast.LENGTH_SHORT).show();
        String name = mUserEditText.getText().toString();
        String gender = mGender.getItemAtPosition(mGender.getSelectedItemPosition()).toString();
        String age = mUserAge.getText().toString();

        FirebaseUser users=FirebaseAuth.getInstance().getCurrentUser();
        String uid=users.getUid();
        reference= FirebaseDatabase.getInstance().getReference().child("users").child(uid);
        User user = new User (uid, name, age, gender);
        reference.setValue(user);
    }

    void changeFragments(Fragment fragment){

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

