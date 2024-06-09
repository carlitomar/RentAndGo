package com.cmmr.rentgo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
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
                                startActivity(new Intent(MenuPrincipal.this, Perfil.class));
                                return true;
                        }
                        return false;
                    }
                }
        );

        productAdapter = new ProductAdapter(new ArrayList<>());
        productAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Producto producto = productoList.get(position);
                Intent intent = new Intent(MenuPrincipal.this, ProductoActivity.class);
                intent.putExtra("imageResId", producto.getImageResId());
                intent.putExtra("title", producto.getTitle());
                intent.putExtra("description", producto.getDescripcion());
                intent.putExtra("price", producto.getPrecio());
                startActivity(intent);
            }
        });
        recyclerViewProducts.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewProducts.setAdapter(productAdapter);

        fabAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, SubirProductoActivity.class);
                startActivity(intent);
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

    private void loadDummyData() {
        List<Producto> productoList = new ArrayList<>();

        Producto producto1 = new Producto("Sudadera 1", R.drawable.sudadera1, "Sudadera de color azul", 29.99,"ropa");
        productoList.add(producto1);

        Producto producto2 = new Producto("Sudadera 2", R.drawable.sudadera2, "Sudadera de color negro", 34.99,"ropa");
        productoList.add(producto2);

        Producto producto3 = new Producto("Zapatillas 1", R.drawable.zaptillas1, "Zapatillas deportivas", 49.99,"calzado");
        productoList.add(producto3);

        Producto producto4 = new Producto("Zapatillas 2", R.drawable.zapatillas2, "Zapatillas para correr", 59.99,"calzado");
        productoList.add(producto4);

        Producto producto5 = new Producto("Caña de pescar", R.drawable.pescar1, "Caña de pescar de alta calidad", 39.99,"pesca");
        productoList.add(producto5);

        productAdapter = new ProductAdapter(productoList);
        recyclerViewProducts.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewProducts.setAdapter(productAdapter);
    }
}
