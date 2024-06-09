package com.cmmr.rentgo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.cmmr.rentgo.Utilitys.currencyChange;

public class SubirProductoActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;

    private EditText editTitle;
    private EditText editDescription;
    private EditText editPrice;
    private Button buttonAddImage;
    private ImageView imageView;
    private Button buttonSubmit;

    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir_producto);

        editTitle = findViewById(R.id.editTitle);
        editDescription = findViewById(R.id.editDescription);
        editPrice = findViewById(R.id.editPrice);
        buttonAddImage = findViewById(R.id.buttonAddImage);
        imageView = findViewById(R.id.imageView);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        editPrice = findViewById(R.id.editPrice);
        editPrice.addTextChangedListener(new currencyChange(editPrice));

        buttonAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE);
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString();
                String description = editDescription.getText().toString();
                String price = editPrice.getText().toString();
                if (title.isEmpty() || description.isEmpty() || price.isEmpty() || imageUri == null) {
                    Toast.makeText(SubirProductoActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(SubirProductoActivity.this, "Producto subido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
            imageView.setVisibility(View.VISIBLE);
        }
    }
}
