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
        return inflater.inflate(getViewType().viewResourceId, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        configAdapter();
    }

    private void configAdapter() {
        AbsListView listView = (AbsListView) getView().findViewById(R.id.listView);
        listView.setAdapter(new GamesListAdapter(getActivity(), getViewType().listItemResourceId));
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
