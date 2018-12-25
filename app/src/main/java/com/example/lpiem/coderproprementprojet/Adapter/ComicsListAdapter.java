package com.example.lpiem.coderproprementprojet.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lpiem.coderproprementprojet.Models.Comic;
import com.example.lpiem.coderproprementprojet.R;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.ArrayList;

public class ComicsListAdapter extends RecyclerView.Adapter<ComicsListAdapter.MyViewHolder> {

    private ArrayList<Comic> comicsList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, date, pageCount;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.image_comic_item);
            title = (TextView) view.findViewById(R.id.title_comic_item);
            date = (TextView) view.findViewById(R.id.date_comic_item);
            pageCount = (TextView) view.findViewById(R.id.number_page_comic_item);
            context = view.getContext();
        }
    }


    public ComicsListAdapter(ArrayList<Comic> comicsList) {
        this.comicsList = comicsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comic_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Comic comic = comicsList.get(position);
        Picasso.get().load(comic.getImage()).into(holder.image);
        holder.title.setText(comic.getTitle());
        holder.date.setText(comic.getDate());
        holder.pageCount.setText(String.valueOf(comic.getPageCount()));
    }

    @Override
    public int getItemCount() {
        return comicsList.size();
    }
}
