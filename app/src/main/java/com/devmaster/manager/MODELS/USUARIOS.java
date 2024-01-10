package com.devmaster.manager.MODELS;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class USUARIOS implements Serializable {

    @SerializedName("USUARIO")
    private String USUARIO;

    @SerializedName("ID_ROL")
    private String ID_ROL;

    @SerializedName("NOMBRE_USUARIO")
    private String NOMBRE_USUARIO;

    @SerializedName("DIRECCION")
    private String DIRECCION;

    @SerializedName("EMAIL")
    private String EMAIL;

    @SerializedName("CELULAR")
    private String CELULAR;

    @SerializedName("PASSWORD")
    private String PASSWORD;

    public String getUSUARIO() {
        return USUARIO;
    }

    public void setUSUARIO(String USUARIO) {
        this.USUARIO = USUARIO;
    }

    public String getID_ROL() {
        return ID_ROL;
    }

    public void setID_ROL(String ID_ROL) {
        this.ID_ROL = ID_ROL;
    }

    public String getNOMBRE_USUARIO() {
        return NOMBRE_USUARIO;
    }

    public void setNOMBRE_USUARIO(String NOMBRE_USUARIO) {
        this.NOMBRE_USUARIO = NOMBRE_USUARIO;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getCELULAR() {
        return CELULAR;
    }

    public void setCELULAR(String CELULAR) {
        this.CELULAR = CELULAR;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }


}
