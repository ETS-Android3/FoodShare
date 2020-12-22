package com.example.photostore.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.photostore.ImageAPI;
import com.example.photostore.Adapters.ImageAdapter;
import com.example.photostore.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class InspirationFragment extends Fragment {

    private RecyclerView rvImages;
    public static final String FOOD_URL = "https://api.unsplash.com/search/photos?query=food&client_id=nonAxQvFpgpn2UiSUiCCUNW01X_enjogkcMeM-8jlXU";
    public static final String TAG = "Dashboard";
    List<ImageAPI> imageAPIS;

    public InspirationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvImages = view.findViewById(R.id.rvImages);
        imageAPIS = new ArrayList<>();

        final ImageAdapter imageAdapter = new ImageAdapter(getContext(), imageAPIS);

        rvImages.setAdapter(imageAdapter);

        rvImages.setLayoutManager(new GridLayoutManager(getContext(), 2));

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(FOOD_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    imageAPIS.addAll(ImageAPI.fromJsonArray(results));
                    imageAdapter.notifyDataSetChanged();
                    Log.i(TAG, "Images: " + imageAPIS.size());

                } catch (JSONException e) {
                    Log.e(TAG, "JSON exception" + e);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        });

    }
}