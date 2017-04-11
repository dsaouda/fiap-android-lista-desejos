package com.github.dsaouda.listadesejos.factory;

import com.github.dsaouda.listadesejos.api.MockyService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

final public class MockyServiceFactory {

    private MockyServiceFactory() {}

    public static MockyService create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.mocky.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(MockyService.class);
    }
}
