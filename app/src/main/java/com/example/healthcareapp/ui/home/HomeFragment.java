package com.example.healthcareapp.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.healthcareapp.CalculIMCIMGActivity;
import com.example.healthcareapp.CalculIMCIMGFragment;
import com.example.healthcareapp.EditProfileActivity;
import com.example.healthcareapp.R;
import com.example.healthcareapp.WorkoutActivity;
import com.example.healthcareapp.databinding.FragmentHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    BottomNavigationView bottomNavigationView;
    TextView profileUsername;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    private TextView stepsTextView;
    private TextView heartRateTextView;
    private CardView card1;
    private CardView card2;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        profileUsername = v.findViewById(R.id.profileName);
        card1= v.findViewById(R.id.docCard);
        card2= v.findViewById(R.id.downloadCard);

        sharedpreferences = this.getActivity().getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);
        Log.d("email", sharedpreferences.getString("name",""));
        profileUsername.setText(sharedpreferences.getString("name",""));
        stepsTextView = v.findViewById(R.id.text_steps);
        heartRateTextView = v.findViewById(R.id.text_heart_rate);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToIMCPage();
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToWorkoutPage();
            }
        });




        return v;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void ToIMCPage(){
        Intent intent = new Intent(this.getActivity(), CalculIMCIMGActivity.class);
        startActivity(intent);
    }
    public void ToWorkoutPage(){
        Intent intent = new Intent(this.getActivity(), WorkoutActivity.class);
        startActivity(intent);
    }
}