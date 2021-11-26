package xyz.webflutter.myandroidjetpackpro.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Objects;

import xyz.webflutter.myandroidjetpackpro.R;
import xyz.webflutter.myandroidjetpackpro.ui.detail.movie.DetailMovieActivity;
import xyz.webflutter.myandroidjetpackpro.ui.movie.MovieModels;

import static xyz.webflutter.myandroidjetpackpro.BuildConfig.IMAGE;

public class MovieFavoriteAdapter extends PagedListAdapter<MovieModels, MovieFavoriteAdapter.ViewHolder> {
    private final Activity activity;
    public MovieFavoriteAdapter(Activity activity) {
        super(DIFF_CALLBACK);
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final MovieModels movieEntities = getItem(position);

        assert movieEntities != null;
        holder.title.setText(movieEntities.getTitle());
        holder.releaseDate.setText(movieEntities.getOverview());
        Glide.with(holder.itemView.getContext())
                .load(IMAGE + movieEntities.getPoster_path())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(holder.poster);
    }

    private static final DiffUtil.ItemCallback<MovieModels> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MovieModels>() {
                @Override
                public boolean areItemsTheSame(@NonNull MovieModels oldItem, @NonNull MovieModels newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull MovieModels oldItem, @NonNull MovieModels newItem) {
                    return oldItem.equals(newItem);
                }
            };

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView title;
        final TextView releaseDate;
        final ImageView poster;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_movie);
            releaseDate = itemView.findViewById(R.id.date_movie);
            poster = itemView.findViewById(R.id.poster_movie);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(activity, DetailMovieActivity.class);
            intent.putExtra(DetailMovieActivity.EXTRA_ID, Objects.requireNonNull(getItem(getAdapterPosition())).getId());
            activity.startActivity(intent);
        }
    }
}
