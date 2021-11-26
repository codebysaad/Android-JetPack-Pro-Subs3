package xyz.webflutter.myandroidjetpackpro.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import xyz.webflutter.myandroidjetpackpro.ui.movie.MovieModels;
import xyz.webflutter.myandroidjetpackpro.ui.tvshow.TvShowModels;

@Database(entities = {MovieModels.class, TvShowModels.class},
        version = 3,
        exportSchema = false)
public abstract class DatabaseLocal extends RoomDatabase {

    private static DatabaseLocal INSTANCE;

    public abstract Dao dao();

    private static final Object sLock = new Object();

    public static DatabaseLocal getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        DatabaseLocal.class, "dbAndroid.db")
                        .build();
            }
            return INSTANCE;
        }
    }
}