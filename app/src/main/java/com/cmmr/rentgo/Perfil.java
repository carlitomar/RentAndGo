package com.cmmr.rentgo;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cmmr.rentgo.Utilitys.Product;
import com.cmmr.rentgo.Utilitys.ProductAdapter;
import java.util.ArrayList;
import java.util.List;

public class Perfil extends AppCompatActivity {

    // Views
    private TextView textViewName;
    private TextView textViewLastName;
    private TextView textViewAddress;
    private RecyclerView recyclerViewProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Initialize views
        textViewName = findViewById(R.id.textViewName);
        textViewLastName = findViewById(R.id.textViewLastName);
        textViewAddress = findViewById(R.id.textViewAddress);
        recyclerViewProducts = findViewById(R.id.recyclerViewProducts);

        // Load dummy data
        loadDummyData();
    }

    // Method to load dummy data into TextViews and RecyclerView
    private void loadDummyData() {
        // Dummy user data
        String name = "Pepe";
        String lastName = "Perez";
        String address = "Madrid,Villanueva de la cañada";

        // Set data to TextViews
        textViewName.setText(name);
        textViewLastName.setText(lastName);
        textViewAddress.setText(address);

        // Dummy product list with Product objects
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Sudadera 1", R.drawable.sudadera1));
        productList.add(new Product("Sudadera 2", R.drawable.sudadera2));

        // Set dummy product list to RecyclerView
        ProductAdapter productAdapter = new ProductAdapter(productList);
        recyclerViewProducts.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewProducts.setAdapter(productAdapter);
    }
}
