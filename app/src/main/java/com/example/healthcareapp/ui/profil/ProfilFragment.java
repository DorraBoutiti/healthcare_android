package com.example.healthcareapp.ui.profil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.healthcareapp.EditProfileActivity;
import com.example.healthcareapp.ProfileActivity;
import com.example.healthcareapp.R;
import com.example.healthcareapp.databinding.FragmentProfilBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class ProfilFragment extends Fragment {
    TextView profileName, profileEmail, profileUsername, profilePassword;
    TextView titleName, titleUsername;
    Button editProfile;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    private FragmentProfilBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profil, container, false);
        profileName = v.findViewById(R.id.profileName);
        profileEmail = v.findViewById(R.id.profileEmail);
        profileUsername = v.findViewById(R.id.profileUsername);
        profilePassword = v.findViewById(R.id.profilePassword);
        titleName = v.findViewById(R.id.titleName);
        titleUsername = v.findViewById(R.id.titleUsername);
        editProfile = v.findViewById(R.id.editButton);
        sharedpreferences = this.getActivity().getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);
        titleName.setText(sharedpreferences.getString("name",""));
        titleUsername.setText(sharedpreferences.getString("username",""));
        profileName.setText(sharedpreferences.getString("name",""));
        profileEmail.setText(sharedpreferences.getString("email",""));
        profileUsername.setText(sharedpreferences.getString("username",""));
        profilePassword.setText(sharedpreferences.getString("password",""));
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToEditPage();
            }
        });
        return v;
    }
    public void ToEditPage(){
        Intent intent = new Intent(this.getActivity(), EditProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}