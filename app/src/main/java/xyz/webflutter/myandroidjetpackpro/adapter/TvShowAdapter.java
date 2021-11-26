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

import xyz.webflutter.myandroidjetpackpro.R;
import xyz.webflutter.myandroidjetpackpro.ui.detail.tvshow.DetailTvShowActivity;
import xyz.webflutter.myandroidjetpackpro.ui.tvshow.TvShowModels;

import static xyz.webflutter.myandroidjetpackpro.BuildConfig.IMAGE;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {
    private final Activity mActivity;
    private final List<TvShowModels> mModels = new ArrayList<>();

    public TvShowAdapter(Activity activity) {
        this.mActivity = activity;
    }

    private List<TvShowModels> getListTvShow() {
        return mModels;
    }

    public void setTvShow(List<TvShowModels> models) {
        if (models == null) return;
        mModels.clear();
        mModels.addAll(models);
    }

    @NonNull
    @Override
    public TvShowAdapter.TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowAdapter.TvShowViewHolder holder, final int position) {
        holder.title.setText(getListTvShow().get(position).getName());
        holder.overview.setText(getListTvShow().get(position).getOverview());
        holder.itemView.setOnClickListener(v -> {
            Intent in = new Intent(mActivity, DetailTvShowActivity.class);
            in.putExtra(DetailTvShowActivity.EXTRA_ID, getListTvShow().get(position).getId());
            mActivity.startActivity(in);
        });
        Glide.with(holder.itemView.getContext())
                .load(IMAGE + getListTvShow().get(position).getBackdrop_path())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return getListTvShow().size();
    }

    class TvShowViewHolder extends RecyclerView.ViewHolder {
        final TextView title, overview;
        final ImageView poster;

        TvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_movie);
            overview = itemView.findViewById(R.id.date_movie);
            poster = itemView.findViewById(R.id.poster_movie);
        }
    }
}