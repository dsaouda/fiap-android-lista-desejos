package com.github.dsaouda.wishlist.api;


import com.github.dsaouda.wishlist.dto.Login;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MockyService {

    @GET("v2/{code}")
    Call<Login> login(@Path("code") String code);

}
