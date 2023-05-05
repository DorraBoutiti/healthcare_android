package com.example.healthcareapp;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;

public class ConnectivityCallback extends ConnectivityManager.NetworkCallback {

    private ConnectivityChangeListener listener;

    public interface ConnectivityChangeListener {
        void onConnectivityChanged(boolean isConnected);
    }

    public ConnectivityCallback(ConnectivityChangeListener listener) {
        this.listener = listener;
    }

    @Override
    public void onAvailable(Network network) {
        listener.onConnectivityChanged(true);
    }

    @Override
    public void onLost(Network network) {
        listener.onConnectivityChanged(false);
    }
}
