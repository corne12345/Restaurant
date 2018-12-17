package com.example.corne.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuItemRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Get clicked category and save it in a string
        Intent intent = getIntent();
        String retrievedCategory = intent.getStringExtra("categories");

        // Make a request for all the menu-items in this category
        MenuItemRequest x = new MenuItemRequest(this, retrievedCategory);
        x.getMenuItems(this);
    }

    @Override
    public void gotMenuItems(ArrayList<MenuItem> menuItems) {

        // instantiate MenuAdapter for listView
        MenuAdapter adapter = new MenuAdapter(this, R.layout.menu_item, menuItems);
        ListView listView = findViewById(R.id.menuList);
        listView.setAdapter(adapter);

        // Set listener for listView of menu items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Start MenuItemActivity and give it the clicked menu item
                MenuItem menuItem = (MenuItem) parent.getItemAtPosition(position);
                Intent intent = new Intent (MenuActivity.this, MenuItemActivity.class);
                intent.putExtra("item", menuItem);
                startActivity(intent);
            }
        });
    }


    @Override
    public void gotMenuItemsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
