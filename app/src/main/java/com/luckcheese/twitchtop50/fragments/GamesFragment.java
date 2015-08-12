package com.luckcheese.twitchtop50.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.luckcheese.twitchtop50.R;
import com.luckcheese.twitchtop50.adapters.GamesListAdapter;
import com.luckcheese.twitchtop50.models.TwitchResult;

public class GamesFragment extends Fragment {

    private GamesListAdapter adapter;

    public static GamesFragment newInstance(ViewType viewType) {
        Bundle b = new Bundle();
        b.putSerializable("viewType", viewType);

        GamesFragment frag = new GamesFragment();
        frag.setArguments(b);

        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new GamesListAdapter(getActivity(), getViewType().listItemResourceId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragView = inflater.inflate(getViewType().viewResourceId, container, false);
        ((AbsListView) fragView.findViewById(R.id.listView)).setAdapter(adapter);
        return fragView;
    }

    public ViewType getViewType() {
        return (ViewType) getArguments().getSerializable("viewType");
    }

    public void setGames(TwitchResult games) {
        adapter.setItems(games.getTop());
        adapter.notifyDataSetChanged();
    }

    // ----- Related classes --------------------------------------------------

    public enum ViewType {
        ListView(R.layout.fragment_games_list, R.layout.list_item),
        GridView(R.layout.fragment_games_grid, R.layout.grid_item);

        public final int viewResourceId;
        public final int listItemResourceId;

        ViewType(int viewResourceId, int listItemResourceId) {
            this.viewResourceId = viewResourceId;
            this.listItemResourceId = listItemResourceId;
        }
    }
}
