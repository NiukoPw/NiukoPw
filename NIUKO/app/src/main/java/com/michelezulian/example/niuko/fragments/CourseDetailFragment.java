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
import static com.michelezulian.example.niuko.misc.StaticValues.POSTI;

public class CourseDetailFragment extends Fragment {
    Corso mCorso;

    public CourseDetailFragment() {

    }

    @SuppressLint("ValidFragment")
    public CourseDetailFragment(Corso aCorso) {
        mCorso = aCorso;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.fragment_details_corso, container, false);

        ImageView vImg = vView.findViewById(R.id.corsoDetailsImageView);
        TextView vTitolo = vView.findViewById(R.id.corsoDetailsTitolo);
        TextView vSede = vView.findViewById(R.id.corsoDetailsSede);
        TextView vDurata = vView.findViewById(R.id.corsoDetailsDurata);
        TextView vPosti = vView.findViewById(R.id.corsoDetailsPosti);
        TextView vDescrizione = vView.findViewById(R.id.corsoDetailsDescrizione);

        Glide.with(getActivity())
                .load(mCorso.getmImgUrl())
                .centerInside()
                .into(vImg);

        vTitolo.setText(mCorso.getmNomeCorso());
        vSede.setText(mCorso.getmSede());
        vDurata.setText(mCorso.getmDurata() + ORE);
        vPosti.setText(POSTI + mCorso.getmPostiLiberi());
        vDescrizione.setText(mCorso.getmDescrizione());

        return vView;
    }
}
