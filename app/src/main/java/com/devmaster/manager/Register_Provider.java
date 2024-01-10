package com.devmaster.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.devmaster.manager.NETWORK.APIClient;
import com.devmaster.manager.NETWORK.APIProductos;
import com.devmaster.manager.NETWORK.APIProveedores;
import com.devmaster.manager.NETWORK.Respuesta;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_Provider extends AppCompatActivity {
    private EditText ID_PROVEEDOR;
    private EditText NOMBRE_PROVEEDOR;
    private EditText DESCRIPCION_PROVEEDOR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_provider);

        ID_PROVEEDOR = findViewById(R.id.EdtIdProveedorRegister);
        NOMBRE_PROVEEDOR = findViewById(R.id.EdtNombreProveedorRegister);
        DESCRIPCION_PROVEEDOR = findViewById(R.id.EdtDescripcionProveedorRegister);

        Button BtnRegistrarProveedor = findViewById(R.id.BtnRegistrarProveedor);
        BtnRegistrarProveedor.setOnClickListener(v -> Registrar());
    }

    private void Registrar(){
        String IdProveedor = ID_PROVEEDOR.getText().toString();
        String NombreProveedor = NOMBRE_PROVEEDOR.getText().toString();
        String DescripcionProveedor = DESCRIPCION_PROVEEDOR.getText().toString();
        
        if(IdProveedor.isEmpty() || NombreProveedor.isEmpty() || DescripcionProveedor.isEmpty()){
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        APIProveedores apiRegisterProvider = APIClient.getClient().create(APIProveedores.class);

        Call<Respuesta> call =apiRegisterProvider.RegistrarProveedor(IdProveedor, NombreProveedor, DescripcionProveedor);
        
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                if(response.isSuccessful()){
                    Toast.makeText(Register_Provider.this, "Proveedor registrado con exito", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Register_Provider.this, "El proveedor ya existe", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Toast.makeText(Register_Provider.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}