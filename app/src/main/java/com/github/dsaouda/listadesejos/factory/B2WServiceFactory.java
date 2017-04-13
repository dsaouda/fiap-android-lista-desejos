package com.github.dsaouda.listadesejos.factory;

import com.github.dsaouda.listadesejos.api.B2WSerciceProvider;
import com.github.dsaouda.listadesejos.service.B2WService;

import java.net.URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

final public class B2WServiceFactory {

    private B2WServiceFactory() {}

    public static B2WService create(URL url) {
        return create(url.getHost().replaceAll("www.|.com.br", ""));
    }

    public static B2WService create(String dominio) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://product-v3."+dominio+".com.br")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return new B2WService(retrofit.create(B2WSerciceProvider.class));
    }
}
