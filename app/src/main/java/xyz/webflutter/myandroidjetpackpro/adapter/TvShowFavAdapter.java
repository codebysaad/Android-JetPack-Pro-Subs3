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
import xyz.webflutter.myandroidjetpackpro.ui.detail.tvshow.DetailTvShowActivity;
import xyz.webflutter.myandroidjetpackpro.ui.tvshow.TvShowModels;

import static xyz.webflutter.myandroidjetpackpro.BuildConfig.IMAGE;

public class TvShowFavAdapter extends PagedListAdapter<TvShowModels, TvShowFavAdapter.ViewHolder> {
    private final Activity activity;

    public TvShowFavAdapter(Activity activity) {
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
        final TvShowModels tvShowEntity = getItem(position);

        assert tvShowEntity != null;
        holder.title.setText(tvShowEntity.getName());
        holder.overview.setText(tvShowEntity.getOverview());
        Glide.with(holder.itemView.getContext())
                .load(IMAGE + tvShowEntity.getBackdrop_path())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(holder.poster);
    }

    private static final DiffUtil.ItemCallback<TvShowModels> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<TvShowModels>() {
                @Override
                public boolean areItemsTheSame(@NonNull TvShowModels oldItem, @NonNull TvShowModels newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull TvShowModels oldItem, @NonNull TvShowModels newItem) {
                    return oldItem.equals(newItem);
                }
            };

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final TextView title;
        final TextView overview;
        final ImageView poster;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_movie);
            overview = itemView.findViewById(R.id.date_movie);
            poster = itemView.findViewById(R.id.poster_movie);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(activity, DetailTvShowActivity.class);
            intent.putExtra(DetailTvShowActivity.EXTRA_ID, Objects.requireNonNull(getItem(getAdapterPosition())).getId());
            activity.startActivity(intent);
        }
    }
}