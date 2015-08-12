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

    private ViewType viewType;

    public static GamesFragment newInstance(ViewType viewType) {
        Bundle b = new Bundle();
        b.putSerializable("viewType", viewType);

        GamesFragment frag = new GamesFragment();
        frag.setArguments(b);

        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutResourceId;
        switch (getViewType()) {
            case ListView:
                layoutResourceId = R.layout.fragment_games_list;
                break;

            default:
                layoutResourceId = R.layout.fragment_games_grid;
                break;
        }

        return inflater.inflate(layoutResourceId, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        configAdapter();
    }

    private void configAdapter() {
        int adapterResource = 0;
        switch (getViewType()) {
            case GridView:
                adapterResource = R.layout.grid_item;
                break;

            default:
                adapterResource = R.layout.list_item;
                break;
        }
        AbsListView listView = (AbsListView) getView().findViewById(R.id.listView);
        listView.setAdapter(new GamesListAdapter(getActivity(), adapterResource));
    }

    public ViewType getViewType() {
        return (ViewType) getArguments().getSerializable("viewType");
    }

    public void setGames(TwitchResult games) {
        AbsListView listView = (AbsListView) getView().findViewById(R.id.listView);
        GamesListAdapter adapter = (GamesListAdapter) listView.getAdapter();
        adapter.setItems(games.getTop());
        adapter.notifyDataSetChanged();
    }

    // ----- Related classes --------------------------------------------------

    public enum ViewType {
        ListView, GridView
    }
}
