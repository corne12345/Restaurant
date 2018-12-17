package com.example.corne.restaurant;

import java.io.Serializable;

public class MenuItem implements Serializable {
    private String name, description, imageURL, category, price;

    // Constructor
    public MenuItem(String name, String description, String imageURL, String category, String price) {
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.category = category;
        this.price = price;
    }

    // All getters and setters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getCategory() {
        return category;
    }

    public String getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public void setImageURL(String imageURL) {
//        this.imageURL = imageURL;
//    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
