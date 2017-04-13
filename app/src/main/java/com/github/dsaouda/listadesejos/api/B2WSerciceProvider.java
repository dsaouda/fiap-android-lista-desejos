package com.github.dsaouda.listadesejos.api;

import com.github.dsaouda.listadesejos.dto.b2w.B2WJson;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface B2WSerciceProvider {

    //itemId:({id})&limit=1&paymentOptionIds=BOLETO
    @GET("product")
    Call<B2WJson> getProduto(@Query("q") String query, @Query("limit") int limit, @Query("paymentOptionIds") String payment);
}
