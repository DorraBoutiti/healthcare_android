package com.example.healthcareapp;


import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;

public class Utility {

    static void showToast(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    static CollectionReference getCollectionReferenceForNotes(){
        return FirebaseFirestore.getInstance().collection("notes");
    }

    static String timestampToString(Timestamp timestamp){
        return new SimpleDateFormat("MM/dd/yyyy").format(timestamp.toDate());
    }

}