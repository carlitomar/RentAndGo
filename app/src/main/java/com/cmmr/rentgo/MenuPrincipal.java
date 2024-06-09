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
import com.cmmr.rentgo.Utilitys.Product;
import com.cmmr.rentgo.Utilitys.ProductAdapter;
import java.util.ArrayList;
import java.util.List;

public class MenuPrincipal extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerViewProducts;
    private ProductAdapter productAdapter;

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
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Sudadera 1", R.drawable.sudadera1));
        productList.add(new Product("Sudadera 2", R.drawable.sudadera2));
        productList.add(new Product("Zapatillas 1", R.drawable.zaptillas1));
        productList.add(new Product("Zapatillas 2", R.drawable.zapatillas2));
        productList.add(new Product("Caña de pescar", R.drawable.pescar1));

        productAdapter = new ProductAdapter(productList);
        recyclerViewProducts.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewProducts.setAdapter(productAdapter);
    }
}
