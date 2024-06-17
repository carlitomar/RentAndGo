package com.cmmr.rentgo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.cmmr.rentgo.Utilitys.Producto;
import com.cmmr.rentgo.Utilitys.ProductAdapter;
import java.util.ArrayList;
import java.util.List;

public class MenuPrincipal extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD_PRODUCT = 1;

    private BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerViewProducts;
    private ProductAdapter productAdapter;
    private List<Producto> productoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        FloatingActionButton fabAddProduct = findViewById(R.id.fab_add_product);
        recyclerViewProducts = findViewById(R.id.recycler_view);
        SearchView searchView = findViewById(R.id.search_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                Toast.makeText(MenuPrincipal.this, "Home selected", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.navigation_profile:
                                Intent intent = new Intent(MenuPrincipal.this, Perfil.class);
                                intent.putParcelableArrayListExtra("productoList", new ArrayList<>(productoList));
                                startActivity(intent);
                                return true;
                        }
                        return false;
                    }
                }
        );

        productoList = new ArrayList<>();
        productAdapter = new ProductAdapter(productoList);
        productAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Producto producto = productoList.get(position);
                Intent intent = new Intent(MenuPrincipal.this, ProductoActivity.class);
                intent.putExtra("imageUri", producto.getImageUri().toString());
                intent.putExtra("titulo", producto.getTitle());
                intent.putExtra("descripcion", producto.getDescripcion());
                intent.putExtra("precio", producto.getPrecio());
                startActivity(intent);
            }
        });
        recyclerViewProducts.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewProducts.setAdapter(productAdapter);

        fabAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, SubirProductoActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_PRODUCT);
            }
        });

        loadDummyData();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                productAdapter.filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                productAdapter.filter(newText);
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_PRODUCT && resultCode == Activity.RESULT_OK && data != null) {
            String title = data.getStringExtra("title");
            String description = data.getStringExtra("description");
            String price = data.getStringExtra("price");
            String imageUri = data.getStringExtra("imageUri");

            Producto newProduct = new Producto(title, Uri.parse(imageUri), description, Double.parseDouble(price), "categoria");
            productoList.add(newProduct);
            productAdapter.notifyItemInserted(productoList.size() - 1);
        }
    }

    private void loadDummyData() {
        productoList.add(new Producto("Sudadera 1", Uri.parse("android.resource://com.cmmr.rentgo/" + R.drawable.sudadera1), "Sudadera de color azul", 29.99, "ropa"));
        productoList.add(new Producto("Sudadera 2", Uri.parse("android.resource://com.cmmr.rentgo/" + R.drawable.sudadera2), "Sudadera de color negro", 34.99, "ropa"));
        productoList.add(new Producto("Zapatillas 1", Uri.parse("android.resource://com.cmmr.rentgo/" + R.drawable.zaptillas1), "Zapatillas deportivas", 49.99, "calzado"));
        productoList.add(new Producto("Zapatillas 2", Uri.parse("android.resource://com.cmmr.rentgo/" + R.drawable.zapatillas2), "Zapatillas para correr", 59.99, "calzado"));
        productoList.add(new Producto("Caña de pescar", Uri.parse("android.resource://com.cmmr.rentgo/" + R.drawable.pescar1), "Caña de pescar de alta calidad", 39.99, "pesca"));

        productAdapter.notifyDataSetChanged();
    }
}
