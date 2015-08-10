package com.luckcheese.twitchtop50.requests;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.luckcheese.twitchtop50.TwitchTop50App;
import com.luckcheese.twitchtop50.models.BroadcastManager;
import com.luckcheese.twitchtop50.models.TwitchReturnModel;

import java.io.UnsupportedEncodingException;

public class TwitchRequest extends JsonRequest<TwitchReturnModel> {

    public TwitchRequest(Context context, String url) {
        super(Method.GET, url, null,
                BroadcastManager.getDefaultRequestListener(context, BroadcastManager.Type.TopGames),
                BroadcastManager.getDefaultRequestErrorListener(context, BroadcastManager.Type.TopGames));
    }

    @Override
    protected Response<TwitchReturnModel> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            TwitchReturnModel jsonResult = TwitchTop50App.getGson().fromJson(json, TwitchReturnModel.class);
            Cache.Entry entry = HttpHeaderParser.parseCacheHeaders(response);
            return Response.success(jsonResult, entry);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new VolleyError(e));
        }
    }
}
