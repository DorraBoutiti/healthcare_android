package com.example.healthcareapp.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.healthcareapp.R;
import com.example.healthcareapp.databinding.FragmentHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    BottomNavigationView bottomNavigationView;
    TextView profileUsername;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        profileUsername = v.findViewById(R.id.profileName);
        sharedpreferences = this.getActivity().getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);
        Log.d("email", sharedpreferences.getString("name",""));
        profileUsername.setText(sharedpreferences.getString("name",""));
        return v;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}