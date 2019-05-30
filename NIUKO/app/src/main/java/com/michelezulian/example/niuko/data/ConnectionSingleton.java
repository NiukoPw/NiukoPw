package com.michelezulian.example.niuko.data;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class ConnectionSingleton {
    private static ConnectionSingleton mInstance;
    private RequestQueue mQueue;
    private static Context mContext;

    private ConnectionSingleton(Context aContext) {
        mContext = aContext;
        mQueue = getRequestQueue();
    }

    public static synchronized ConnectionSingleton  getInstance(Context aContext) {
        if(mInstance == null) {
            mInstance = new ConnectionSingleton(aContext);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if(mQueue == null) {
            mQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mQueue;
    }

    public <T> void addToRequestQueue(Request<T> aRequest) {
        getRequestQueue().add(aRequest);
    }
}
