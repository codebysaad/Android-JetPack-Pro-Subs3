package xyz.webflutter.myandroidjetpackpro.utils;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MyAppExecutor {
    private static final int THREAD_COUNT = 3;

    private Executor disk;

    private Executor network;

    private Executor mainThread;

    @VisibleForTesting
    private MyAppExecutor(Executor disk, Executor network, Executor mainThread) {
        this.disk = disk;
        this.network = network;
        this.mainThread = mainThread;
    }

    public MyAppExecutor() {
        this(new IOThreadExecutor(), Executors.newFixedThreadPool(THREAD_COUNT),
                new MainThreadExecutor());
    }

    public Executor disk() {
        return disk;
    }

    public Executor network() {
        return network;
    }

    public Executor mainThread() {
        return mainThread;
    }

    private static class MainThreadExecutor implements Executor {
        private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable runnable) {
            mainThreadHandler.post(runnable);
        }
    }
}
