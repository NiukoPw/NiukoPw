package com.michelezulian.example.niuko;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ExploreFragment extends Fragment {
    ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.fragment_explore, container, false);
        mListView = vView.findViewById(R.id.exploreListView);

        ArrayList<Corso> vCorsi = new ArrayList<>();

        vCorsi.add(new Corso(1,"Inglese Avanzato", "Padova","http://www.metup.it/wp-content/uploads/2016/06/Google-Immagini-Homepage-1.jpg" , 32));
        vCorsi.add(new Corso(2,"Francese Arretrato", "Padova", "https://www.neweuropelingue.it/wp-content/uploads/2018/08/corsi-francese-vomero.jpg" , 32));
        vCorsi.add(new Corso(3,"Informatica Avanzata Nel Campo Delle Nanotecnologie E Pasta Alla Carbonara", "Padova", "https://www.informacibo.it/wp-content/uploads/2018/04/carbonara.jpg", 32));

        CorsoAdapter vAdapter = new CorsoAdapter(vCorsi, getActivity());
        mListView.setAdapter(vAdapter);
        /*
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager vManager = getFragmentManager();
                FragmentTransaction vTransaction = vManager.beginTransaction();
                vTransaction.replace(R.id.fragment_container, new DetailFragment());
                vTransaction.commit();
            }
        });
        */

        return vView;
    }
}
