package com.devmaster.manager.NETWORK;

import com.devmaster.manager.MODELS.USUARIOS;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIUsuarios {

    @GET("usuarios/{USUARIO}")
    Call<List<USUARIOS>> getUsuariosById(@Path("USUARIO") String USUARIO);

    @FormUrlEncoded
    @POST("login")
    Call<Respuesta> loginusuario(
            @Field("USUARIO") String usuario,
            @Field("PASSWORD") String password
    );
}
