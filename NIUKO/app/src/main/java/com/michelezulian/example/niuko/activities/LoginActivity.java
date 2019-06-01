package com.michelezulian.example.niuko.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SharedElementCallback;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.michelezulian.example.niuko.R;
import com.michelezulian.example.niuko.fragments.LoginFragment;

import static com.michelezulian.example.niuko.misc.StaticValues.ID_KEY;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences vSharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        if(vSharedPref.getInt(ID_KEY, -1) >= 0) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        } else {
            setContentView(R.layout.activity_login);
            loadFragment(new LoginFragment());
        }
    }

    public void loadFragment(Fragment aFragment) {
        FragmentManager vManager = getFragmentManager();
        FragmentTransaction vTransaction = vManager.beginTransaction();
        vTransaction.replace(R.id.loginFrameLayout, aFragment);
        vTransaction.commit();
    }
}
