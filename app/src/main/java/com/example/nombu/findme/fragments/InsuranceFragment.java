package com.example.nombu.findme.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nombu.findme.R;
import com.example.nombu.findme.model.Allergies;
import com.example.nombu.findme.model.Insurance;
import com.example.nombu.findme.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class InsuranceFragment extends Fragment implements View.OnClickListener{
    DatabaseReference reference;
    FirebaseAuth.AuthStateListener auth;
    String userId;

    @Bind(R.id.userMedCover)
    EditText mUserMedCover;
    @Bind(R.id.userNationalId)
    EditText mNatId;
    @Bind(R.id.insurancePolicyNo)
    EditText mInsurancePolicyNo;
    @Bind(R.id.userPrefHospital)
    EditText mUserPrefHospital;
    @Bind(R.id.userInsuranceButton)
    Button mUserInsuranceBtn;

    public InsuranceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_insurance, container, false);
        ButterKnife.bind(this, view);
        mUserInsuranceBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //from text boxes
//        Insurance insurance=new Insurance(userId,"UAP","29353324","A102934","Aga Khan (Main)");
//        pushToFirebase(insurance);

    }

    @Override
    public void onClick (View view) {
        savetoFirebase();

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:=+254711809296"));
        startActivity(intent);
//        Fragment fragment_location = new LocationFragment();
//        changeFragments(fragment_location);
//        mUserMedCover.setVisibility(View.INVISIBLE);
//        mNatId.setVisibility(View.INVISIBLE);
//        mInsurancePolicyNo.setVisibility(View.INVISIBLE);
//        mUserPrefHospital.setVisibility(View.INVISIBLE);

    }

    void savetoFirebase() {
        Toast.makeText(getActivity(), "Medical Cover Saved", Toast.LENGTH_SHORT).show();

        String medCover = mUserMedCover.getText().toString();
        String natId = mNatId.getText().toString();
        String insurancePolicy = mInsurancePolicyNo.getText().toString();
        String prefHospital = mUserPrefHospital.getText().toString();
        FirebaseUser users=FirebaseAuth.getInstance().getCurrentUser();
        String uid=users.getUid();
        reference= FirebaseDatabase.getInstance().getReference().child("insurance").child(uid);
        Insurance insurance = new Insurance (uid, medCover, natId, insurancePolicy, prefHospital);
        reference.setValue(insurance);
    }
//    void changeFragments(Fragment fragment){
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.replace(R.id.mainFrame, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }

}
