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

import java.net.HttpURLConnection;
import java.io.InputStream;
import java.net.URL;

import java.io.IOException;

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
            PingUpstreamTask ping = new PingUpstreamTask();
            EditText addrTextField = (EditText) findViewById(R.id.upstreamAddressText);
            String url = addrTextField.getText().toString();
            ping.execute(url);

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

    private class PingUpstreamTask extends AsyncTask<String, Void, UrlResponse> {
        protected UrlResponse doInBackground(String... urls) {
            try {
                return new UrlResponse(urls[0], getResponseCode(urls[0]));
            } catch (IOException e) {
                return new UrlResponse(urls[0], "404");
            }
        }

        private String getResponseCode(String myurl) throws IOException {
            InputStream is = null;

            try {
                URL url = new URL(myurl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                Integer statusCode = conn.getResponseCode();

                return statusCode.toString();

            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
                if (is != null) {
                    is.close();
                }

                return "500";
            }
        }

        protected void onPostExecute(UrlResponse result) {
            if (validUpstream(result)) {
                saveUpstream(result.getUrl());
                changeToCategoryView();
            } else {
                DialogFragment dialog = new InvalidUpstreamDialog();
                dialog.show(getSupportFragmentManager(), "noupstream");
            }
        }

        private boolean validUpstream(UrlResponse result) {
            return result.getStatusCode().equals("200") || result.getStatusCode().startsWith("3");
        }
    }
}
