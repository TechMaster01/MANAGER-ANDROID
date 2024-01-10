package com.devmaster.manager.MODELS;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PROVEEDORES implements Serializable {

    @SerializedName("ID_PROVEEDOR")
    private String ID_PROVEEDOR;

    @SerializedName("NOMBRE_PROVEEDOR")
    private String NOMBRE_PROVEEDOR;

    @SerializedName("DESCRIPCION")
    private String DESCRIPCION;

    public String getID_PROVEEDOR() {
        return ID_PROVEEDOR;
    }

    public void setID_PROVEEDOR(String ID_PROVEEDOR) {
        this.ID_PROVEEDOR = ID_PROVEEDOR;
    }

    public String getNOMBRE_PROVEEDOR() {
        return NOMBRE_PROVEEDOR;
    }

    public void setNOMBRE_PROVEEDOR(String NOMBRE_PROVEEDOR) {
        this.NOMBRE_PROVEEDOR = NOMBRE_PROVEEDOR;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }
}
