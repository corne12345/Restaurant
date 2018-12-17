package com.example.corne.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import com.squareup.picasso.Picasso;


public class MenuAdapter extends ArrayAdapter<MenuItem> {

    private ArrayList<MenuItem> menuItems;

    // Constructor
    public MenuAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        this.menuItems = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Create a convertView if it doesn't exist yet
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent, false);
        }

        // Set textviews and imageviews to all the items in the screen
        MenuItem menuItem = menuItems.get(position);

        ImageView image = convertView.findViewById(R.id.image);
        String url = menuItem.getImageURL();
        Picasso.get().load(url).into(image); // Library for retrieving images by link

        TextView nameView = convertView.findViewById(R.id.title);
        nameView.setText(menuItem.getName());

        TextView priceView = convertView.findViewById(R.id.price);
        priceView.setText(menuItem.getPrice());

        return convertView;
    }
}
