package com.example.photostore;

import android.app.Application;

import com.example.photostore.Models.CreatedImage;
import com.example.photostore.Models.FavoriteImage;
import com.example.photostore.Models.ScheduledImage;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(ScheduledImage.class);
        ParseObject.registerSubclass(CreatedImage.class);
        ParseObject.registerSubclass(FavoriteImage.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("jQWMIO2oLGJwATSTsMWqSols5rM3aNJiZByDZvcm")
                .clientKey("pftJVjnzCGFN3xg7jcirfqNtk39GRQC8Sm4AgQg4")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
