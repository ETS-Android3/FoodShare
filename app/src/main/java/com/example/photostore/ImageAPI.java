package com.example.photostore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class ImageAPI {

    String image;

    public ImageAPI() {}

    public ImageAPI(JSONObject jsonObject) throws JSONException {
        image = jsonObject.getString("full");
    }

    public static List<ImageAPI> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<ImageAPI> imageAPIS = new ArrayList<>();
        for (int i = 0; i < movieJsonArray.length(); i++) {
            imageAPIS.add(new ImageAPI(movieJsonArray.getJSONObject(i).getJSONObject("urls")));
        }
        return imageAPIS;
    }

    public String getImage() {
        return image;
    }
}
