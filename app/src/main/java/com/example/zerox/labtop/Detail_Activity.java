package com.example.zerox.labtop;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zerox.labtop.Model.Laptop;
import com.squareup.picasso.Picasso;

public class Detail_Activity extends AppCompatActivity {
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_OBJECT = "laptop";
    int postion;
    Laptop laptop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_);

        postion = getIntent().getIntExtra(EXTRA_POSITION, 0);
        laptop = MainActivity.list.get(postion);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        // Set title of Detail page
        collapsingToolbar.setTitle(laptop.getTitle());

        TextView placeDetail = (TextView) findViewById(R.id.laptop_detail);
        placeDetail.setText(laptop.getDescription());
        TextView placeLocation = (TextView) findViewById(R.id.laptop_price);
        placeLocation.setText(laptop.getPrice() + "  EGP");
        ImageView placePicutre = (ImageView) findViewById(R.id.image);
        Picasso.with(getBaseContext())
                .load(laptop.getImage())
                .error(R.mipmap.ic_launcher)
                .into(placePicutre);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.buyFap);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), BuyActivity.class).putExtra(Detail_Activity.EXTRA_OBJECT, laptop);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


}
