package xyz.webflutter.myandroidjetpackpro.data.remote.entity;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class TvShowEntity {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("first_air_date")
    private String first_air_date;
    @SerializedName("backdrop_path")
    private String backdrop_path;
    @SerializedName("overview")
    private String overview;

    public TvShowEntity() {
    }

    public TvShowEntity(@NonNull String id, String name, String first_air_date, String backdrop_path, String overview) {
        this.id = id;
        this.name = name;
        this.first_air_date = first_air_date;
        this.backdrop_path = backdrop_path;
        this.overview = overview;
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
}
