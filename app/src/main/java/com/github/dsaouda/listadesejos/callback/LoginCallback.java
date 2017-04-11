package com.github.dsaouda.listadesejos.callback;

import com.github.dsaouda.listadesejos.dto.Login;
import com.github.dsaouda.listadesejos.manager.LoginManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginCallback implements Callback<Login> {
    private final LoginManager manager;
    private final Runnable callback;

    public LoginCallback(LoginManager manager, Runnable callback) {
        this.manager = manager;
        this.callback = callback;
    }

    @Override
    public void onResponse(Call<Login> call, Response<Login> response) {
        final com.github.dsaouda.listadesejos.model.Login login = response.body().getModel();
        manager.save(login);

        callback.run();
    }

    @Override
    public void onFailure(Call<Login> call, Throwable t) {

    }
}
