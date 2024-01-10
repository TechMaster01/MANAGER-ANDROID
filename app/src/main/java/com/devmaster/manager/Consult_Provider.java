package com.devmaster.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.devmaster.manager.MODELS.PROVEEDORES;
import com.devmaster.manager.NETWORK.APIClient;
import com.devmaster.manager.NETWORK.APIProductos;
import com.devmaster.manager.NETWORK.APIProveedores;
import com.devmaster.manager.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Consult_Provider extends AppCompatActivity {

    public static APIProveedores apiProveedores;

    private EditText EdtIdProveedor;

    private TextView ID_PROVEEDOR;
    private TextView NOMBRE_PROVEEDOR;
    private TextView DESCRIPCION_PROVEEDOR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_provider);

        apiProveedores = APIClient.getClient().create(APIProveedores.class);

        EdtIdProveedor = findViewById(R.id.EdtIdProveedorConsult);

        ID_PROVEEDOR = findViewById(R.id.TxtIdProveedor);
        NOMBRE_PROVEEDOR = findViewById(R.id.TxtNombreProveedor);
        DESCRIPCION_PROVEEDOR = findViewById(R.id.TxtDescripcionProveedor);

        Button BtnConsult = findViewById(R.id.BtnConsultarExistenciaProveedor);
        BtnConsult.setOnClickListener(v -> BuscarProveedor(EdtIdProveedor.getText().toString()));

        Button BtnRegister = findViewById(R.id.BtnRegisterProvider);
        BtnRegister.setOnClickListener(v -> RegistrarProveedor());
    }

    private void RegistrarProveedor(){
        Intent i = new Intent(Consult_Provider.this, Register_Provider.class);
        startActivity(i);
    }

    public void BuscarProveedor(String ID){
        Call<List<PROVEEDORES>> ObtenerProveedor = apiProveedores.getProveedorById(ID);
        ObtenerProveedor.enqueue(new Callback<List<PROVEEDORES>>() {
            @Override
            public void onResponse(Call<List<PROVEEDORES>> call, Response<List<PROVEEDORES>> response) {
                if(response.body() != null){
                    PROVEEDORES proveedores = response.body().get(0);
                    ID_PROVEEDOR.setText("ID DEL PROVEEDOR: " + proveedores.getID_PROVEEDOR());
                    NOMBRE_PROVEEDOR.setText("NOMBRE DEL PROVEEDOR: \n" + proveedores.getNOMBRE_PROVEEDOR());
                    DESCRIPCION_PROVEEDOR.setText("DESCRIPCIÓN: \n" + proveedores.getDESCRIPCION());
                }else{
                    Toast.makeText(Consult_Provider.this, "El proveedor no existe", Toast.LENGTH_SHORT).show();
                    ID_PROVEEDOR.setText("ID DEL PROVEEDOR: " + "N/A");
                    NOMBRE_PROVEEDOR.setText("NOMBRE DEL PROVEEDOR: " + "N/A");
                    DESCRIPCION_PROVEEDOR.setText("DESCRIPCIÓN: " + "N/A");
                }
            }

            @Override
            public void onFailure(Call<List<PROVEEDORES>> call, Throwable t) {
                Toast.makeText(Consult_Provider.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                ID_PROVEEDOR.setText("ID DEL PROVEEDOR: " + "N/A");
                NOMBRE_PROVEEDOR.setText("NOMBRE DEL PROVEEDOR: " + "N/A");
                DESCRIPCION_PROVEEDOR.setText("DESCRIPCIÓN: " + "N/A");
            }
        });

    }
}