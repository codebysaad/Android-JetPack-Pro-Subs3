package xyz.webflutter.myandroidjetpackpro.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import xyz.webflutter.myandroidjetpackpro.ui.detail.movie.DetailMovieActivity;
import xyz.webflutter.myandroidjetpackpro.R;
import xyz.webflutter.myandroidjetpackpro.ui.movie.MovieModels;

import static xyz.webflutter.myandroidjetpackpro.BuildConfig.IMAGE;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private final Activity mActivity;
    private final List<MovieModels> mModels = new ArrayList<>();

    public MovieAdapter(Activity activity){
        this.mActivity = activity;
    }
    private List<MovieModels> getListMovies(){
        return mModels;
    }

    public void setMovies(List<MovieModels> models){
        if (models == null) return;
        mModels.clear();
        mModels.addAll(models);
    }
    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, final int position) {
        holder.title.setText(getListMovies().get(position).getTitle());
        holder.releaseDate.setText(getListMovies().get(position).getRelease_date());
        holder.itemView.setOnClickListener(v -> {
            Intent in = new Intent(mActivity, DetailMovieActivity.class);
            in.putExtra(DetailMovieActivity.EXTRA_ID, getListMovies().get(position).getId());
            mActivity.startActivity(in);
        });
        Glide.with(holder.itemView.getContext())
                .load(IMAGE + getListMovies().get(position).getPoster_path())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return getListMovies().size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        final TextView title, releaseDate;
        final ImageView poster;
        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_movie);
            releaseDate = itemView.findViewById(R.id.date_movie);
            poster = itemView.findViewById(R.id.poster_movie);
        }
    }
}
