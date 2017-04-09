package com.example.jonas.eduquest;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by jonas on 2017-01-03.
 */

public class NetworkManager {
        private static NetworkManager mInstance;
        private RequestQueue mRequestQueue;
        private static Context mCtx;

        private NetworkManager(Context context) {
            mCtx = context;
            mRequestQueue = getRequestQueue();
        }

        public static synchronized NetworkManager getInstance(Context context) {
            if (mInstance == null) {
                mInstance = new NetworkManager(context);
            }
            return mInstance;
        }

        public RequestQueue getRequestQueue() {
            if (mRequestQueue == null) {
                // getApplicationContext() is key, it keeps you from leaking the
                // Activity or BroadcastReceiver if someone passes one in.
                mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
            }
            return mRequestQueue;
        }

        public <T> void addToRequestQueue(Request<T> req) {
            getRequestQueue().add(req);
        }
}
