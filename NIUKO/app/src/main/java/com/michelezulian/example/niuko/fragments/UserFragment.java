package com.michelezulian.example.niuko.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.michelezulian.example.niuko.R;
import com.michelezulian.example.niuko.activities.LoginActivity;
import com.michelezulian.example.niuko.activities.MainActivity;
import com.michelezulian.example.niuko.data.Utente;
import com.michelezulian.example.niuko.misc.ConnectionSingleton;
import com.michelezulian.example.niuko.misc.FragmentListener;

import org.json.JSONObject;

import java.lang.reflect.Method;

import static com.michelezulian.example.niuko.misc.StaticValues.ID_KEY;
import static com.michelezulian.example.niuko.misc.StaticValues.PROPIC_URL;
import static com.michelezulian.example.niuko.misc.StaticValues.URL_ORE_SVOLTE;
import static com.michelezulian.example.niuko.misc.StaticValues.TAG;

public class UserFragment extends Fragment {
    FragmentListener mListener;
    Utente mUtente;
    TextView mNomeUtente, mOreSvolte, mCertificati, mCorisAttivi;
    ImageView mBgPic, mProPic;
    Button mToMyCourses, mLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.fragment_user, container, false);
        mUtente = mListener.getUtente();

        mNomeUtente = vView.findViewById(R.id.userNomeUtente);
        mBgPic = vView.findViewById(R.id.userProfilePic);
        mProPic = vView.findViewById(R.id.userSmallProfilPic);
        mToMyCourses = vView.findViewById(R.id.userButtonMieiCorsi);
        mOreSvolte = vView.findViewById(R.id.userOreSvolte);
        mCorisAttivi= vView.findViewById(R.id.userCorsiAttivi);
        mCertificati = vView.findViewById(R.id.userCertificati);
        mLogout = vView.findViewById(R.id.userButtonLogout);


        mNomeUtente.setText(mUtente.getmNomeUtente());

        // immagine di sfondo
        Glide.with(this)
                .load(PROPIC_URL)
                .centerInside()
                .into(mBgPic);

        // immagine profilo
        Glide.with(this)
                .load(PROPIC_URL)
                .centerInside()
                .into(mProPic);

        // vedi i miei corsi
        mToMyCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager vManager = getFragmentManager();
                FragmentTransaction vTransaction = vManager.beginTransaction();
                vTransaction.replace(R.id.fragment_container, new UserCoursesFragment());
                vTransaction.commit();
            }
        });

        // logout
        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences vSharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor vEditor = vSharedPref.edit();
                vEditor.remove(ID_KEY);
                vEditor.commit();

                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });

        // ore di lezione
        JSONObject vParameters = new JSONObject();
        try {
            vParameters.put("id", mUtente.getmId());
            JsonObjectRequest vRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    URL_ORE_SVOLTE,
                    vParameters,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.d(TAG, "onResponse: " + response.toString());
                                mOreSvolte.setText("" + response.getInt("ore"));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("risposta", "onErrorResponse: " + error.getMessage());
                        }
                    }
            );

            ConnectionSingleton.getInstance(getActivity()).addToRequestQueue(vRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }


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
