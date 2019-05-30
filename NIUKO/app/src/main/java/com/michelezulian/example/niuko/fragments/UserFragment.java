package com.michelezulian.example.niuko.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
    TextView mUsername, mNomeCompleto;
    ImageView mProPic;
    Button mToMyCourses;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.fragment_user, container, false);
        mUtente = mListener.getUtente();

        mUsername = vView.findViewById(R.id.userNomeUtente);
        mNomeCompleto = vView.findViewById(R.id.userNomeCompleto);
        mProPic = vView.findViewById(R.id.userProfilePic);
        mToMyCourses = vView.findViewById(R.id.userButtonMieiCorsi);

        mUsername.setText(mUtente.getmNomeUtente());
        mNomeCompleto.setText(mUtente.getmNome() + " " + mUtente.getmCognome());
        Glide.with(this)
                .load(PROPIC_URL)
                .centerInside()
                .into(mProPic);

        mToMyCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager vManager = getFragmentManager();
                FragmentTransaction vTransaction = vManager.beginTransaction();
                vTransaction.replace(R.id.fragment_container, new UserCoursesFragment());
                vTransaction.commit();
            }
        });
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
