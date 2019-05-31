package com.michelezulian.example.niuko.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.michelezulian.example.niuko.R;
import com.michelezulian.example.niuko.fragments.LoginFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loadFragment(new LoginFragment());
    }

    public void loadFragment(Fragment aFragment) {
        FragmentManager vManager = getFragmentManager();
        FragmentTransaction vTransaction = vManager.beginTransaction();
        vTransaction.replace(R.id.loginFrameLayout, aFragment);
        vTransaction.commit();
    }
}
