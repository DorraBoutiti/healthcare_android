package com.example.healthcareapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.content.BroadcastReceiver;

import android.content.Intent;
import android.content.IntentFilter;

import android.net.Network;
import android.net.NetworkInfo;


import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private BroadcastReceiver networkChangeReceiver;
    private ConnectivityManager connectivityManager;
    private ConnectivityManager.NetworkCallback networkCallback;
    public static final String MyPREFERENCES = "MyPrefs" ;
    TextView internetStatusTextView;
    String status="Déconnecté";

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        Switch switchDarkMode = view.findViewById(R.id.switch_dark_mode);

        SharedPreferences preferences = this.getActivity().getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);
        boolean isDarkModeEnabled = preferences.getBoolean("dark_mode", false);
        switchDarkMode.setChecked(isDarkModeEnabled);
        switchDarkMode.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            // Save the user's preference for dark mode to SharedPreferences
            preferences.edit().putBoolean("dark_mode", isChecked).apply();

            // Apply the selected theme immediately
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
        internetStatusTextView = view.findViewById(R.id.text_internet_status);

        return  view;
    }
    @Override
    public void onResume() {
        super.onResume();
        registerNetworkChangeReceiver();
        updateInternetStatus();
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterNetworkChangeReceiver();
    }

    private void registerNetworkChangeReceiver() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        networkChangeReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                updateInternetStatus();
            }
        };
        requireActivity().registerReceiver(networkChangeReceiver, filter);
    }

    private void unregisterNetworkChangeReceiver() {
        requireActivity().unregisterReceiver(networkChangeReceiver);
    }

    private void updateInternetStatus() {
        ConnectivityManager connectivityManager = (ConnectivityManager) requireContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = networkInfo != null && networkInfo.isConnected();
        boolean isWiFi = networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;

        if (isConnected && isWiFi) {
            // Wi-Fi connected
            internetStatusTextView.setText("État de la connexion Internet: Connecté en Wi-Fi");
        } else if (isConnected) {
            // Connected to another network
            internetStatusTextView.setText("État de la connexion Internet: Connecté");
        } else {
            // No network connection
            internetStatusTextView.setText("État de la connexion Internet: Déconnecté");
        }
    }


}