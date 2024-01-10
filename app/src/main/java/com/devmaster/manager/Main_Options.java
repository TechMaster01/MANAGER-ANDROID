package com.devmaster.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Main_Options extends AppCompatActivity {

    private String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_options);

        Button BtnConsultOrder = findViewById(R.id.BtnConsultSalesMain);
        BtnConsultOrder.setOnClickListener(v -> OpenConsultOrder());

        Button BtnConsultProvider = findViewById(R.id.BtnConsultProviderMain);
        BtnConsultProvider.setOnClickListener(v -> OpenConsultProvider());

        Button BtnReporteProductos = findViewById(R.id.BtnProductsReportMain);
        BtnReporteProductos.setOnClickListener(v -> OpenProductReport());

        Button BtnMyAccount = findViewById(R.id.BtnMyAccountMain);
        BtnMyAccount.setOnClickListener(v -> OpenMyAccount());

        Button BtnCerrarSesion = findViewById(R.id.BtnCerrarSesion);
        BtnCerrarSesion.setOnClickListener(v -> LogOut());
    }

    private void OpenConsultOrder(){
        Intent i = new Intent(Main_Options.this, Orders_Report.class);
        startActivity(i);
    }

    private void OpenMyAccount(){
        Intent i = new Intent(Main_Options.this, My_Account.class);
        Intent Recibir = getIntent();
        usuario = Recibir.getStringExtra("ID_USUARIO");
        i.putExtra("ID_USUARIO", usuario);
        startActivity(i);
    }

    private void LogOut(){
        Intent i = new Intent(Main_Options.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void OpenProductReport(){
        Intent i = new Intent(Main_Options.this, Products_Report.class);
        startActivity(i);
    }

    private void OpenConsultProvider(){
        Intent i = new Intent(Main_Options.this, Consult_Provider.class);
        startActivity(i);
    }
}