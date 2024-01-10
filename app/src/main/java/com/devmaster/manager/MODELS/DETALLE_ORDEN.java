package com.devmaster.manager.MODELS;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DETALLE_ORDEN implements Serializable {

    @SerializedName("ID_ORDEN")
    private String ID_ORDEN;

    @SerializedName("ID_PRODUCTO")
    private String ID_PRODUCTO;

    @SerializedName("CANTIDAD")
    private String CANTIDAD;

    public String getID_ORDEN() {
        return ID_ORDEN;
    }

    public void setID_ORDEN(String ID_ORDEN) {
        this.ID_ORDEN = ID_ORDEN;
    }

    public String getID_PRODUCTO() {
        return ID_PRODUCTO;
    }

    public void setID_PRODUCTO(String ID_PRODUCTO) {
        this.ID_PRODUCTO = ID_PRODUCTO;
    }

    public String getCANTIDAD() {
        return CANTIDAD;
    }

    public void setCANTIDAD(String CANTIDAD) {
        this.CANTIDAD = CANTIDAD;
    }
}
