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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.michelezulian.example.niuko.R;
import com.michelezulian.example.niuko.adapters.NotiziaAdapter;
import com.michelezulian.example.niuko.misc.ConnectionSingleton;
import com.michelezulian.example.niuko.data.Notizia;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.michelezulian.example.niuko.misc.StaticValues.IMG_URL;
import static com.michelezulian.example.niuko.misc.StaticValues.URL_ALL_NEWS;

public class NewsFragment extends Fragment {
    ListView mListView;
    ArrayList<Notizia> mNotizie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.fragment_notizia, container, false);
        mListView = vView.findViewById(R.id.notiziaListView);
        mNotizie = new ArrayList<>();

        // creo stringa di richiesta
        JsonObjectRequest vJsonRequest = new JsonObjectRequest
                (Request.Method.GET, URL_ALL_NEWS, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("risposta", "Risposta: " + response.toString());
                        try {
                            JSONArray vRecords = response.getJSONArray("records");

                            for (int i = 0; i < vRecords.length(); i++) {
                                JSONObject vCurrent = vRecords.getJSONObject(i);

                                mNotizie.add(new Notizia(
                                        vCurrent.getInt("id"),
                                        vCurrent.getString("titolo"),
                                        vCurrent.getString("descrizione"),
                                        IMG_URL,
                                        vCurrent.getString("data")
                                ));

                            }

                            // aggiungo adapter alla listview
                            NotiziaAdapter vAdapter = new NotiziaAdapter(mNotizie, getActivity());
                            mListView.setAdapter(vAdapter);
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
        ConnectionSingleton.getInstance(getActivity()).addToRequestQueue(vJsonRequest);



        NotiziaAdapter vAdapter = new NotiziaAdapter(mNotizie, getActivity());
        mListView.setAdapter(vAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager vManager = getFragmentManager();
                FragmentTransaction vTransaction = vManager.beginTransaction();
                vTransaction.replace(R.id.fragment_container, new NewsDetailFragment(mNotizie.get(position)));
                vTransaction.commit();
            }
        });

        return vView;
    }
}
