package com.example.zerox.labtop;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zerox.labtop.Adapter.TileContentAdapter;

/**
 * Created by Zerox on 11/11/2016.
 */

public class TileContentFragment extends Fragment {


    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);


        TileContentAdapter adapter = new TileContentAdapter(recyclerView.getContext(), MainActivity.list);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        // Set padding for Tiles
        int tilePadding = getResources().getDimensionPixelSize(R.dimen.tile_padding);
        recyclerView.setPadding(tilePadding, tilePadding, tilePadding, tilePadding);
        int TileItem = getResources().getInteger(R.integer.tileitem);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), TileItem));


        return recyclerView;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            int TileItem = getResources().getInteger(R.integer.tileitem);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), TileItem));
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            int TileItem = getResources().getInteger(R.integer.tileitem_land);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), TileItem));


        }

    }
}
