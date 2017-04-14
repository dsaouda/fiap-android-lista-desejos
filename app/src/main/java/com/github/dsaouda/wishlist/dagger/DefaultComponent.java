package com.github.dsaouda.wishlist.dagger;

import com.github.dsaouda.wishlist.LoginActivity;
import com.github.dsaouda.wishlist.MainActivity;
import com.github.dsaouda.wishlist.ProdutoActivity;
import com.github.dsaouda.wishlist.SplashScreenActivity;

import dagger.Component;

@Component(modules = {RetrofitModule.class, GreenDaoModule.class})
public interface DefaultComponent {
    void inject(MainActivity app);
    void inject(SplashScreenActivity splashScreenActivity);
    void inject(LoginActivity loginActivity);
    void inject(ProdutoActivity produtoActivity);
}