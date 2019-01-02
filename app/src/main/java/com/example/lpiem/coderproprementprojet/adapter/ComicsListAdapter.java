package com.example.lpiem.coderproprementprojet.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lpiem.coderproprementprojet.manager.ComicManager;
import com.example.lpiem.coderproprementprojet.models.Comic;
import com.example.lpiem.coderproprementprojet.R;
import com.example.lpiem.coderproprementprojet.presenters.ComicListPresenter;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import io.reactivex.subjects.PublishSubject;

public class ComicsListAdapter extends RecyclerView.Adapter<ComicsListAdapter.MyViewHolder> {

    private ArrayList<Comic> comicsList;
    private ComicManager comicManager;
    public PublishSubject<Integer> clickItemListener = PublishSubject.create();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, date, pageCount;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.image_comic_item);
            title = (TextView) view.findViewById(R.id.title_comic_item);
            date = (TextView) view.findViewById(R.id.date_comic_item);
            pageCount = (TextView) view.findViewById(R.id.number_page_comic_item);
        }
    }

    public ComicsListAdapter(ArrayList<Comic> comicsList, ComicManager comicManager) {
        this.comicsList = comicsList;
        this.comicManager = comicManager;
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
        try {
            holder.image.setImageDrawable(comicManager.getComicPicture(comic.getImage()));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        holder.title.setText(comic.getTitle());
        holder.date.setText(comic.getDate());
        holder.pageCount.setText(String.valueOf(comic.getPageCount()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItemListener.onNext(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return comicsList.size();
    }
}
