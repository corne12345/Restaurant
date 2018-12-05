package com.example.corne.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class MenuItemActivity extends AppCompatActivity {

    MenuItem retrievedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);
        Intent intent = getIntent();
        retrievedItem = (MenuItem) intent.getSerializableExtra("item");

        ImageView image =  findViewById(R.id.image);
        String url = retrievedItem.getImageURL();
        Picasso.get().load(url).into(image);

        TextView name = findViewById(R.id.name);
        String nameString = retrievedItem.getName();
        name.setText(nameString);

        TextView description = findViewById(R.id.description);
        String descriptionString = retrievedItem.getDescription();
        description.setText(descriptionString);

        TextView price = findViewById(R.id.price);
        String priceString = retrievedItem.getPrice();
        price.setText(priceString);



    }

}
