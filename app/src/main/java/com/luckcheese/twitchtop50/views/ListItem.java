package com.luckcheese.twitchtop50.views;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.luckcheese.twitchtop50.R;
import com.luckcheese.twitchtop50.models.Game;
import com.squareup.picasso.Picasso;

public class ListItem extends CardView {

    private TextView textView;
    private ImageView imageView;

    public ListItem(Context context) {
        super(context);
    }

    public ListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    public void setGame(Game game) {
        textView.setText(game.getName());

        Picasso.with(getContext())
                .load(game.getLogoImageUrl(Game.ImageLinks.ImageSize.Medium))
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
    }
}
