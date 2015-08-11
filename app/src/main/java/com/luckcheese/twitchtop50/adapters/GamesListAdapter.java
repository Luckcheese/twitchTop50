package com.luckcheese.twitchtop50.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.luckcheese.twitchtop50.models.Game;
import com.luckcheese.twitchtop50.views.ListItem;

import java.util.List;

public final class GamesListAdapter extends ArrayAdapter<Game.Entry> {

    private LayoutInflater inflater;
    private int resource;

    public GamesListAdapter(Context context, int resource) {
        super(context, resource);

        inflater = LayoutInflater.from(context);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(resource, parent, false);
        }

        Game game = getItem(position).getGame();
        ((ListItem) convertView).setGame(game);

        return convertView;
    }

    public void setItems(List<Game.Entry> items) {
        clear();
        addAll(items);
    }
}
