package xyz.webflutter.myandroidjetpackpro.di;

import android.app.Application;

import xyz.webflutter.myandroidjetpackpro.data.LocalRepository;
import xyz.webflutter.myandroidjetpackpro.data.Repository;
import xyz.webflutter.myandroidjetpackpro.data.local.DatabaseLocal;
import xyz.webflutter.myandroidjetpackpro.data.remote.RemoteRepository;
import xyz.webflutter.myandroidjetpackpro.utils.JsonHelper;
import xyz.webflutter.myandroidjetpackpro.utils.MyAppExecutor;

public class Injection {
    public static Repository repository(Application application) {

        DatabaseLocal db = DatabaseLocal.getInstance(application);

        LocalRepository localRepository = LocalRepository.getInstance(db.dao());
        RemoteRepository remoteRepository = RemoteRepository.getInstance(JsonHelper.getInstance());
        MyAppExecutor appExecutors = new MyAppExecutor();

        return Repository.getInstance(localRepository, remoteRepository, appExecutors);
    }
}