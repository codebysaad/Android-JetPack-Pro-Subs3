package xyz.webflutter.myandroidjetpackpro.data.remote.entity;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class MovieEntity {

    @SerializedName("id")
    private String id;
    @SerializedName("poster_path")
    private String poster_path;
    @SerializedName("title")
    private String title;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String release_date;

    public MovieEntity() {
    }

    public MovieEntity(@NonNull String id, String poster_path, String title, String overview, String release_date) {
        this.id = id;
        this.poster_path = poster_path;
        this.title = title;
        this.overview = overview;
        this.release_date = release_date;
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
}
