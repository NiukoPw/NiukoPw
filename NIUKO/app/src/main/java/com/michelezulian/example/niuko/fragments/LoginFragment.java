package com.michelezulian.example.niuko.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.michelezulian.example.niuko.R;
import com.michelezulian.example.niuko.misc.ConnectionSingleton;

import org.json.JSONObject;

import static com.michelezulian.example.niuko.misc.StaticValues.URL_LOGIN;

public class LoginFragment extends Fragment {
    EditText mUsername, mPassword;
    Button mGoLogin;
    TextView mToSignUp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.fragment_login, container, false);
        mUsername = vView.findViewById(R.id.loginUsername);
        mPassword = vView.findViewById(R.id.loginPassword);
        mGoLogin = vView.findViewById(R.id.loginButton);
        mToSignUp = vView.findViewById(R.id.loginSignUpLink);

        mToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager vManager = getFragmentManager();
                FragmentTransaction vTransaction = vManager.beginTransaction();
                vTransaction.replace(R.id.loginFrameLayout, new SignUpFragment());
                vTransaction.commit();
            }
        });

        mGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject vParameters = new JSONObject();
                try {
                    vParameters.put("username", mUsername.getText().toString());
                    vParameters.put("password", mPassword.getText().toString());

                    JsonObjectRequest vRequest = new JsonObjectRequest(
                            Request.Method.POST, URL_LOGIN, vParameters,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.d("risposta", "onResponse: " + response.toString());
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.d("risposta", "Errore: " + error.getMessage());
                                }
                            }
                    );

                    ConnectionSingleton.getInstance(getActivity()).addToRequestQueue(vRequest);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return vView;
    }

}
