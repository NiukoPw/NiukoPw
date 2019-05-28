package com.michelezulian.example.niuko.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.michelezulian.example.niuko.data.ConnectionSingleton;
import com.michelezulian.example.niuko.data.Corso;
import com.michelezulian.example.niuko.adapters.CorsoAdapter;
import com.michelezulian.example.niuko.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import static com.michelezulian.example.niuko.data.StaticValues.URL_ALL_COURSES;

public class ExploreFragment extends Fragment {
    ListView mListView;
    ArrayList<Corso> mCorsi;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.fragment_explore, container, false);
        mListView = vView.findViewById(R.id.exploreListView);
        mCorsi = new ArrayList<>();

        // creo stringa di richiesta
        JsonObjectRequest vJsonRequest = new JsonObjectRequest
                (Request.Method.GET, URL_ALL_COURSES, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("risposta", "Risposta: " + response.toString());
                        try {
                            JSONArray vRecords = response.getJSONArray("records");

                            for (int i = 0; i < vRecords.length(); i++) {
                                JSONObject vCurrent = vRecords.getJSONObject(i);

                                mCorsi.add(new Corso(0, vCurrent.getString("titolo"), vCurrent.getString("descrizione"), ))
                                Log.d("risposta", "Titolo: " + vCurrent.getString("titolo"));
                                Log.d("risposta", "descrizione: " + vCurrent.getString("descrizione"));
                                Log.d("risposta", "stato: " + vCurrent.getString("stato"));
                                Log.d("risposta", "postiLiberi: " + vCurrent.getInt("postiLiberi"));
                                Log.d("risposta", "durata: " + vCurrent.getInt("durata"));
                                Log.d("risposta", "sede: " + vCurrent.getString("sede"));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });


        // eseguo richiesta
        ConnectionSingleton.getInstance(getContext()).addToRequestQueue(vJsonRequest);


        // aggiungo adapter alla listview
        CorsoAdapter vAdapter = new CorsoAdapter(mCorsi, getActivity());
        mListView.setAdapter(vAdapter);

        // quando viene cliccato un elemento della lista
        // passa al fragment coi dettagli dell'elemento
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager vManager = getFragmentManager();
                FragmentTransaction vTransaction = vManager.beginTransaction();
                vTransaction.replace(R.id.fragment_container, new DetailFragmentCorso(mCorsi.get(position)));
                vTransaction.commit();
            }
        });

        return vView;
    }
}

        /*
        vCorsi.add(new Corso(1,"Inglese Avanzato", LOREM_IPSUM, "Padova","http://www.metup.it/wp-content/uploads/2016/06/Google-Immagini-Homepage-1.jpg" , 32));
        vCorsi.add(new Corso(2,"Francese Arretrato", LOREM_IPSUM, "Padova", "https://www.neweuropelingue.it/wp-content/uploads/2018/08/corsi-francese-vomero.jpg" , 32));
        vCorsi.add(new Corso(3,"Informatica Avanzata Nel Campo Delle Nanotecnologie E Pasta Alla Carbonara", LOREM_IPSUM, "Padova", "https://www.informacibo.it/wp-content/uploads/2018/04/carbonara.jpg", 32));
        */
