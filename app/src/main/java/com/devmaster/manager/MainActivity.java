package com.devmaster.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.devmaster.manager.NETWORK.APIClient;
import com.devmaster.manager.NETWORK.APIUsuarios;
import com.devmaster.manager.NETWORK.Respuesta;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText EdtUsuario;
    private EditText EdtPassword;
    private TextView ResetPass;

    private Switch SwchMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EdtUsuario = findViewById(R.id.EdtTextUser);
        EdtPassword = findViewById(R.id.EdtTextPass);
        SwchMostrar = findViewById(R.id.SwitchMostrar);
        ResetPass = findViewById(R.id.TxtResetPassword);

        Button BtnLogin = findViewById(R.id.BtnLogin);
        BtnLogin.setOnClickListener(view -> Login());
        ResetPass.setOnClickListener(v -> ResetPassword());

        SwchMostrar.setOnCheckedChangeListener((buttonView, isChecked) -> {
            MostrarContrasena(isChecked);
        });
    }
    
    private void ResetPassword(){
        Intent i = new Intent(MainActivity.this, Reset_Password_Form.class);
        startActivity(i);
    }

    private void Login(){
        String Usuario = EdtUsuario.getText().toString().trim();
        String Password = EdtPassword.getText().toString().trim();

        if(Usuario.isEmpty() || Password.isEmpty()){
            showToast("Complete todos los campos.");
            return;
        }

        APIUsuarios ApiService = APIClient.getClient().create(APIUsuarios.class);

        Call<Respuesta> call = ApiService.loginusuario(Usuario, Password);

        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                if (response.isSuccessful()) {
                    Respuesta apiResponse = response.body();
                    if ("Conexión exitosa.".equals(apiResponse.getMessage())) {
                        showToast("Conexión exitosa.");
                    } else {
                        showToast(apiResponse.getMessage());
                    }
                } else {
                    showToast("Usuario o contraseña invalidos.");
                }
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                showToast("Error de conexión.");
            }
        });

    }

    private void showToast(final String Texto) {
        runOnUiThread(() -> {
            if (Texto != null) {
                Toast.makeText(MainActivity.this, Texto, Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(MainActivity.this, Main_Options.class);
                intent.putExtra("ID_USUARIO", EdtUsuario.getText().toString());
                startActivity(intent);
                finish();
            }
        });
    }

    private void MostrarContrasena(Boolean Mostrar){
        if(Mostrar){
            EdtPassword.setTransformationMethod(null);
        }else{
            EdtPassword.setTransformationMethod(new PasswordTransformationMethod());
        }
    }
}