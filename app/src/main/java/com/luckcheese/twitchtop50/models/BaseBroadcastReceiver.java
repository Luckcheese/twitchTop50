package com.luckcheese.twitchtop50.models;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BaseBroadcastReceiver<T> extends BroadcastReceiver {

    private BaseBroadCastListener<T> listener;

    public BaseBroadcastReceiver(BaseBroadCastListener<T> listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getBooleanExtra("success", false)) {
            listener.onSuccess((T) intent.getSerializableExtra("data"));
        } else {
            listener.onError((Exception) intent.getSerializableExtra("error"));
        }
    }

    // ----- Related classes --------------------------------------------------

    public interface BaseBroadCastListener<T> {
        void onSuccess(T data);

        void onError(Exception e);
    }
}
