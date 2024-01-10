package com.devmaster.manager.NETWORK;

import com.devmaster.manager.MODELS.ORDENES;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIOrdenes {

    @GET("ordenes/{ID_ORDEN}")
    Call<List<ORDENES>> getOrdenesById(@Path("ID_ORDEN") String ID_ORDEN);
}
