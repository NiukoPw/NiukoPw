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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.michelezulian.example.niuko.R;
import com.michelezulian.example.niuko.adapters.CorsoAdapter;
import com.michelezulian.example.niuko.misc.ConnectionSingleton;
import com.michelezulian.example.niuko.data.Corso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.michelezulian.example.niuko.misc.StaticValues.IMG_URL;
import static com.michelezulian.example.niuko.misc.StaticValues.STATO_PUBBLICATO;
import static com.michelezulian.example.niuko.misc.StaticValues.URL_ALL_COURSES;

public class ExploreFragment extends Fragment {
    ListView mListView;
    ArrayList<Corso> mCorsi;


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

                            /***** I = 0 DOPO AVER SISTEMATO DB *****/
                            for (int i = 1; i < vRecords.length(); i++) {
                                JSONObject vCurrent = vRecords.getJSONObject(i);

                                //if (vCurrent.getString("stato").equals(STATO_PUBBLICATO)) {
                                    mCorsi.add(new Corso(
                                            vCurrent.getInt("id"),
                                            vCurrent.getString("titolo"),
                                            vCurrent.getString("sede"),
                                            vCurrent.getInt("durata"),
                                            vCurrent.getString("descrizione"),
                                            vCurrent.getString("stato"),
                                            vCurrent.getInt("postiLiberi"),
                                            IMG_URL
                                    ));
                                //}
                            }

                            // aggiungo adapter alla listview
                            CorsoAdapter vAdapter = new CorsoAdapter(mCorsi, getActivity());
                            mListView.setAdapter(vAdapter);
                        } catch (Exception e) {
                            Log.d("risposta", "Errore: " + e.toString());
                            Toast.makeText(getActivity(), "Oops! C'è stato un errore", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("risposta", "Errore: " + error.toString());
                        Toast.makeText(getActivity(), "Oops! C'è stato un errore", Toast.LENGTH_SHORT).show();
                    }
                });


        // eseguo richiesta
        ConnectionSingleton.getInstance(getActivity()).addToRequestQueue(vJsonRequest);

        // quando viene cliccato un elemento della lista
        // passa al fragment coi dettagli dell'elemento
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager vManager = getFragmentManager();
                FragmentTransaction vTransaction = vManager.beginTransaction();
                vTransaction.replace(R.id.fragment_container, new CourseDetailFragment(mCorsi.get(position)));
                vTransaction.commit();
            }
        });

        return vView;
    }
}

