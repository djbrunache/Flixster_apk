package com.codepath.bourdeau_melinda.flixsterapk.adapters;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.bourdeau_melinda.flixsterapk.DetailActivity;
import com.codepath.bourdeau_melinda.flixsterapk.databinding.ItemMovieBinding;
import com.codepath.bourdeau_melinda.flixsterapk.databinding.ItemMoviePopularBinding;
import com.codepath.bourdeau_melinda.flixsterapk.models.Movie;
import com.codepath.bourdeau_melinda.flixsterapk.R;

import org.parceler.Parcels;

import java.util.List;

public class movieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static Context context;
    List<Movie> movies;
    private static final int POSTER = 0, BACK = 1;
    private static final double RATING = 5;

    public movieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }
    @Override
    public int getItemViewType(int position) {
        double rating = movies.get(position).getRating();
        if (rating > (float) RATING)
            return BACK;
        return POSTER;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Log.d("movieAdapter", "onCreateViewHolder");
        RecyclerView.ViewHolder viewholder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == 1){
            ItemMoviePopularBinding itempop = DataBindingUtil.inflate(inflater, R.layout.item_movie_popular, parent, false);
            viewholder = new popViewHolder(itempop);
        }
        else {
            ItemMovieBinding item = DataBindingUtil.inflate(inflater, R.layout.item_movie, parent, false);
            viewholder = new ViewHolder(item);
        }
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        Log.d("movieAdapter", "onBindViewHolder" + position);
        Movie movie = movies.get(position);
//        holder.bind(movie);

        if (getItemViewType(position) == 0) {
            popViewHolder pviewholder =(popViewHolder) holder;
            pviewholder.bind(movie);

        }
        else{
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.bind(movie);
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ItemMovieBinding itemMovieBinding;

        public ViewHolder(@NonNull ItemMovieBinding movieBinding) {
            super(movieBinding.getRoot());
            this.itemMovieBinding = movieBinding;
        }

        public void bind(Movie movie) {
            itemMovieBinding.setMovie(movie);
            itemMovieBinding.executePendingBindings();
            itemMovieBinding.container.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("movie", Parcels.wrap(movie));
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation((Activity) context, itemMovieBinding.ivPoster, "transcontainer");
                    context.startActivity(i);
                }
            });
        }
    }
    public class popViewHolder extends RecyclerView.ViewHolder {


        ItemMoviePopularBinding itemMoviePopularBinding;

        public popViewHolder(@NonNull ItemMoviePopularBinding itemMoviePopularBinding) {
            super(itemMoviePopularBinding.getRoot());
            this.itemMoviePopularBinding = itemMoviePopularBinding;
        }

        public void bind(Movie movie) {
            itemMoviePopularBinding.setMovie(movie);
            itemMoviePopularBinding.executePendingBindings();

            itemMoviePopularBinding.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(i);
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation((Activity) context, itemMoviePopularBinding.ivPoster, "transcontainer");
                    context.startActivity(i, options.toBundle());
                }
            });
        }
    }


    public static class BindingAdapterUtils {
        @BindingAdapter({"imageUrl"})
        public static void loadImage(ImageView png, String ImageUrl) {

            Glide.with(context).
                    load(ImageUrl).
                    fitCenter().
                    placeholder(R.drawable.kaneki).
                    transform(new RoundedCorners(35)).
                    into(png);

        }
        @BindingAdapter({"imageUrl1"})
        public static void loadImage1(ImageView png, String ImageUrl){

            Glide.with(context).
                    load(ImageUrl).
                    fitCenter().
                    placeholder(R.drawable.kaneki).
                    transform(new RoundedCorners(35)).
                    into(png);

        }
    }

}
