package com.luckcheese.twitchtop50.models;

import android.content.Context;

import com.android.volley.toolbox.JsonRequest;
import com.luckcheese.twitchtop50.TwitchTop50App;
import com.luckcheese.twitchtop50.requests.TwitchRequest;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

public class TwitchResult extends JSONObject implements Serializable {

    private List<Game> top;

    public List<Game> getTop() {
        return top;
    }

    // ----- Static methods ---------------------------------------------------

    public static void fetch(Context context) {
        String firstPageUrl = "https://api.twitch.tv/kraken/games/top?limit=50";
        JsonRequest stringRequest = new TwitchRequest(context, firstPageUrl);
        TwitchTop50App.getQueue().add(stringRequest);
    }
}
