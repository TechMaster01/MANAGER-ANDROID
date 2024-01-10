package com.devmaster.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.devmaster.manager.MODELS.PRODUCTOS;
import com.devmaster.manager.MODELS.PROVEEDORES;
import com.devmaster.manager.NETWORK.APIClient;
import com.devmaster.manager.NETWORK.APIProductos;
import com.devmaster.manager.NETWORK.APIProveedores;
import com.devmaster.manager.NETWORK.Respuesta;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Products_Report extends AppCompatActivity {
    public static APIProductos apiProductos;
    public static APIProveedores apiProveedores;

    private EditText EdtBarcode;
    private TextView Nombre_Producto;
    private TextView Precio_Producto;
    private TextView Descripcion_Producto;
    private TextView Stock_Producto;
    private TextView Nombre_Proveedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_report);

        apiProductos = APIClient.getClient().create(APIProductos.class);
        apiProveedores = APIClient.getClient().create(APIProveedores.class);

        Button BuscarProducto = findViewById(R.id.BtnBuscar);
        Button EliminarProducto = findViewById(R.id.BtnEliminar);
        Button ScanBarcode = findViewById(R.id.BtnScan);
        Button RegistrarProducto = findViewById(R.id.BtnRegisterProduct);


        Nombre_Producto = findViewById(R.id.TxtProducto);
        Descripcion_Producto = findViewById(R.id.TxtDescripcion);
        Precio_Producto = findViewById(R.id.TxtPrecio);
        Stock_Producto = findViewById(R.id.TxtStock);
        Nombre_Proveedor = findViewById(R.id.TxtProveedor);
        EdtBarcode = findViewById(R.id.EdtCodigo);

        RegistrarProducto.setOnClickListener(v -> OpenRegisterProduct());
        BuscarProducto.setOnClickListener(v -> ObtenerDatosProducto(EdtBarcode.getText().toString()));
        EliminarProducto.setOnClickListener(v -> EliminarProductos(EdtBarcode.getText().toString()));

        ScanBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator Scanner = new IntentIntegrator(Products_Report.this);
                Scanner.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                Scanner.setPrompt("Escanea el CDB");
                Scanner.setCameraId(0);
                Scanner.setBarcodeImageEnabled(true);
                Scanner.initiateScan();
            }
        });
    }

    private void OpenRegisterProduct(){
        Intent i = new Intent(Products_Report.this, Register_Product.class);
        startActivity(i);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null){
            if (result.getContents() == null){
                Toast.makeText(this, "Lectura Cancelada", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, "Exito al escanear", Toast.LENGTH_SHORT).show();
                EdtBarcode.setText(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void EliminarProductos(String CODIGOBARRAS){

        if(CODIGOBARRAS.isEmpty()){
            Toast.makeText(this, "Sin información", Toast.LENGTH_SHORT).show();
            return;
        }

        APIProductos ApiService = APIClient.getClient().create(APIProductos.class);

        Call<Respuesta> call = ApiService.BorrarProductos(CODIGOBARRAS);
        
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                if(response.isSuccessful()){
                    Toast.makeText(Products_Report.this, "Producto Eliminado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Products_Report.this, "El producto no existe", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Toast.makeText(Products_Report.this, "Error de conexion", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void ObtenerDatosProducto(String ID_PRODUCTO){
        Call<List<PRODUCTOS>> ObtenerProducto = apiProductos.getProductosById(ID_PRODUCTO);
        Log.d("ID Producto", ID_PRODUCTO);
        ObtenerProducto.enqueue(new Callback<List<PRODUCTOS>>() {
            @Override
            public void onResponse(Call<List<PRODUCTOS>> call, Response<List<PRODUCTOS>> response) {
                Log.d("RespuestaServidor", "Json: " + response);
                if(response.body() != null){
                    PRODUCTOS Producto = response.body().get(0);
                    Nombre_Producto.setText(Producto.getNOMBRE_PRODUCTO() != null ? "NOMBRE: " + Producto.getNOMBRE_PRODUCTO() : "N/A");
                    Descripcion_Producto.setText(Producto.getDESCRIPCION() != null ? "DESCRIPCIÓN: " + Producto.getDESCRIPCION() : "N/A");
                    Precio_Producto.setText(Producto.getPRECIO() != null ? "PRECIO: $" + Producto.getPRECIO() : "N/A");
                    Stock_Producto.setText(Producto.getSTOCK() != null ? "STOCK: " + Producto.getSTOCK() : "N/A");
                    ObtenerNombreProveedor(Producto.getID_PROVEEDOR().toString());
                }else{
                    Toast.makeText(Products_Report.this, "El producto no existe", Toast.LENGTH_SHORT).show();
                    Nombre_Producto.setText("NOMBRE: N/A");
                    Descripcion_Producto.setText("DESCRIPCIÓN: N/A");
                    Precio_Producto.setText("PRECIO: $N/A");
                    Stock_Producto.setText("STOCK: N/A");
                    Nombre_Proveedor.setText("PROVEEDOR: N/A");
                }
            }

            @Override
            public void onFailure(Call<List<PRODUCTOS>> call, Throwable t) {
                Log.e("RespuestaServidor", "Error de conexion: " + t.getMessage());
                Toast.makeText(Products_Report.this, "Respuesta no exitosa", Toast.LENGTH_SHORT).show();
                Nombre_Producto.setText("NOMBRE: N/A");
                Descripcion_Producto.setText("DESCRIPCIÓN: N/A");
                Precio_Producto.setText("PRECIO: $N/A");
                Stock_Producto.setText("STOCK: N/A");
                Nombre_Proveedor.setText("PROVEEDOR: N/A");
            }
        });
    }

    private void ObtenerNombreProveedor(String ID_PROVEEDOR){
        Call<List<PROVEEDORES>> ObtenerNombre = apiProveedores.getProveedorById(ID_PROVEEDOR);
        ObtenerNombre.enqueue(new Callback<List<PROVEEDORES>>() {
            @Override
            public void onResponse(Call<List<PROVEEDORES>> call, Response<List<PROVEEDORES>> response) {
                if(response.body() != null){
                    PROVEEDORES proveedores = response.body().get(0);
                    Nombre_Proveedor.setText("PROVEEDOR: " + proveedores.getNOMBRE_PROVEEDOR());
                }else{
                    Toast.makeText(Products_Report.this, "El proveedor no existe", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PROVEEDORES>> call, Throwable t) {
                Toast.makeText(Products_Report.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}