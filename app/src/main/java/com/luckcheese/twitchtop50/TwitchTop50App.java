package com.luckcheese.twitchtop50;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.luckcheese.twitchtop50.models.Game;
import com.luckcheese.twitchtop50.parsers.TwitchResultItemParser;

public class TwitchTop50App extends Application {

    private static RequestQueue queue;
    private static Gson gson;

    @Override
    public void onCreate() {
        super.onCreate();
        initRequestHelpers();
    }

    public static RequestQueue getQueue() {
        return queue;
    }

    public static Gson getGson() {
        return gson;
    }

    // ----- Private methods --------------------------------------------------

    private void initRequestHelpers() {
        queue = Volley.newRequestQueue(this);

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Game.class, new TwitchResultItemParser());
        gson = builder.create();
    }
}
