package com.example.zerox.labtop;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zerox.labtop.Adapter.CardContentAdapter;
import com.example.zerox.labtop.ContentProvider.LaptopDBHelper;
import com.example.zerox.labtop.Model.Laptop;

import java.util.List;

/**
 * Created by Zerox on 11/11/2016.
 */

public class CardContentFragment extends Fragment {
    CardContentAdapter adapter;
    LaptopDBHelper laptopDBHelper;
    RecyclerView recyclerView;

    List<Laptop> detailList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        laptopDBHelper = new LaptopDBHelper(getActivity());

        detailList = MainActivity.list;
        adapter = new CardContentAdapter(getContext(), detailList, laptopDBHelper);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        int Item = getResources().getInteger(R.integer.carditem);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), Item));
        return recyclerView;

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            int TileItem = getResources().getInteger(R.integer.carditem);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), TileItem));
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            int TileItem = getResources().getInteger(R.integer.carditem_land);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), TileItem));


        }

    }

}
