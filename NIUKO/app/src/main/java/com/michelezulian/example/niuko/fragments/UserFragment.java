package com.michelezulian.example.niuko.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.michelezulian.example.niuko.R;
import com.michelezulian.example.niuko.data.Utente;
import com.michelezulian.example.niuko.misc.FragmentListener;

import static com.michelezulian.example.niuko.misc.StaticValues.PROPIC_URL;

public class UserFragment extends Fragment {
    FragmentListener mListener;
    Utente mUtente;
    TextView mNomeUtente;
    ImageView mProPic;
    Button mToMyCourses, mToDetails;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.fragment_user, container, false);
        mUtente = mListener.getUtente();

        mNomeUtente = vView.findViewById(R.id.userNomeUtente);
        mProPic = vView.findViewById(R.id.userProfilePic);
        mToMyCourses = vView.findViewById(R.id.userButtonMieiCorsi);
        mToDetails = vView.findViewById(R.id.userButtonDettagli);

        mNomeUtente.setText(mUtente.getmNomeUtente());

        Glide.with(this)
                .load(PROPIC_URL)
                .centerInside()
                .into(mProPic);

        return vView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof FragmentListener) {
            mListener = (FragmentListener) activity;
        } else {
            mListener = null;
        }
    }
}
