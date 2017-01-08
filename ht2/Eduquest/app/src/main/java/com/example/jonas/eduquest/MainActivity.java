package com.example.jonas.eduquest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (hasSavedUpstream()) {
            changeToCategoryView();
        }
    }

    private boolean hasSavedUpstream() {
        SharedPreferences prefs = getSharedPreferences("settings", 0);
        String upstream = prefs.getString("upstream", "");

        return upstream.length() != 0;
    }

    private void changeToCategoryView() {
        Intent intent = new Intent(this, CategorySelectionActivity.class);
        startActivity(intent);
    }

    public void onBtnClicked(View v) {
        verifyAndSaveValidUpstream(v);
    }

    public void verifyAndSaveValidUpstream(View view) {
        if (hasInternetConnection()) {
            EditText addrTextField = (EditText) findViewById(R.id.upstreamAddressText);
            final String baseUrl = addrTextField.getText().toString();
            String requestUrl = baseUrl.concat("/eduquestprovider");

            StringRequest request = new StringRequest(Request.Method.GET, requestUrl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("isProvider")) {
                                saveUpstream(baseUrl);
                                changeToCategoryView();
                                System.out.println("ALL IS WELL");
                            } else {
                                DialogFragment dialog = new InvalidUpstreamDialog();
                                dialog.show(getSupportFragmentManager(), "noupstream");
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

            NetworkManager.getInstance(this).addToRequestQueue(request);
        } else {
            DialogFragment dialog = new FirstStartupDialogs();
            dialog.show(getSupportFragmentManager(), "nointernet");
        }
    }

    private boolean hasInternetConnection() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

    private void saveUpstream(String url) {
        SharedPreferences prefs = getSharedPreferences("settings", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("upstream", url);
        editor.commit();
    }
}