package com.michelezulian.example.niuko.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.michelezulian.example.niuko.R;
import com.michelezulian.example.niuko.adapters.NotiziaAdapter;
import com.michelezulian.example.niuko.data.Notizia;

import java.util.ArrayList;
import java.util.Date;

public class NewsFragment extends Fragment {
    ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.fragment_notizia, container, false);
        mListView = vView.findViewById(R.id.notiziaListView);

        ArrayList<Notizia> vNotizie = new ArrayList<>();

        vNotizie.add(new Notizia(1, "Nuovo corso!!!!!", "ebledb", "https://www.tpi.it/app/uploads/2019/01/heart-3147976_1920-2.jpg", new Date()));
        vNotizie.add(new Notizia(2, "Vecchio corso!!!!!", "ebledb", "http://www.digital-marketing-epc.it/wp-content/uploads/2017/02/dimensioni-immagini-display-563x353.jpg", new Date()));
        vNotizie.add(new Notizia(3, "!!!!!", "ebledb", "https://upload.wikimedia.org/wikipedia/commons/6/6a/Lago_bolsena_tramonto.jpg", new Date()));

        NotiziaAdapter vAdapter = new NotiziaAdapter(vNotizie, getActivity());
        mListView.setAdapter(vAdapter);

        return vView;
    }
}
