package com.example.corne.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // Make a request for the restaurant cateogories
        CategoriesRequest x = new CategoriesRequest(this);
        x.getCategories(this);
    }


    // If the request is succesfull, this method will start
    @Override
    public void gotCategories(final ArrayList<String> categories) {

        // Make an adapter for the listview and place the categories
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories);
        ListView listView = findViewById(R.id.categoriesList);
        listView.setAdapter(categoriesAdapter);

        // Set listener for clicks on the categories
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // When clicked, start MenuActivity and send the chosen category as extra
                Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
                intent.putExtra("categories", categories.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
