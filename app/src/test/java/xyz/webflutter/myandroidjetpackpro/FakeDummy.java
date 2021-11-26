package xyz.webflutter.myandroidjetpackpro;

import java.util.ArrayList;

import xyz.webflutter.myandroidjetpackpro.data.remote.entity.MovieEntity;
import xyz.webflutter.myandroidjetpackpro.data.remote.entity.TvShowEntity;
import xyz.webflutter.myandroidjetpackpro.ui.movie.MovieModels;
import xyz.webflutter.myandroidjetpackpro.ui.tvshow.TvShowModels;

public class FakeDummy {
    public static ArrayList<MovieEntity> generateRemoteDummyMovie(){
        ArrayList<MovieEntity> movieResponses = new ArrayList<>();

        movieResponses.add(new MovieEntity(
                "475557", "Joker", "2019-10-02", "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.", "/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg"
        ));

        return movieResponses;

    }

    public static ArrayList<TvShowEntity> generateRemoteDummyTvShow(){
        ArrayList<TvShowEntity> tvShowResponses = new ArrayList<>();

        tvShowResponses.add(new TvShowEntity(
                "60625","Rick and Morty","Rick is a mentally-unbalanced but scientifically-gifted old man who has recently reconnected with his family. He spends most of his time involving his young grandson Morty in dangerous, outlandish adventures throughout space and alternate universes. Compounded with Morty's already unstable family life, these events cause Morty much distress at home and school.","2013-12-02","/mzzHr6g1yvZ05Mc7hNj3tUdy2bM.jpg"
        ));

        return tvShowResponses;
    }

    public static ArrayList<MovieModels> generateDummyMovie(){
        ArrayList<MovieModels> movieEntities = new ArrayList<>();

        movieEntities.add(new MovieModels(
                "475557", "Joker", "2019-10-02", "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.", "/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg", false
        ));

        return movieEntities;

    }

    public static ArrayList<TvShowModels> generateDummyTvShow(){
        ArrayList<TvShowModels> tvShowEntities = new ArrayList<>();

        tvShowEntities.add(new TvShowModels(
                "60625","Rick and Morty","Rick is a mentally-unbalanced but scientifically-gifted old man who has recently reconnected with his family. He spends most of his time involving his young grandson Morty in dangerous, outlandish adventures throughout space and alternate universes. Compounded with Morty's already unstable family life, these events cause Morty much distress at home and school.","2013-12-02","/mzzHr6g1yvZ05Mc7hNj3tUdy2bM.jpg", false
        ));

        return tvShowEntities;
    }
}
