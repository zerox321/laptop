package com.example.zerox.labtop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zerox.labtop.Adapter.ListContentAdapter;
import com.example.zerox.labtop.ContentProvider.LaptopDBHelper;
import com.example.zerox.labtop.Model.Laptop;

import java.util.List;

/**
 * Created by Zerox on 11/3/2016.
 */

public class ListContentFragment extends Fragment {
    ListContentAdapter adapter;
    LaptopDBHelper laptopDBHelper;
    List<Laptop> detailList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        //this to set delegate/listener back to this class

        //execute the async task
        laptopDBHelper = new LaptopDBHelper(getActivity());
        ///////////
        detailList = MainActivity.list;
        //        Toast.makeText(getContext(),detailList.get(0).getTitle(),Toast.LENGTH_LONG).show();
        adapter = new ListContentAdapter(recyclerView.getContext(), detailList);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }


}