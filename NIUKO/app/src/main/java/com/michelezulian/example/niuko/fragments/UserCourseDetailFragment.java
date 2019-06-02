package com.michelezulian.example.niuko.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.michelezulian.example.niuko.R;
import com.michelezulian.example.niuko.data.Corso;

import static com.michelezulian.example.niuko.misc.StaticValues.ORE;

public class UserCourseDetailFragment extends Fragment {
    Corso mCorso;

    public UserCourseDetailFragment() {

    }

    @SuppressLint("ValidFragment")
    public UserCourseDetailFragment(Corso aCorso) {
        mCorso = aCorso;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.fragment_user_course_detail, container, false);

        ImageView vImg = vView.findViewById(R.id.userCourseDetailsImageView);
        TextView vTitolo = vView.findViewById(R.id.userCourseDetailsTitolo);
        TextView vSede = vView.findViewById(R.id.userCourseDetailsSede);
        TextView vDurata = vView.findViewById(R.id.userCourseDetailsDurata);
        TextView vDescrizione = vView.findViewById(R.id.userCourseDetailsDescrizione);

        Glide.with(getActivity())
                .load(mCorso.getmImgUrl())
                .into(vImg);

        vTitolo.setText(mCorso.getmNomeCorso());
        vSede.setText(mCorso.getmSede());
        vDurata.setText(mCorso.getmDurata() + ORE);
        vDescrizione.setText(mCorso.getmDescrizione());

        return vView;
    }
}
