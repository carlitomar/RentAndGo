package com.cmmr.rentgo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MenuPrincipal extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        FloatingActionButton fabAddProduct = findViewById(R.id.fab_add_product);

        // Configuración del listener para la navegación del menú
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                // Acción cuando se selecciona el elemento de inicio
                                Toast.makeText(MenuPrincipal.this, "Home selected", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.navigation_profile:
                                // Acción cuando se selecciona el elemento de perfil
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
    }
}
