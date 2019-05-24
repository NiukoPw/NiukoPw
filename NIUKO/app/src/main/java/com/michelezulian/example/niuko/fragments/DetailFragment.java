package com.michelezulian.example.niuko.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;

public class DetailFragment extends Fragment {
    int mId;

    public DetailFragment() {

    }

    @SuppressLint("ValidFragment")
    public DetailFragment(int aId) {
        mId = aId;

    }
}
