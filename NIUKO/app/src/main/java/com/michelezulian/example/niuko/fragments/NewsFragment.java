package com.michelezulian.example.niuko.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.michelezulian.example.niuko.R;
import com.michelezulian.example.niuko.adapters.NotiziaAdapter;
import com.michelezulian.example.niuko.data.Notizia;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.michelezulian.example.niuko.data.StaticValues.LOREM_IPSUM;

public class NewsFragment extends Fragment {
    ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.fragment_notizia, container, false);
        mListView = vView.findViewById(R.id.notiziaListView);

        final ArrayList<Notizia> vNotizie = new ArrayList<>();
        DateFormat vDf = DateFormat.getDateInstance(DateFormat.LONG);

        vNotizie.add(new Notizia(1, "Nuovo corso!!!!!", LOREM_IPSUM, "https://www.tpi.it/app/uploads/2019/01/heart-3147976_1920-2.jpg", vDf.format(new Date())));
        vNotizie.add(new Notizia(2, "Vecchio corso!!!!!", LOREM_IPSUM, "http://www.digital-marketing-epc.it/wp-content/uploads/2017/02/dimensioni-immagini-display-563x353.jpg", vDf.format(new Date())));
        vNotizie.add(new Notizia(3, "!!!!!", LOREM_IPSUM, "https://upload.wikimedia.org/wikipedia/commons/6/6a/Lago_bolsena_tramonto.jpg", vDf.format(new Date())));

        NotiziaAdapter vAdapter = new NotiziaAdapter(vNotizie, getActivity());
        mListView.setAdapter(vAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager vManager = getFragmentManager();
                FragmentTransaction vTransaction = vManager.beginTransaction();
                vTransaction.replace(R.id.fragment_container, new DetailFragmentNotizia(vNotizie.get(position)));
                vTransaction.commit();
            }
        });

        return vView;
    }
}
