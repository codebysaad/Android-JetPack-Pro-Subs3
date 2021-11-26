package xyz.webflutter.myandroidjetpackpro.data.remote.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import xyz.webflutter.myandroidjetpackpro.data.remote.entity.MovieEntity;

public class MovieResponse {

    @SerializedName("results")
    private final ArrayList<MovieEntity> entities;
    public MovieResponse(ArrayList<MovieEntity> entities){
        this.entities = entities;
    }
    public ArrayList<MovieEntity> getMovieEntities(){
        return entities;
    }
}
