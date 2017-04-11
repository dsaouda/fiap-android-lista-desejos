package com.github.dsaouda.listadesejos.api;


import com.github.dsaouda.listadesejos.dto.Login;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MockyService {

    @GET("v2/{code}")
    Call<Login> login(@Path("code") String code);

}
