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
import com.example.nombu.findme.model.Contacts;
import com.example.nombu.findme.model.Insurance;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment implements View.OnClickListener{
    DatabaseReference reference;
    FirebaseAuth.AuthStateListener auth;

    @Bind(R.id.emergencyContact)
    EditText mEmergencyContact;
    @Bind(R.id.emergencyNumber)
    EditText mEmergencyNumber;
    @Bind(R.id.emergencyContactAddBtn)
    Button mEmergencyAdd;
    @Bind(R.id.emergencyContactBtn)
    Button mEmergencyContactBtn;

    public ContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_contacts, container, false);
        ButterKnife.bind(this, view);

        mEmergencyAdd.setOnClickListener(this);
        mEmergencyContactBtn.setOnClickListener(this);

        return view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        reference= FirebaseDatabase.getInstance().getReference().child("contacts");
//        connectToFirebase();
//        //from text boxes
//        Contacts contacts=new Contacts(userId,"Dad", "0711809296");
//        pushToFirebase(contacts);

    }

    @Override
    public void onClick (View view) {

        if (view== mEmergencyAdd) {
            saveToFirebase();
            mEmergencyContact.setVisibility(View.INVISIBLE);
            mEmergencyNumber.setVisibility(View.INVISIBLE);
        }
        else if (view == mEmergencyContactBtn) {
            Toast.makeText(getActivity(), "Contacts Saved", Toast.LENGTH_SHORT).show();
            Fragment fragment_insurance = new InsuranceFragment();
            changeFragments(fragment_insurance);

        }
    }
    void saveToFirebase() {
        Toast.makeText(getActivity(), "Contact Added", Toast.LENGTH_SHORT).show();

        String contactName = mEmergencyContact.getText().toString();
        String contactNumber = mEmergencyNumber.getText().toString();

        FirebaseUser users=FirebaseAuth.getInstance().getCurrentUser();
        String uid=users.getUid();

        reference= FirebaseDatabase.getInstance().getReference().child("contacts").child(uid);
        Contacts contacts= new Contacts (uid, contactName, contactNumber);
        reference.setValue(contacts);
    }
    void changeFragments(Fragment fragment){

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}

