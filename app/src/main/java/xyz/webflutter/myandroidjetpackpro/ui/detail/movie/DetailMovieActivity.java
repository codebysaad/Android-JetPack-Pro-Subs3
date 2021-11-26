package xyz.webflutter.myandroidjetpackpro.ui.detail.movie;

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
import xyz.webflutter.myandroidjetpackpro.adapter.MovieAdapter;
import xyz.webflutter.myandroidjetpackpro.ui.movie.MovieModels;
import xyz.webflutter.myandroidjetpackpro.utils.ViewModelFactory;

import static xyz.webflutter.myandroidjetpackpro.BuildConfig.IMAGE;

public class DetailMovieActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "EXTRA_ID";
    private TextView title, releaseDate, overview;
    private ImageView ivPoster;
    private List<MovieModels> models;
    private ProgressBar progressBar;
    private DetailMovieViewModels viewModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        Toolbar toolbar = findViewById(R.id.toolbar_movie);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        title = findViewById(R.id.title_movie_detail);
        releaseDate = findViewById(R.id.release_date);
        overview = findViewById(R.id.overview_movie_detail);
        ivPoster = findViewById(R.id.image_poster);
        progressBar = findViewById(R.id.progress_detail);
        viewModels = obtainViewModel(this);
        MovieAdapter adapter = new MovieAdapter(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String id = bundle.getString(EXTRA_ID);
            if (id != null) {
                viewModels.setMovieId(id);
                adapter.setMovies(models);
                adapter.notifyDataSetChanged();
                viewModels.getMovie().observe(this, movieDetails -> {
                    if (movieDetails != null) {
                        switch (movieDetails.status) {
                            case LOADING:
                                progressBar.setVisibility(View.VISIBLE);
                                break;
                            case SUCCESS:
                                progressBar.setVisibility(View.GONE);
                                MovieModels movieModels = movieDetails.data;
                                assert movieModels != null;
                                initializePopulate(movieModels);
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

    private void initializePopulate(MovieModels entity) {
        Glide.with(getApplicationContext())
                .load(IMAGE + entity.getPoster_path())
                .error(R.drawable.ic_broken_image_black)
                .into(ivPoster);
        title.setText(entity.getTitle());
        releaseDate.setText(entity.getRelease_date());
        overview.setText(entity.getOverview());
    }

    @NonNull
    private static DetailMovieViewModels obtainViewModel(AppCompatActivity activity) {

        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        return ViewModelProviders.of(activity, factory).get(DetailMovieViewModels.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favorite_action, menu);
        viewModels.getMovie().observe(this, itemFavorite -> {
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
                            setMovieFavState(menu, stateFav);
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

    private void setMovieFavState(Menu menu, boolean state) {
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
