package com.proficiency.innocirc;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class VolleySingletonQuee {

    private static VolleySingletonQuee mInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    public VolleySingletonQuee(Context context) {

        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized VolleySingletonQuee getInstance(Context context){
        // If Instance is null then initialize new Instance
        if(mInstance == null){
            mInstance = new VolleySingletonQuee(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        // If RequestQueue is null the initialize new RequestQueue
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }

        // Return RequestQueue
        return mRequestQueue;
    }

    public<T> void addToRequestQueue(Request<T> request){
        // Add the specified request to the request queue
        getRequestQueue().add(request);
    }
}
