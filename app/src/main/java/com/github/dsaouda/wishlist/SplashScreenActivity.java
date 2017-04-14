package com.github.dsaouda.wishlist;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.github.dsaouda.wishlist.callback.LoginCallback;
import com.github.dsaouda.wishlist.dto.Login;
import com.github.dsaouda.wishlist.manager.LoginManager;

import javax.inject.Inject;

import retrofit2.Call;

public class SplashScreenActivity extends AppCompatActivity {
    private static final int SPLASH_DISPLAY_LENGTH = 3000;

    @Inject
    Call<Login> loginService;

    @Inject
    LoginManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        App.getComponent().inject(this);

        Animation anim = AnimationUtils.loadAnimation(this,
                R.anim.animacao_splash_screen);
        anim.reset();

        LinearLayout iv = (LinearLayout) findViewById(R.id.activity_splash_screen);
        iv.clearAnimation();
        iv.startAnimation(anim);


        final com.github.dsaouda.wishlist.model.Login login = manager.getRepo().defaultLogin();

        if (login == null) {
            loginService.enqueue(getLoginCallback());
        } else {
            SplashScreenActivity.this.handler(login.isManterConectado()
                    ? MainActivity.class
                    : LoginActivity.class).run();
        }
    }

    @NonNull
    private LoginCallback getLoginCallback() {
        return new LoginCallback(manager, this.handler(LoginActivity.class), this);
    }

    private Runnable handler(final Class<?> cls) {
        return new Runnable() {
            @Override
            public void run() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashScreenActivity.this, cls);
                        startActivity(intent);
                        SplashScreenActivity.this.finish();

                    }
                }, SPLASH_DISPLAY_LENGTH);
            }
        };
    }
}