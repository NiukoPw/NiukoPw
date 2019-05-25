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
import com.michelezulian.example.niuko.data.Notizia;

public class DetailFragmentNotizia extends Fragment {
    Notizia mNotizia;

    public DetailFragmentNotizia() {

    }

    @SuppressLint("ValidFragment")
    public DetailFragmentNotizia(Notizia aNotizia) {
        mNotizia = aNotizia;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.fragment_details_notizia, container, false);

        ImageView vImg = vView.findViewById(R.id.notiziaDetailsImageView);
        TextView vTitolo = vView.findViewById(R.id.notiziaDetailsTitolo);
        TextView vData = vView.findViewById(R.id.notiziaDetailsData);
        TextView vDescrizione = vView.findViewById(R.id.notiziaDetailsDescrizione);

        Glide.with(getActivity())
                .load(mNotizia.getmImgUrl())
                .centerInside()
                .into(vImg);

        vTitolo.setText(mNotizia.getmTitolo());
        vData.setText(mNotizia.getmData());
        vDescrizione.setText(mNotizia.getmDescrizione());

        return vView;
    }
}
