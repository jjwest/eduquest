package com.example.jonas.eduquest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.net.URL;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    final String UPSTREAM_FILENAME = "upstream.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (hasSavedUpstream()) {
            System.out.println("BYTER VY");
        }
    }

    private boolean hasSavedUpstream() {
        try {
            FileInputStream fis = openFileInput(UPSTREAM_FILENAME);
            byte [] input = new byte[150];
            fis.read(input);
            String upstream = input.toString();

            return !upstream.isEmpty();

        } catch (IOException e) {
            return false;
        }

    }

    public void verifyValidUpstream(View view) {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            PingUpstreamTask ping = new PingUpstreamTask();
            EditText addrTextField = (EditText) findViewById(R.id.upstreamAddressText);
            String url = addrTextField.getText().toString();
            ping.execute(url);

        } else {
            DialogFragment dialog = new FirstStartupDialogs();
            dialog.show(getSupportFragmentManager(), "nointernet");
        }
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
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // Starts the query
                conn.connect();
                Integer response = conn.getResponseCode();
                return response.toString();

                // Makes sure that the InputStream is closed after the app is
                // finished using it.
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }

        protected void onPostExecute(UrlResponse result) {
            if (result.getStatusCode().equals("200") || result.getStatusCode().startsWith("3")) {
                saveUpstream(result.getUrl());
            } else {
                DialogFragment dialog = new InvalidUpstreamDialog();
                dialog.show(getSupportFragmentManager(), "noupstream");
            }
        }

        private void saveUpstream(String url) {
            try {
                FileOutputStream fos = openFileOutput(UPSTREAM_FILENAME, Context.MODE_PRIVATE);
                fos.write(url.getBytes());
                fos.close();
                System.out.println("SPARAT");
            }
            catch (IOException e) {
                // FIXA FELMEDDELANDE
            }
        }
    }
}
