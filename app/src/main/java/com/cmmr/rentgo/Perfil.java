package com.cmmr.rentgo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cmmr.rentgo.Utilitys.Producto;
import com.cmmr.rentgo.Utilitys.ProductAdapter;
import java.util.ArrayList;
import java.util.List;

public class Perfil extends AppCompatActivity {


    private TextView textViewName;
    private TextView textViewLastName;
    private TextView textViewAddress;
    private RecyclerView recyclerViewProducts;
    private ImageButton buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);


        textViewName = findViewById(R.id.textViewName);
        textViewLastName = findViewById(R.id.textViewLastName);
        textViewAddress = findViewById(R.id.textViewAddress);
        recyclerViewProducts = findViewById(R.id.recyclerViewProducts);
        buttonBack = findViewById(R.id.buttonBack);


        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, MenuPrincipal.class);
                startActivity(intent);
                finish();
            }
        });


        loadDummyData();
    }


    private void loadDummyData() {

        String name = "Pepe";
        String lastName = "Perez";
        String address = "Madrid, Villanueva de la Cañada";


        textViewName.setText(name);
        textViewLastName.setText(lastName);
        textViewAddress.setText(address);


        List<Producto> productoList = new ArrayList<>();
        productoList.add(new Producto("Sudadera 1", Uri.parse("android.resource://com.cmmr.rentgo/" + R.drawable.sudadera1), "Sudadera de color azul", 29.99, "ropa"));
        productoList.add(new Producto("Sudadera 2", Uri.parse("android.resource://com.cmmr.rentgo/" + R.drawable.sudadera2), "Sudadera de color negro", 34.99, "ropa"));
        productoList.add(new Producto("Zapatillas 1", Uri.parse("android.resource://com.cmmr.rentgo/" + R.drawable.zaptillas1), "Zapatillas deportivas", 49.99, "calzado"));


        ProductAdapter productAdapter = new ProductAdapter(productoList);
        recyclerViewProducts.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewProducts.setAdapter(productAdapter);
    }
}
