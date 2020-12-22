package com.example.photostore.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Favorites")
public class FavoriteImage extends ParseObject {

    public static final String KEY_IMAGE = "image";

    public String getImage() {
        return getString(KEY_IMAGE);
    }

    public void setImage(String image) {
        put(KEY_IMAGE, image);
    }

}
