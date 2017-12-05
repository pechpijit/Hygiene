package com.tong.hygiene.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tong.hygiene.BaseActivity;
import com.tong.hygiene.R;
import com.tong.hygiene.model.ModelNews;

import java.util.ArrayList;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.VersionViewHolder> {
    ArrayList<ModelNews> list;
    Context context;
    OnItemClickListener clickListener;

    public AdapterNews(Context applicationContext, ArrayList<ModelNews> list) {
        this.context = applicationContext;
        this.list = list;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_news, viewGroup, false);
        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int position) {
        versionViewHolder.title.setText(list.get(position).getInformationName().substring(0,50));
        String temp = String.valueOf(Html.fromHtml(list.get(position).getInformationDetail()));
        versionViewHolder.content.setText(temp.substring(0,50));
        Glide.with(context)
                .load(BaseActivity.BASE_URL_PICTURE+"/news/"+list.get(position).getInformationImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.nopic)
                .into(versionViewHolder.img);
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title,content;
        ImageView img;


        public VersionViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_title);
            content = itemView.findViewById(R.id.txt_content);
            img = itemView.findViewById(R.id.img_news);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, getPosition());
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
}