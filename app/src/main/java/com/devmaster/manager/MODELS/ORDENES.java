package com.devmaster.manager.MODELS;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ORDENES implements Serializable {

    @SerializedName("ID_ORDEN")
    private String ID_ORDEN;

    @SerializedName("USUARIO")
    private String USUARIO;

    @SerializedName("FECHA_ORDEN")
    private String FECHA_ORDEN;

    @SerializedName("TOTAL")
    private String TOTAL;

    public String getID_ORDEN() {
        return ID_ORDEN;
    }

    public void setID_ORDEN(String ID_ORDEN) {
        this.ID_ORDEN = ID_ORDEN;
    }

    public String getUSUARIO() {
        return USUARIO;
    }

    public void setUSUARIO(String USUARIO) {
        this.USUARIO = USUARIO;
    }

    public String getFECHA_ORDEN() {
        return FECHA_ORDEN;
    }

    public void setFECHA_ORDEN(String FECHA_ORDEN) {
        this.FECHA_ORDEN = FECHA_ORDEN;
    }

    public String getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(String TOTAL) {
        this.TOTAL = TOTAL;
    }
}
