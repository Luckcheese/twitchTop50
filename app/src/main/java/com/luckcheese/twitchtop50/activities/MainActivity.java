package com.luckcheese.twitchtop50.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.blunderer.materialdesignlibrary.activities.ViewPagerActivity;
import com.blunderer.materialdesignlibrary.handlers.ActionBarDefaultHandler;
import com.blunderer.materialdesignlibrary.handlers.ActionBarHandler;
import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;
import com.luckcheese.twitchtop50.R;
import com.luckcheese.twitchtop50.fragments.GamesFragment;
import com.luckcheese.twitchtop50.models.BroadcastManager;
import com.luckcheese.twitchtop50.models.TwitchReturnModel;

public class MainActivity extends ViewPagerActivity
        implements BroadcastManager.BaseBroadcastReceiver.BaseBroadCastListener<TwitchReturnModel> {

    private BroadcastManager.BaseBroadcastReceiver<TwitchReturnModel> gamesReceiver;

    private TwitchReturnModel top50Games;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gamesReceiver = new BroadcastManager.BaseBroadcastReceiver<>(this);

        setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (top50Games != null) {
                    getCurrentFragment().setGames(top50Games);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        BroadcastManager.register(this, BroadcastManager.Type.TopGames, gamesReceiver);

        TwitchReturnModel.fetch(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        BroadcastManager.unregister(this, gamesReceiver);
    }

    @Override
    public boolean showViewPagerIndicator() {
        return false;
    }

    @Override
    public boolean replaceActionBarTitleByViewPagerPageTitle() {
        return false;
    }

    @Override
    protected boolean enableActionBarShadow() {
        return true;
    }

    @Override
    protected ActionBarHandler getActionBarHandler() {
        return new ActionBarDefaultHandler(this);
    }

    private GamesFragment getCurrentFragment() {
        return (GamesFragment) getSupportFragmentManager().getFragments().get(mViewPager.getCurrentItem());
    }

    // ----- Menu related methods ---------------------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_grid, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != R.id.toggleView) {
            return super.onOptionsItemSelected(item);
        }

        GamesFragment.ViewType viewType = getCurrentFragment().getViewType();
        int nextPos = viewType == GamesFragment.ViewType.ListView ? 1 : 0;
        mViewPager.setCurrentItem(nextPos, true);

        return true;
    }

    // ----- BroadcastManager.BaseBroadcastReceiver.BaseBroadCastListener -----

    @Override
    public void onSuccess(TwitchReturnModel data) {
        top50Games = data;
        getCurrentFragment().setGames(top50Games);
    }

    @Override
    public void onError(Exception e) {
        // do nothing
    }

    // ----- ViewPager --------------------------------------------------------

    @Override
    public ViewPagerHandler getViewPagerHandler() {
        ViewPagerHandler handler = new ViewPagerHandler(this);
        handler.addPage("", GamesFragment.newInstance(GamesFragment.ViewType.ListView));
        handler.addPage("", GamesFragment.newInstance(GamesFragment.ViewType.GridView));
        return handler;
    }

    @Override
    public int defaultViewPagerPageSelectedPosition() {
        return 0;
    }
}
