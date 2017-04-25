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
import com.example.nombu.findme.model.Conditions;
import com.example.nombu.findme.model.Medication;
import com.example.nombu.findme.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class MedicationFragment extends Fragment implements View.OnClickListener{
    DatabaseReference reference;
    FirebaseAuth.AuthStateListener auth;

    @Bind(R.id.userMedication)
    EditText mMedication;
    @Bind(R.id.userAddMedBtn)
    Button mAddMedButton;
    @Bind(R.id.userMedicationBtn)
    Button mMedicationBtn;


    public MedicationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_medication, container, false);
        ButterKnife.bind(this, view);

        mAddMedButton.setOnClickListener(this);
        mMedicationBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //from text boxes
//        ArrayList<String> meds = new ArrayList<>();
//        meds.add("Panadol");
//        meds.add("Penicillin");
//        Medication medication =new Medication(userId, meds);
//        pushToFirebase(medication);
    }

    @Override
    public void onClick (View view) {

        if (view== mAddMedButton) {
            saveToFirebase();
            mMedication.setVisibility(View.INVISIBLE);
        }
        else if (view == mMedicationBtn) {
            Toast.makeText(getActivity(), "Medication Saved", Toast.LENGTH_SHORT).show();
            mMedication.setVisibility(View.INVISIBLE);
            Fragment fragment_contacts = new ContactsFragment();
            changeFragments(fragment_contacts);

        }
    }
    void saveToFirebase() {
        Toast.makeText(getActivity(), "Medication Added", Toast.LENGTH_SHORT).show();

        String medicine = mMedication.getText().toString();

        FirebaseUser users=FirebaseAuth.getInstance().getCurrentUser();
        String uid=users.getUid();

        reference= FirebaseDatabase.getInstance().getReference().child("medication").child(uid);
        Medication medication= new Medication (uid, medicine);
        reference.setValue(medication);
    }
    void changeFragments(Fragment fragment){

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}




