package com.eatit.user.eatit.Model;

/**
 * Created by User on 21-Nov-17.
 */

public class Category {
    private String Name;
    private String Image;

    public Category() {
        Name = "a";
        Image = "a";
    }

    public Category(String name, String image) {
        Name = name;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
