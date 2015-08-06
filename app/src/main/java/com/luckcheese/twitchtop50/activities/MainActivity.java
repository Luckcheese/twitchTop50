package com.luckcheese.twitchtop50.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.luckcheese.twitchtop50.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_main_list);
//        ListView listView = (ListView) findViewById(R.id.listView);
//        listView.setAdapter(new ListAdapter(this));

        setContentView(R.layout.activity_main_grid);
        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new ListAdapter(this));
    }

    // ----- Related classes --------------------------------------------------

    private static final class ListAdapter extends ArrayAdapter<Void> {

        private LayoutInflater inflater;

        public ListAdapter(Context context) {
//            super(context, R.layout.list_item);
            super(context, R.layout.grid_item);

            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return 50;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
//                convertView = inflater.inflate(R.layout.list_item, parent, false);
                convertView = inflater.inflate(R.layout.grid_item, parent, false);
            }

            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
            imageView.setImageResource(R.drawable.ic_launcher);

            TextView textView = (TextView) convertView.findViewById(R.id.textView);
            textView.setText("Line " + position);

            return convertView;
        }
    }
}
