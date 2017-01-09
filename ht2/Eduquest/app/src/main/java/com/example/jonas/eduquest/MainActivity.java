package com.example.jonas.eduquest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
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

        if (!hasInternetConnection()) {
            DialogFragment dialog = new NoInternetConnectionDialog();
            dialog.show(getSupportFragmentManager(), "nointernet");
        } else if (hasSavedUpstream()) {
            changeToCategoryView();
        }
    }

    private boolean hasInternetConnection() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
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
        validateAndSaveUpstream(v);
    }

    public void validateAndSaveUpstream(View view) {
            EditText addrTextField = (EditText) findViewById(R.id.upstreamAddressText);
            String url = addrTextField.getText().toString();

            if (!validUrl(url)) {
                DialogFragment dialog = new InvalidUrlDialog();
                dialog.show(getSupportFragmentManager(), "invalidurl");
                return;
            } else {
                sendValidationRequest(url);
            }
    }

    private boolean validUrl(String url) {
        return Patterns.WEB_URL.matcher(url).matches();
    }

    private void sendValidationRequest(final String baseUrl) {
        String requestUrl = baseUrl.concat("/eduquestprovider");
        StringRequest request = new StringRequest(Request.Method.GET, requestUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("isProvider")) {
                            saveUpstream(baseUrl);
                            changeToCategoryView();
                        } else {
                            DialogFragment dialog = new InvalidUpstreamDialog();
                            dialog.show(getSupportFragmentManager(), "noupstream");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        DialogFragment dialog = new InvalidUpstreamDialog();
                        dialog.show(getSupportFragmentManager(), "noupstream");
                    }
                });

        NetworkManager.getInstance(this).addToRequestQueue(request);
    }



    private void saveUpstream(String url) {
        SharedPreferences prefs = getSharedPreferences("settings", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("upstream", url);
        editor.commit();
    }
}