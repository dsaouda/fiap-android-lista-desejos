package com.github.dsaouda.wishlist.callback;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.github.dsaouda.wishlist.R;
import com.github.dsaouda.wishlist.dto.Login;
import com.github.dsaouda.wishlist.manager.LoginManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginCallback implements Callback<Login> {
    private final LoginManager manager;
    private final Runnable callback;
    private final Context context;

    public LoginCallback(LoginManager manager, Runnable callback, Context context) {
        this.manager = manager;
        this.callback = callback;
        this.context = context;
    }

    @Override
    public void onResponse(Call<Login> call, Response<Login> response) {
        if (response.isSuccessful()) {
            final com.github.dsaouda.wishlist.model.Login login = response.body().getModel();
            manager.save(login);

            callback.run();
        } else {
            toastError(context.getString(R.string.url_mocky_error));
        }
    }

    @Override
    public void onFailure(Call<Login> call, Throwable t) {
        String error = context.getString(R.string.error_callback_login);

        if (t instanceof java.net.UnknownHostException) {
            error = context.getString(R.string.no_internet);
        }

        toastError(error);
    }

    private void toastError(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
