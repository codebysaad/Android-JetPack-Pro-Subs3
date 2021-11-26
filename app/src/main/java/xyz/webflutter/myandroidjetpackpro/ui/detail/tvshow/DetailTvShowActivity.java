package xyz.webflutter.myandroidjetpackpro.ui.detail.tvshow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Objects;

import xyz.webflutter.myandroidjetpackpro.R;
import xyz.webflutter.myandroidjetpackpro.adapter.TvShowAdapter;
import xyz.webflutter.myandroidjetpackpro.ui.tvshow.TvShowModels;
import xyz.webflutter.myandroidjetpackpro.utils.ViewModelFactory;

import static xyz.webflutter.myandroidjetpackpro.BuildConfig.IMAGE;

public class DetailTvShowActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "EXTRA_ID_TV_SHOW";
    private TextView title, releaseDate, overview;
    private ImageView ivPoster;
    private List<TvShowModels> models;
    private ProgressBar progressBar;
    private DetailTvViewModels viewModels;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);
        Toolbar toolbar = findViewById(R.id.toolbar_tv_show);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        title = findViewById(R.id.title_movie_detail);
        releaseDate = findViewById(R.id.release_date);
        overview = findViewById(R.id.overview_movie_detail);
        ivPoster = findViewById(R.id.image_poster);
        progressBar = findViewById(R.id.progress_detail);
        viewModels = obtainViewModel(this);
        TvShowAdapter adapter = new TvShowAdapter(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String id = bundle.getString(EXTRA_ID);
            if (id != null) {
                viewModels.setTvShowId(id);
                adapter.setTvShow(models);
                adapter.notifyDataSetChanged();
                viewModels.getTvShow().observe(this, tvShowDetails -> {
                    if (tvShowDetails != null) {
                        switch (tvShowDetails.status) {
                            case LOADING:
                                progressBar.setVisibility(View.VISIBLE);
                                break;
                            case SUCCESS:
                                progressBar.setVisibility(View.GONE);
                                TvShowModels tvShowModels = tvShowDetails.data;
                                assert tvShowModels != null;
                                initializePopulate(tvShowModels);
                                break;
                            case FAILED:
                                progressBar.setVisibility(View.GONE);
                                break;
                        }
                    }
                });
            }
        }
    }

    private void initializePopulate(TvShowModels entity) {
        Glide.with(getApplicationContext())
                .load(IMAGE + entity.getBackdrop_path())
                .error(R.drawable.ic_broken_image_black)
                .into(ivPoster);
        title.setText(entity.getName());
        releaseDate.setText(entity.getFirst_air_date());
        overview.setText(entity.getOverview());
    }

    @NonNull
    private static DetailTvViewModels obtainViewModel(AppCompatActivity activity) {

        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        return ViewModelProviders.of(activity, factory).get(DetailTvViewModels.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favorite_action, menu);
        this.menu = menu;
        viewModels.getTvShow().observe(this, itemFavorite -> {
            if (itemFavorite != null) {
                switch (itemFavorite.status) {
                    case LOADING:
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    case FAILED:
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(this, R.string.some_mistake, Toast.LENGTH_SHORT).show();
                        break;
                    case SUCCESS:
                        if (itemFavorite.data != null) {
                            progressBar.setVisibility(View.GONE);
                            boolean stateFav = itemFavorite.data.isFavorite();
                            setTvShowFavState(stateFav);
                        }
                        break;
                }
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.favorite_act) {
            viewModels.setFavorite();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setTvShowFavState(boolean state) {
        if (menu == null) return;
        MenuItem menuItem = menu.findItem(R.id.favorite_act);
        if (state) {
            menuItem.setIcon(getResources().getDrawable(R.drawable.ic_favorite_red));
            Toast.makeText(this, R.string.fav, Toast.LENGTH_SHORT).show();
        } else {
            menuItem.setIcon(getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp));
            Toast.makeText(this, R.string.no_fav, Toast.LENGTH_SHORT).show();
        }
    }
}