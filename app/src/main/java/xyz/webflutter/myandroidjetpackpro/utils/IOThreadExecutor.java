package xyz.webflutter.myandroidjetpackpro.utils;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class IOThreadExecutor implements Executor{
    private Executor disk;

    IOThreadExecutor() {
        disk = Executors.newSingleThreadExecutor();
    }

    @Override
    public void execute(@NonNull Runnable runnable) {
        disk.execute(runnable);
    }
}
