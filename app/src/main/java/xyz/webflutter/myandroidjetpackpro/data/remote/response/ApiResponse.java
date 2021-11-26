package xyz.webflutter.myandroidjetpackpro.data.remote.response;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ApiResponse<T> {
    @NonNull
    public final StatusResponse statusResponse;

    @Nullable
    public final String message;

    @Nullable
    public final T body;

    private ApiResponse(@NonNull StatusResponse statusResponse, @Nullable T body, @Nullable String message) {
        this.statusResponse = statusResponse;
        this.body = body;
        this.message = message;
    }

    public static <T> ApiResponse<T> success(@Nullable T body) {
        return new ApiResponse<>(StatusResponse.SUCCES, body, null);
    }

    public static <T> ApiResponse<T> empty(String msg, @Nullable T body) {
        return new ApiResponse<>(StatusResponse.EMPTY, body, msg);
    }

    public static <T> ApiResponse<T> failed(String msg, @Nullable T body) {
        return new ApiResponse<>(StatusResponse.FAILED, body, msg);
    }
}
