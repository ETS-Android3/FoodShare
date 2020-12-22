package com.example.photostore.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Scheduled")
public class ScheduledImage extends ParseObject {

    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "user";
    public static final String KEY_DATE = "date";

    public String getImage() {
        return getString(KEY_IMAGE);
    }

    public void setImage(String image) {
        put(KEY_IMAGE, image);
    }

    public String getDate() {
        return getString(KEY_DATE);
    }

    public void setDate (String description) {
        put(KEY_DATE, description);
    }

}
