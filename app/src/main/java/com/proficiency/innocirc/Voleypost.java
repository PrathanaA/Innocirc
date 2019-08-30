package com.proficiency.innocirc;


import android.app.Activity;
import android.app.ProgressDialog;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class Voleypost {

    public static void getting_brand_(Activity activity, String url, JSONObject postObject, final VoleyJsonObjectCallback callback) {
        final ProgressDialog progressDialog = ProgressDialog.show(activity, "",
                "Loading....Please wait.");



        JsonObjectRequest jobReq = new JsonObjectRequest(Request.Method.POST, url, postObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        progressDialog.cancel();

                        try {
                            callback.onSuccessResponse(jsonObject);


                        } catch (Exception e) {
                            e.printStackTrace();

                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.cancel();

                    }
                });


        VolleySingletonQuee.getInstance(activity).addToRequestQueue(jobReq);

    }
}
