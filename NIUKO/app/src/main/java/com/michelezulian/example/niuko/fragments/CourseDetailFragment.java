package com.michelezulian.example.niuko.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.michelezulian.example.niuko.R;
import com.michelezulian.example.niuko.data.Corso;
import com.michelezulian.example.niuko.data.Utente;
import com.michelezulian.example.niuko.misc.ConnectionSingleton;
import com.michelezulian.example.niuko.misc.FragmentListener;

import org.json.JSONObject;

import static com.michelezulian.example.niuko.misc.StaticValues.ORE;
import static com.michelezulian.example.niuko.misc.StaticValues.POSTI;
import static com.michelezulian.example.niuko.misc.StaticValues.URL_JOIN_COURSE;
import static com.michelezulian.example.niuko.misc.StaticValues.URL_LOGIN;
import static com.michelezulian.example.niuko.misc.StaticValues.URL_USER_COURSES;

public class CourseDetailFragment extends Fragment {
    Corso mCorso;
    FragmentListener mListener;

    public CourseDetailFragment () { }

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
        Button vIscriviti = vView.findViewById(R.id.corsoDetailsIscriviti);

        Glide.with(getActivity())
                .load(mCorso.getmImgUrl())
                .centerInside()
                .into(vImg);

        vTitolo.setText(mCorso.getmNomeCorso());
        vSede.setText(mCorso.getmSede());
        vDurata.setText(mCorso.getmDurata() + ORE);
        vPosti.setText(POSTI + mCorso.getmPostiLiberi());
        vDescrizione.setText(mCorso.getmDescrizione());

        vIscriviti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject vParameters = new JSONObject();
                try {
                    Utente vUser = mListener.getUtente();
                    vParameters.put("idUtente", vUser.getmId());
                    vParameters.put("idCorso", mCorso.getmId());

                    JsonObjectRequest vRequest = new JsonObjectRequest(
                            Request.Method.POST, URL_JOIN_COURSE, vParameters,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.d("risposta", "onResponse: " + response.toString());
                                    try {
                                        if(response.getBoolean("iscrizione")) {
                                            Toast.makeText(getActivity(), "Iscrizione avvenuta!", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (Exception e) {
                                        Log.d("risposta", "Errore: " + e.toString());
                                        Toast.makeText(getActivity(), "Oops! C'è stato un errore", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.d("risposta", "Errore: " + error.toString());
                                    Toast.makeText(getActivity(), "Oops! C'è stato un errore", Toast.LENGTH_SHORT).show();
                                }
                            }
                    );

                    ConnectionSingleton.getInstance(getActivity()).addToRequestQueue(vRequest);
                } catch (Exception e) {
                    Log.d("risposta", "Errore: " + e.toString());
                    Toast.makeText(getActivity(), "Oops! C'è stato un errore", Toast.LENGTH_SHORT).show();
                }
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
