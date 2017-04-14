package com.github.dsaouda.listadesejos.dagger;

import com.github.dsaouda.listadesejos.MainActivity;
import com.github.dsaouda.listadesejos.SplashScreenActivity;

import dagger.Component;

@Component(modules = {RetrofitModule.class, GreenDaoModule.class})
public interface DefaultComponent {
    void inject(MainActivity app);
    void inject(SplashScreenActivity splashScreenActivity);
}