package com.antonblue16.mycatalogueuiux;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>
{
    private List<FilmItems> filmList;
    private Context context;

    public NowPlayingAdapter(List<FilmItems> filmList, Context context)
    {
        this.filmList = filmList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_now_playing, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
    {
        final FilmItems itemList = filmList.get(position);
        holder.nameFilm.setText(itemList.getFilmName());
        holder.synopsisFilm.setText(itemList.getFilmSynopsis());
        holder.dateTimeFilm.setText(itemList.getFilmDateTime());

        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w500/"+itemList.getImageFilm())
                .into(holder.imageFilm);

        holder.btnDetail.setOnClickListener(new ButtonOnClickListener(position, new ButtonOnClickListener.ItemButtonCallback()
        {
            @Override
            public void onItemClicked(View view, int position)
            {
                Toast.makeText(context, "Detail: "+itemList.getFilmSynopsis(), Toast.LENGTH_SHORT).show();
            }
        }));

        holder.btnShare.setOnClickListener(new ButtonOnClickListener(position, new ButtonOnClickListener.ItemButtonCallback()
        {
            @Override
            public void onItemClicked(View view, int position)
            {
                Toast.makeText(context, "Share:" +itemList.getFilmName(), Toast.LENGTH_SHORT).show();
            }
        }));

        holder.detailFilm.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                FilmItems itemFilmList = filmList.get(position);
                Intent intent = new Intent(context, DetailFilmActivity.class);
                intent.putExtra(DetailFilmActivity.EXTRA_NAME, itemFilmList.getFilmName());
                intent.putExtra(DetailFilmActivity.EXTRA_SYNOPSIS, itemFilmList.getFilmSynopsis());
                intent.putExtra(DetailFilmActivity.EXTRA_AVERAGE, itemFilmList.getFilmRate());
                intent.putExtra(DetailFilmActivity.EXTRA_LANGUAGE, itemFilmList.getFilmLanguage());
                intent.putExtra(DetailFilmActivity.EXTRA_DATETIME, itemFilmList.getFilmDateTime());
                intent.putExtra(DetailFilmActivity.EXTRA_IMAGE, itemFilmList.getImageFilm());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return filmList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView nameFilm, synopsisFilm, dateTimeFilm;
        public ImageView imageFilm;
        public Button btnDetail, btnShare;
        public CardView detailFilm;

        public ViewHolder(View itemView)
        {
            super(itemView);
            nameFilm = itemView.findViewById(R.id.tvTitleFilm);
            synopsisFilm = itemView.findViewById(R.id.tvOverviewFilm);
            dateTimeFilm = itemView.findViewById(R.id.tvDateTimeFilm);
            imageFilm = itemView.findViewById(R.id.ivPosterFilm);
            btnDetail = itemView.findViewById(R.id.btnDetails);
            btnShare = itemView.findViewById(R.id.btnShare);
            detailFilm = itemView.findViewById(R.id.cardViewNowPlaying);
        }
    }


}
