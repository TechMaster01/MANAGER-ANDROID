package com.devmaster.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.devmaster.manager.MODELS.USUARIOS;
import com.devmaster.manager.NETWORK.APIClient;
import com.devmaster.manager.NETWORK.APIUsuarios;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class My_Account extends AppCompatActivity {
    
    public static APIUsuarios ApiUsuario;

    private TextView Nombre_Usuario;
    private TextView Direccion_Usuario;
    private TextView Email_Usuario;
    private TextView Celular_Usuario;
    private TextView Rol_Usuario;
    
    private String ID_USUARIO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        
        ApiUsuario = APIClient.getClient().create(APIUsuarios.class);

        Intent Recibir = getIntent();
        ID_USUARIO = Recibir.getStringExtra("ID_USUARIO");

        Nombre_Usuario = findViewById(R.id.TxtNombreUsuario);
        Direccion_Usuario = findViewById(R.id.TxtDireccionUsuario);
        Email_Usuario = findViewById(R.id.TxtEmailUsuario);
        Celular_Usuario = findViewById(R.id.TxtCelularUsuario);
        Rol_Usuario = findViewById(R.id.TxtRolUsuario);

        ObtenerDatosUsuario(ID_USUARIO);
    }

    private void ObtenerDatosUsuario(String ID_USUARIO){
        Call<List<USUARIOS>> ObtenerDatos = ApiUsuario.getUsuariosById(ID_USUARIO);
        
        ObtenerDatos.enqueue(new Callback<List<USUARIOS>>() {
            @Override
            public void onResponse(Call<List<USUARIOS>> call, Response<List<USUARIOS>> response) {
                if(response.body() != null){
                    USUARIOS Usuario = response.body().get(0);
                    Nombre_Usuario.setText(Usuario.getNOMBRE_USUARIO());
                    Direccion_Usuario.setText(Usuario.getDIRECCION());
                    Email_Usuario.setText(Usuario.getEMAIL());
                    Celular_Usuario.setText(Usuario.getCELULAR());
                    Rol_Usuario.setText(Usuario.getID_ROL());
                }else{
                    Toast.makeText(My_Account.this, "No hay datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<USUARIOS>> call, Throwable t) {
                Toast.makeText(My_Account.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}