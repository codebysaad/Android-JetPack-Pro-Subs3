package xyz.webflutter.myandroidjetpackpro.ui.tvshow;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import xyz.webflutter.myandroidjetpackpro.data.remote.entity.TvShowEntity;

@Entity(tableName = "tvshow")
public class TvShowModels {

    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "id")
    private String id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "first_air_date")
    private String first_air_date;
    @ColumnInfo(name = "backdrop_path")
    private String backdrop_path;
    @ColumnInfo(name = "overview")
    private String overview;
    @ColumnInfo(name = "favorite")
    private boolean favorite = false;

    public TvShowModels() {
    }

    public TvShowModels(TvShowEntity tvShowEntity){
        id = tvShowEntity.getId();
        first_air_date = tvShowEntity.getFirst_air_date();
        name = tvShowEntity.getName();
        backdrop_path = tvShowEntity.getBackdrop_path();
        overview = tvShowEntity.getOverview();
        favorite = false;
    }

    public TvShowModels(@NonNull String id, String name, String overview, String first_air_date, String backdrop_path, Boolean isFavorited){
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.first_air_date = first_air_date;
        this.backdrop_path = backdrop_path;
        if (isFavorited!=null){
            this.favorite = isFavorited;
        }
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
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
