package com.cmmr.rentgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import com.cmmr.rentgo.MenuPrincipal;
import com.cmmr.rentgo.Perfil;
import com.cmmr.rentgo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ProductoActivity extends AppCompatActivity {

    private ImageView imageViewProduct;
    private TextView textViewProductTitle;
    private TextView textViewProductDescription;
    private TextView textViewProductPrice;
    private Button buttonStartDate;
    private Button buttonEndDate;
    private Calendar calendar;
    private SimpleDateFormat sdf;
    private double precioPorDia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        imageViewProduct = findViewById(R.id.imageViewProduct);
        textViewProductTitle = findViewById(R.id.textViewProductTitle);
        textViewProductDescription = findViewById(R.id.textViewProductDescription);
        textViewProductPrice = findViewById(R.id.textViewProductPrice);
        buttonStartDate = findViewById(R.id.buttonStartDate);
        buttonEndDate = findViewById(R.id.buttonEndDate);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        calendar = Calendar.getInstance();
        sdf = new SimpleDateFormat("dd/MM/yyyy");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int imageResId = extras.getInt("imageResId");
            String title = extras.getString("titulo");
            String description = extras.getString("descripcion");
            double precio = extras.getDouble("precio");

            imageViewProduct.setImageResource(imageResId);
            textViewProductTitle.setText(title);
            textViewProductDescription.setText(description);
            textViewProductPrice.setText("Precio por día: €" + precio);
            precioPorDia = precio;
        }

        buttonStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(buttonStartDate);
            }
        });

        buttonEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(buttonEndDate);
            }
        });

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

    private void showDatePickerDialog(final Button button) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        button.setText(selectedDate);

                        // Calcular precio basado en la diferencia de días
                        double precioTotal = calcularPrecioTotal(); // Función para calcular el precio total
                        textViewProductPrice.setText("Precio total: €" + precioTotal);
                    }
                },
                year, month, dayOfMonth);

        datePickerDialog.show();
    }

    private double calcularPrecioTotal() {
        String startDateString = buttonStartDate.getText().toString();
        String endDateString = buttonEndDate.getText().toString();

        try {
            Date startDate = sdf.parse(startDateString);
            Date endDate = sdf.parse(endDateString);

            long differenceMillis = endDate.getTime() - startDate.getTime();
            long differenceDays = TimeUnit.DAYS.convert(differenceMillis, TimeUnit.MILLISECONDS);

            return precioPorDia * differenceDays;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}
