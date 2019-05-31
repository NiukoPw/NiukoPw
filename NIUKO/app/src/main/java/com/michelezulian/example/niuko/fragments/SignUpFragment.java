package com.michelezulian.example.niuko.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.michelezulian.example.niuko.R;
import com.michelezulian.example.niuko.misc.ConnectionSingleton;

import org.json.JSONObject;

import static android.text.TextUtils.isEmpty;
import static com.michelezulian.example.niuko.misc.StaticValues.URL_LOGIN;
import static com.michelezulian.example.niuko.misc.StaticValues.URL_SIGNUP;

public class SignUpFragment extends Fragment {
    EditText mNome, mCognome, mUsername, mPassword, mPasswordCheck;
    Button mIscriviti;
    TextView mToLogin;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.fragment_signup, container, false);

        mNome = vView.findViewById(R.id.signupNome);
        mCognome = vView.findViewById(R.id.signupCognome);
        mUsername = vView.findViewById(R.id.signupUsername);
        mPassword = vView.findViewById(R.id.signupPassword);
        mPasswordCheck = vView.findViewById(R.id.signupPasswordCheck);
        mIscriviti = vView.findViewById(R.id.signupButton);
        mToLogin = vView.findViewById(R.id.signupToLogin);

        mToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager vManager = getFragmentManager();
                FragmentTransaction vTransaction = vManager.beginTransaction();
                vTransaction.replace(R.id.loginFrameLayout, new LoginFragment());
                vTransaction.commit();
            }
        });

        mIscriviti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject vParameters = new JSONObject();
                try {
                    if (checkValues()) {
                        if (!mPassword.getText().toString().equals(mPasswordCheck.getText().toString())) {
                            vParameters.put("nome", mNome.getText().toString());
                            vParameters.put("cognome", mCognome.getText().toString());
                            vParameters.put("username", mUsername.getText().toString());
                            vParameters.put("password", mPassword.getText().toString());

                            JsonObjectRequest vRequest = new JsonObjectRequest(
                                    Request.Method.POST, URL_SIGNUP, vParameters,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {

                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            error.printStackTrace();
                                        }
                                    }
                            );
                            ConnectionSingleton.getInstance(getActivity()).addToRequestQueue(vRequest);
                        } else {
                            Toast.makeText(getActivity(), "La password non corrisponde", Toast.LENGTH_SHORT);
                        }
                    } else {
                        Toast.makeText(getActivity(), "Compila tutti i campi", Toast.LENGTH_SHORT);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return vView;
    }

    public boolean checkValues() {
        if (isEmpty(mNome.getText().toString()) ||
            isEmpty(mCognome.getText().toString()) ||
            isEmpty(mUsername.getText().toString()) ||
            isEmpty(mPassword.getText().toString()) ||
            isEmpty(mPasswordCheck.getText().toString())
            ){
            return false;
        }
        return true;
    }
}
