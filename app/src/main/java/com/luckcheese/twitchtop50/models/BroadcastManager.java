package com.luckcheese.twitchtop50.models;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public class BroadcastManager {

    public static void register(Context context, Type type, BroadcastReceiver receiver) {
        LocalBroadcastManager.getInstance(context).registerReceiver(receiver, new IntentFilter(type.id));
    }

    public static void unregister(Context context, BroadcastReceiver receiver) {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver);
    }

    public static Response.Listener<TwitchReturnModel> getDefaultRequestListener(final Context context, final Type type) {
        return new Response.Listener<TwitchReturnModel>() {

            @Override
            public void onResponse(TwitchReturnModel response) {
                Intent intent = new Intent(type.id);
                intent.putExtra("success", true);
                intent.putExtra("data", response);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        };
    }

    public static Response.ErrorListener getDefaultRequestErrorListener(final Context context, final Type type) {
        return new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Intent intent = new Intent(type.id);
                intent.putExtra("success", false);
                intent.putExtra("error", error);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        };
    }

    // ----- Related classes --------------------------------------------------

    public enum Type {
        TopGames("twitch_top_games");

        private final String id;

        Type(String id) {
            this.id = id;
        }
    }

}
