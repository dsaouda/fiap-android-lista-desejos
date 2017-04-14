package com.github.dsaouda.wishlist.dagger;

import com.github.dsaouda.wishlist.api.MockyService;
import com.github.dsaouda.wishlist.dto.Login;
import com.github.dsaouda.wishlist.factory.MockyServiceFactory;

import dagger.Module;
import dagger.Provides;
import retrofit2.Call;


@Module
public class RetrofitModule {

    @Provides
    public MockyService providerMockyService() {
        return MockyServiceFactory.create();
    }

    @Provides
    public Call<Login> providerCallLogin(MockyService service) {
        return service.login("58b9b1740f0000b614f09d2f");
    }
}