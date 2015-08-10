package com.luckcheese.twitchtop50.models;

import android.content.Context;

import com.android.volley.toolbox.JsonRequest;
import com.luckcheese.twitchtop50.TwitchTop50App;
import com.luckcheese.twitchtop50.requests.TwitchRequest;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

public class TwitchReturnModel extends JSONObject implements Serializable {

    private int _total;
    private Links _links;
    private List<Game.Entry> top;

    public List<Game.Entry> getTop() {
        return top;
    }

    // ----- Static methods ---------------------------------------------------

    public static void fetch(Context context) {
        String firstPageUrl = "https://api.twitch.tv/kraken/games/top?limit=50";
        JsonRequest stringRequest = new TwitchRequest(context, firstPageUrl);
        TwitchTop50App.getQueue().add(stringRequest);
    }

    // ----- Related classes --------------------------------------------------

    public static final class Links implements Serializable {
        private String self;
        private String next;
        private String prev;
    }
}
