package com.example.jonas.eduquest;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
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
        final String upstreamUrl = getUpstream().concat("/categories");
        JsonArrayRequest request = new JsonArrayRequest
                (upstreamUrl, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        if (gotContent(response)) {
                            createMenuItems(response);
                        } else {
                            DialogFragment dialog = new InvalidUpstreamDialog();
                            dialog.show(getSupportFragmentManager(), "noupstream");
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        DialogFragment dialog = new InvalidUpstreamDialog();
                        dialog.show(getSupportFragmentManager(), "noupstream");
                    }
                });

        NetworkManager.getInstance(this).addToRequestQueue(request);
    }

    private String getUpstream() {
        SharedPreferences prefs = getSharedPreferences("settings", 0);
        String url = prefs.getString("upstream", "");

        return url;
    }

    private boolean gotContent(JSONArray response) {
        return response.length() != 0;
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

    public void onCategoryBtnClicked(View v) {
        if (!mCategories.isEmpty()) {
            getQuestionsFromServer();
        } else {
            DialogFragment dialog = new NoCategorySelectedDialog();
            dialog.show(getSupportFragmentManager(), "nocategory");
        }
    }

    public void getQuestionsFromServer() {
        final String upstreamUrl = getUpstream().concat("/questions");
        JSONArray params = new JSONArray(mCategories);
        JsonArrayRequest request = new JsonArrayRequest
                (Request.Method.POST, upstreamUrl, params, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length() > 0) {
                            changeToQuizActivity(response);
                        } else {
                            DialogFragment dialog = new NoQuestionsDialog();
                            dialog.show(getSupportFragmentManager(), "noquestion");
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        DialogFragment dialog = new InvalidUpstreamDialog();
                        dialog.show(getSupportFragmentManager(), "noupstream");
                    }
                });

        NetworkManager.getInstance(this).addToRequestQueue(request);
    }

    private void changeToQuizActivity(JSONArray questions) {
        Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
        intent.putExtra("questions", questions.toString());
        startActivity(intent);
    }

}
