package com.example.zerox.labtop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zerox.labtop.Detail_Activity;
import com.example.zerox.labtop.Model.Laptop;
import com.example.zerox.labtop.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListContentAdapter extends RecyclerView.Adapter<ListContentAdapter.CustomViewHolder> {
    private List<Laptop> Laptoplist;
    private Context mContext;


    public ListContentAdapter(Context context, List<Laptop> Laptoplist) {
        this.Laptoplist = Laptoplist;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        Laptop laptop = Laptoplist.get(i);

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

    }

    @Override
    public int getItemCount() {
        return (null != Laptoplist ? Laptoplist.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView TitleTv;
        protected TextView DescriptionTv;

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
            this.imageView = (ImageView) view.findViewById(R.id.list_avatar);
            this.TitleTv = (TextView) view.findViewById(R.id.list_title);
            this.DescriptionTv = (TextView) view.findViewById(R.id.list_desc);
        }
    }
}