package com.badlogic.gdx.utils.async;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * TeaVM stub: desktop {@code AsyncExecutor} uses {@link java.util.concurrent.ThreadFactory}.
 */
public class AsyncExecutor implements Disposable {

    public AsyncExecutor(int maxConcurrent) {
        this(maxConcurrent, "AsyncExecutor-Thread");
    }

    public AsyncExecutor(int maxConcurrent, String name) {
    }

    public <T> AsyncResult<T> submit(final AsyncTask<T> task) {
        FutureTask<T> future = new FutureTask<>(new Callable<T>() {
            @Override
            public T call() throws Exception {
                return task.call();
            }
        });
        future.run();
        return new AsyncResult<>(future);
    }

    @Override
    public void dispose() {
    }
}
