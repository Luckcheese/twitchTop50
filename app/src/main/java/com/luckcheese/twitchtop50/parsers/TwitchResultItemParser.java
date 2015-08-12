package com.luckcheese.twitchtop50.parsers;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.luckcheese.twitchtop50.models.Game;

import java.lang.reflect.Type;

public class TwitchResultItemParser implements JsonDeserializer<Game> {

    private Gson gson = new Gson();

    @Override
    public Game deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement gameJson = json.getAsJsonObject().get("game");
        return gson.fromJson(gameJson, Game.class);
    }
}
