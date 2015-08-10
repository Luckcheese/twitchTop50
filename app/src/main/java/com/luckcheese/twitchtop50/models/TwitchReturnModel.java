package com.luckcheese.twitchtop50.models;

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

    // ----- Related classes --------------------------------------------------

    public static final class Links implements Serializable {
        private String self;
        private String next;
        private String prev;
    }
}
