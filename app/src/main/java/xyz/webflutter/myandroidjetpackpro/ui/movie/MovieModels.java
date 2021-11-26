package xyz.webflutter.myandroidjetpackpro.ui.movie;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import xyz.webflutter.myandroidjetpackpro.data.remote.entity.MovieEntity;

@Entity(tableName = "movie")
public class MovieModels{

    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "id")
    private String id;
    @ColumnInfo(name = "poster_path")
    private String poster_path;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "overview")
    private String overview;
    @ColumnInfo(name = "release_date")
    private String release_date;
    @ColumnInfo(name = "favorite")
    private boolean favorite = false;

    public MovieModels() {
    }

    public MovieModels(@NonNull String id, String title, String release_date, String overview, String poster_path, Boolean isFavorited) {
        this.id = id;
        this.title = title;
        this.release_date = release_date;
        this.overview = overview;
        this.poster_path = poster_path;
        if (isFavorited!=null){
            this.favorite = isFavorited;
        }
    }

    public MovieModels(MovieEntity movieEntity){
        id = movieEntity.getId();
        poster_path = movieEntity.getPoster_path();
        title = movieEntity.getTitle();
        release_date = movieEntity.getRelease_date();
        overview = movieEntity.getOverview();
        favorite = false;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void favorited(){
        favorite = !isFavorite();
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}