package com.example.jonas.eduquest;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;


import java.util.ArrayList;

public class CategorySelectionActivity extends AppCompatActivity {
    private ArrayList<String> mCategories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selection);
        loadCategoryMenu();

    }

    private void loadCategoryMenu() {
        final String upstreamUrl = getUpstream() + "/categories";
        System.out.println("Upstream: " + upstreamUrl);

        JsonArrayRequest request = new JsonArrayRequest
                (upstreamUrl, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        createMenuItems(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        NetworkManager.getInstance(this).addToRequestQueue(request);
    }

    private String getUpstream() {
        SharedPreferences prefs = getSharedPreferences("settings", 0);
        String url = prefs.getString("upstream", "");

        return url;
    }

    private void createMenuItems(final JSONArray categories) {
        LinearLayout container = (LinearLayout) findViewById(R.id.categoryContainer);
        for (int i = 0; i < categories.length(); i++)
            try {
                CheckBox button = new CheckBox(getApplicationContext());
                button.setText(categories.getString(i));
                button.setGravity(Gravity.CENTER);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CheckBox button = (CheckBox) view;
                        if (button.isChecked()) {
                            mCategories.add(button.getText().toString());
                        } else {
                            mCategories.remove(button.getText().toString());
                        }
                    }
                });

                container.addView(button, 0);

            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void getQuestionsFromServer(View v) {
        final String upstreamUrl = getUpstream() + "/questions";
        JSONArray params = new JSONArray(mCategories);
        JsonArrayRequest request = new JsonArrayRequest
                (Request.Method.POST, upstreamUrl, params, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        changeToQuizActivity(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        System.out.println("ERROR: " + error.getMessage());
                    }
                });

        NetworkManager.getInstance(this).addToRequestQueue(request);
    }

    private void changeToQuizActivity(JSONArray questions) {
        Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
        intent.putExtra("questions", questions.toString());
        System.out.println("Questions: " + questions);
        startActivity(intent);
    }

}
