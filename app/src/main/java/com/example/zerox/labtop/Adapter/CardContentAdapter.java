package com.example.zerox.labtop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zerox.labtop.BuyActivity;
import com.example.zerox.labtop.ContentProvider.LaptopDBHelper;
import com.example.zerox.labtop.Detail_Activity;
import com.example.zerox.labtop.Model.Laptop;
import com.example.zerox.labtop.R;
import com.example.zerox.labtop.Utility;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Zerox on 11/11/2016.
 */
public class CardContentAdapter extends RecyclerView.Adapter<CardContentAdapter.CustomViewHolder> {
    LaptopDBHelper laptopDBHelper;
    private List<Laptop> Laptoplist;
    private Context mContext;
    private ShareActionProvider mShareActionProvider;

    public CardContentAdapter(Context context, List<Laptop> Laptoplist, LaptopDBHelper laptopDBHelper) {
        this.Laptoplist = Laptoplist;
        this.mContext = context;
        this.laptopDBHelper = laptopDBHelper;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card, null);
        CardContentAdapter.CustomViewHolder viewHolder = new CardContentAdapter.CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CardContentAdapter.CustomViewHolder customViewHolder, int i) {
        final Laptop laptop = Laptoplist.get(i);

        //Render image using Picasso library
        if (!TextUtils.isEmpty(laptop.getImage())) {
            Picasso.with(mContext)
                    .load(laptop.getImage())
                    .error(R.mipmap.ic_launcher)
                    .into(customViewHolder.imageView);

        }

        //Setting text view title
        customViewHolder.TitleTv.setText(laptop.getTitle());
        customViewHolder.DescriptionTv.setText(laptop.getDescription());
        customViewHolder.price_Textview.setText(laptop.getPrice() + "  EGP");
        customViewHolder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.insert(laptopDBHelper, laptop.getId(), laptop.getImage(), laptop.getPrice(), laptop.getDescription(), laptop.getTitle());
                Snackbar.make(v, "Added to Favorites!", Snackbar.LENGTH_LONG).show();
            }
        });
        customViewHolder.order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();
                Intent intent = new Intent(context, BuyActivity.class).putExtra(Detail_Activity.EXTRA_OBJECT, laptop);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != Laptoplist ? Laptoplist.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView TitleTv;
        protected TextView DescriptionTv;
        protected ImageButton favorite;
        protected ImageButton order;
        protected TextView price_Textview;

        public CustomViewHolder(View view) {
            super(view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, Detail_Activity.class);
                    intent.putExtra(Detail_Activity.EXTRA_POSITION, getAdapterPosition());
                    context.startActivity(intent);
                }
            });

            this.imageView = (ImageView) view.findViewById(R.id.card_image);
            this.TitleTv = (TextView) view.findViewById(R.id.card_title);
            this.DescriptionTv = (TextView) view.findViewById(R.id.card_text);
            this.price_Textview = (TextView) view.findViewById(R.id.price_Textview);
            this.favorite = (ImageButton) view.findViewById(R.id.favorite_button);
            this.order = (ImageButton) view.findViewById(R.id.order_button);

        }
    }

}
