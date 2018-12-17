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

public class MenuItemRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Callback activity;
    private Context context;
    private String categoryName;
    private ArrayList<MenuItem> menuItems = new ArrayList<>();

    // Constructor
    public MenuItemRequest(Context context, String retrievedCategory) {
        this.context = context;
        this.categoryName = retrievedCategory;
    }

    // Callback method
    public interface Callback {
        void gotMenuItems(ArrayList<MenuItem> menuItems);
        void gotMenuItemsError(String message);
    }

    // Make the actual request
    public void getMenuItems(Callback activity) {
        this.activity = activity;
        String url = "https://resto.mprog.nl/menu?category=" + categoryName;
        RequestQueue queue = Volley.newRequestQueue(context);
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
            queue.add(jsonObjectRequest);
        } catch (Exception error) {
            Log.e("Request", error.getMessage());
        }
    }

    // When request is succesfull
    @Override
    public void onResponse(JSONObject response) {

        // Retrieve all information from JSON and make a new menuItem and add it to list of items
        try {
            JSONArray menuItemsArray = response.getJSONArray("items");
            for (int i = 0; i < menuItemsArray.length(); i++){
                JSONObject menuItemObject = menuItemsArray.getJSONObject(i);
                String name = menuItemObject.getString("name");
                String description = menuItemObject.getString("description");
                String imageUrl = menuItemObject.getString("image_url");
                String category = menuItemObject.getString("category");
                String price = menuItemObject.getString("price");
                MenuItem menuItem = new MenuItem(name, description, imageUrl, category, price);
                menuItems.add(menuItem);
            }
        }
        catch (JSONException e) {
            Log.e("Request", e.getMessage());
        }
        activity.gotMenuItems(menuItems);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotMenuItemsError(error.getMessage());
    }
}
