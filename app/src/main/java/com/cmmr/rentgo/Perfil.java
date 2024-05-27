package com.cmmr.rentgo;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Perfil extends AppCompatActivity {

    // Views
    private TextView textViewName;
    private TextView textViewLastName;
    private TextView textViewAddress;
    private RecyclerView recyclerViewProducts;

    // Adapter for RecyclerView
    private ProductAdapter productAdapter;

    // Database Helper
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Initialize views
        textViewName = findViewById(R.id.textViewName);
        textViewLastName = findViewById(R.id.textViewLastName);
        textViewAddress = findViewById(R.id.textViewAddress);
        recyclerViewProducts = findViewById(R.id.recyclerViewProducts);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Set user data
        loadUserData();

        // Load products from database and populate RecyclerView
        List<Producto> productList = loadProductsFromDatabase();
        productAdapter = new ProductAdapter(productList);
        recyclerViewProducts.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewProducts.setAdapter(productAdapter);
    }

    // Method to load user data from database
    private void loadUserData() {
        // You should implement this method based on how your database is structured
        // For example:
        Cursor cursor = databaseHelper.getUserData(); // Replace this with your method to query user data
        if (cursor != null && cursor.moveToFirst()) {
            textViewName.setText(cursor.getString(cursor.getColumnIndex("name")));
            textViewLastName.setText(cursor.getString(cursor.getColumnIndex("last_name")));
            textViewAddress.setText(cursor.getString(cursor.getColumnIndex("address")));
            cursor.close();
        }
    }

    // Method to load products from database
    private List<Producto> loadProductsFromDatabase() {
        List<Producto> productList = new ArrayList<>();
        // You should implement this method based on how your database is structured
        // For example:
        Cursor cursor = databaseHelper.getAllProducts(); // Replace this with your method to query products
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String productName = cursor.getString(cursor.getColumnIndex("name"));
                int productImageResId = cursor.getInt(cursor.getColumnIndex("image_res_id"));
                productList.add(new Producto(productName, productImageResId));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return productList;
    }
}

