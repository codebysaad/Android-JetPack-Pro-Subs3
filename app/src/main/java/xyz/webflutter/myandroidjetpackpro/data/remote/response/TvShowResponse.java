package xyz.webflutter.myandroidjetpackpro.data.remote.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import xyz.webflutter.myandroidjetpackpro.data.remote.entity.TvShowEntity;

public class TvShowResponse {

    @SerializedName("results")
    private final ArrayList<TvShowEntity> entities;
    public TvShowResponse(ArrayList<TvShowEntity> entities){
        this.entities = entities;
    }
    public ArrayList<TvShowEntity> getTvShowEntities(){
        return entities;
    }
}
