package com.example.corne.restaurant;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    ArrayList<String> categories = new ArrayList<>();
    Callback activity;

    // Constructor
    public CategoriesRequest(Context context) {
        this.context = context;
    }

    // Callback function
    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    // Method that makes a queue and tries to add this request to it
    void getCategories(Callback activity){
        this.activity = activity;
        String url = "https://resto.mprog.nl/categories";

        RequestQueue queue = Volley.newRequestQueue(context);
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
            queue.add(jsonObjectRequest);
        }
        catch (Exception error){

            // Return an error message if the request is not succesfull
            Log.e("Request", error.getMessage());
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        // Start method in CategoriesActivity when the request fails
        activity.gotCategoriesError(error.getMessage());
    }

    @Override

    // If the request is succesfull
    public void onResponse(JSONObject response) {

        // Try to transform the JSONArray to a list of the categories
        try {
            JSONArray categoriesArray = response.getJSONArray("categories");
            for (int i = 0; i < categoriesArray.length(); i++){
                categories.add(categoriesArray.getString(i));
            }
        }
        catch (JSONException e) {
            Log.e("Request", e.getMessage());
        }
        activity.gotCategories(categories);
    }

}
