package com.luckcheese.twitchtop50.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.luckcheese.twitchtop50.R;

public class MainActivity extends Activity {

    public static final String INTENT_VIEW_TYPE = "com.luckcheese.twitchtop50.INTENT_VIEW_TYPE";

    private ViewTypes viewType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().hasExtra(INTENT_VIEW_TYPE)) {
            viewType = (ViewTypes) getIntent().getSerializableExtra(INTENT_VIEW_TYPE);
        } else {
            viewType = ViewTypes.ListView;
        }

        int adapterResource = 0;
        switch (viewType) {
            case GridView:
                setContentView(R.layout.activity_main_grid);
                adapterResource = R.layout.grid_item;
                break;

            case ListView:
                setContentView(R.layout.activity_main_list);
                adapterResource = R.layout.list_item;
                break;
        }

        AbsListView listView = (AbsListView) findViewById(R.id.listView);
        listView.setAdapter(new ListAdapter(this, adapterResource));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        switch (viewType) {
            case GridView:
                inflater.inflate(R.menu.activity_main_grid, menu);
                break;

            case ListView:
                inflater.inflate(R.menu.activity_main_list, menu);
                break;
        }
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if (item.getItemId() != R.id.toggleView) return false;

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        switch (viewType) {
            case GridView:
                intent.putExtra(INTENT_VIEW_TYPE, ViewTypes.ListView);
                break;

            case ListView:
                intent.putExtra(INTENT_VIEW_TYPE, ViewTypes.GridView);
                break;
        }

        startActivity(intent);
        return true;
    }

    // ----- Related classes --------------------------------------------------

    public static enum ViewTypes {
        ListView, GridView
    }

    private static final class ListAdapter extends ArrayAdapter<Void> {

        private LayoutInflater inflater;
        private int resource;

        public ListAdapter(Context context, int resource) {
            super(context, resource);

            inflater = LayoutInflater.from(context);
            this.resource = resource;
        }

        @Override
        public int getCount() {
            return 50;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(resource, parent, false);
            }

            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
            imageView.setImageResource(R.drawable.ic_launcher);

            TextView textView = (TextView) convertView.findViewById(R.id.textView);
            textView.setText("Line " + position);

            return convertView;
        }
    }
}
