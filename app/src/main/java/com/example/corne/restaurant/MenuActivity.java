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

        Intent intent = getIntent();
        String retrievedCategory = intent.getStringExtra("categories");

        MenuItemRequest x = new MenuItemRequest(this, retrievedCategory);
        x.getMenuItems(this);
        Toast.makeText(this, retrievedCategory, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void gotMenuItems(ArrayList<MenuItem> menuItems) {
        Toast.makeText(this, "some menu's", Toast.LENGTH_LONG).show();

        // instantiate MenuAdapter
        MenuAdapter adapter = new MenuAdapter(this, R.layout.menu_item, menuItems);
        ListView listView = findViewById(R.id.menuList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new onItemClickListener());
    }

    private class onItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MenuItem menuItem = (MenuItem) parent.getItemAtPosition(position);
            Intent intent = new Intent (MenuActivity.this, MenuItemActivity.class);
            intent.putExtra("item", menuItem);
            startActivity(intent);
        }
    }

    @Override
    public void gotMenuItemsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
