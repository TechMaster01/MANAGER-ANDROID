package com.devmaster.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.devmaster.manager.MODELS.ORDENES;
import com.devmaster.manager.MODELS.PRODUCTOS;
import com.devmaster.manager.MODELS.USUARIOS;
import com.devmaster.manager.NETWORK.APIClient;
import com.devmaster.manager.NETWORK.APIOrdenes;
import com.devmaster.manager.NETWORK.APIUsuarios;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Orders_Report extends AppCompatActivity {

    public static APIOrdenes apiOrdenes;
    public static APIUsuarios apiUsuarios;

    private EditText EdtIdVenta;

    private TextView ID_VENTA;
    private TextView NOMBRE_USUARIO;
    private TextView FECHA_ORDEN;
    private TextView TOTAL_ORDEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_report);

        apiOrdenes = APIClient.getClient().create(APIOrdenes.class);
        apiUsuarios = APIClient.getClient().create(APIUsuarios.class);

        ID_VENTA = findViewById(R.id.TxtIdOrden);
        NOMBRE_USUARIO = findViewById(R.id.TxtUsuarioOrden);
        FECHA_ORDEN = findViewById(R.id.TxtFechaOrden);
        TOTAL_ORDEN = findViewById(R.id.TxtTotalOrden);
        EdtIdVenta = findViewById(R.id.EdtIdOrder);

        Button BtnConsultar = findViewById(R.id.BtnConsultarVenta);
        BtnConsultar.setOnClickListener(v -> ObtenerDatosVenta(EdtIdVenta.getText().toString()));
    }

    private void ObtenerDatosVenta(String ID){
        Call<List<ORDENES>> ObtenerOrden = apiOrdenes.getOrdenesById(ID);
        ObtenerOrden.enqueue(new Callback<List<ORDENES>>() {
            @Override
            public void onResponse(Call<List<ORDENES>> call, Response<List<ORDENES>> response) {
                if(response.body() != null){
                    ORDENES ordenes = response.body().get(0);
                    ID_VENTA.setText("ORDEN: " + ordenes.getID_ORDEN());
                    ObtenerUsuario(ordenes.getUSUARIO().toString());
                    FECHA_ORDEN.setText("FECHA: " + ordenes.getFECHA_ORDEN());
                    TOTAL_ORDEN.setText("TOTAL: $" + ordenes.getTOTAL());
                }else{
                    Toast.makeText(Orders_Report.this, "La orden no existe.", Toast.LENGTH_SHORT).show();
                    ID_VENTA.setText("ORDEN: N/A");
                    NOMBRE_USUARIO.setText("USUARIO: N/A");
                    FECHA_ORDEN.setText("FECHA: N/A");
                    TOTAL_ORDEN.setText("TOTAL: $N/A");
                }
            }

            @Override
            public void onFailure(Call<List<ORDENES>> call, Throwable t) {
                Toast.makeText(Orders_Report.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                ID_VENTA.setText("ORDEN: N/A");
                NOMBRE_USUARIO.setText("USUARIO: N/A");
                FECHA_ORDEN.setText("FECHA: N/A");
                TOTAL_ORDEN.setText("TOTAL: $N/A");
            }
        });
    }
    
    private void ObtenerUsuario(String ID){
        Call<List<USUARIOS>> ObtenerUser = apiUsuarios.getUsuariosById(ID);
        ObtenerUser.enqueue(new Callback<List<USUARIOS>>() {
            @Override
            public void onResponse(Call<List<USUARIOS>> call, Response<List<USUARIOS>> response) {
                if(response.body() != null){
                    USUARIOS usuarios = response.body().get(0);
                    NOMBRE_USUARIO.setText("USUARIO: " + usuarios.getNOMBRE_USUARIO());
                }else{
                    Toast.makeText(Orders_Report.this, "El usuario no existe.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<USUARIOS>> call, Throwable t) {
                Toast.makeText(Orders_Report.this, "Error de conexión.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}