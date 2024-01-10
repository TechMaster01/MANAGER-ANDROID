package com.devmaster.manager.NETWORK;

import com.devmaster.manager.MODELS.PRODUCTOS;
import com.devmaster.manager.MODELS.PROVEEDORES;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIProveedores {

    @GET("proveedores")
    Call<List<PROVEEDORES>> getProveedores();

    @GET("proveedores/{ID_PROVEEDOR}")
    Call<List<PROVEEDORES>> getProveedorById(@Path("ID_PROVEEDOR") String ID_PROVEEDOR);

    @FormUrlEncoded
    @POST("proveedores")
    Call<Respuesta> RegistrarProveedor(
            @Field("ID_PROVEEDOR") String ID_PROVEEDOR,
            @Field("NOMBRE_PROVEEDOR") String NOMBRE_PROVEEDOR,
            @Field("DESCRIPCION") String DESCRIPCION
    );

    @DELETE("proveedores/{ID_PROVEEDOR}")
    Call<Respuesta> BorrarProveedor(@Path("ID_PROVEEDOR") String ID_PROVEEDOR);
}
