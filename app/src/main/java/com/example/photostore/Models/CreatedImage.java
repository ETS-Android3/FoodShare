package com.example.photostore.Models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

@ParseClassName("Created")
public class CreatedImage extends ParseObject {

    public static final String KEY_SCHEDULED_PIC = "scheduled";
    public static final String KEY_CREATED_PIC = "created";
    public static final String KEY_CREATED_AT = "createdAt";

    public String getScheduledPic() {
        return getString(KEY_SCHEDULED_PIC);
    }

    public void setScheduledPic(String image) {
        put(KEY_SCHEDULED_PIC, image);
    }

    public ParseFile getCreatedPic() {
        return getParseFile(KEY_CREATED_PIC);
    }

    public void setCreatedPic(ParseFile image) {
        put(KEY_CREATED_PIC, image);
    }

}
