package com.cmmr.rentgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProductoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        ImageView imageViewProduct = findViewById(R.id.imageViewProduct);
        TextView textViewProductTitle = findViewById(R.id.textViewProductTitle);
        TextView textViewProductDescription = findViewById(R.id.textViewProductDescription);
        TextView textViewProductPrice = findViewById(R.id.textViewProductPrice);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            int imageResId = extras.getInt("imageResId");
            String title = extras.getString("titulo");
            String description = extras.getString("descripcion");
            double precio = extras.getDouble("precio");


            imageViewProduct.setImageResource(imageResId);
            textViewProductTitle.setText(title);
            textViewProductDescription.setText(description);
            textViewProductPrice.setText("Precio: €" + precio);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                startActivity(new Intent(ProductoActivity.this, MenuPrincipal.class));
                                return true;
                            case R.id.navigation_profile:
                                startActivity(new Intent(ProductoActivity.this, Perfil.class));
                                return true;
                        }
                        return false;
                    }
                }
        );
    }
}
