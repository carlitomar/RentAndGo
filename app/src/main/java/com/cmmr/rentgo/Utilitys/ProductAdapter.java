package com.cmmr.rentgo.Utilitys;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.cmmr.rentgo.ProductoActivity;
import com.cmmr.rentgo.R;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Producto> productoList;
    private List<Producto> productoListFull;
    private OnItemClickListener mListener;

    public ProductAdapter(List<Producto> productoList) {
        this.productoList = productoList;
        this.productoListFull = new ArrayList<>(productoList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Producto producto = productoList.get(position);
        holder.bind(producto);

        holder.imageViewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductoActivity.class);
                intent.putExtra("imageUri", producto.getImageUri().toString());
                intent.putExtra("titulo", producto.getTitle());
                intent.putExtra("descripcion", producto.getDescripcion());
                intent.putExtra("precio", producto.getPrecio());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productoList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewProduct;
        private ImageView imageViewProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewProduct = itemView.findViewById(R.id.textViewProduct);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
            itemView.setOnClickListener(this);
        }

        public void bind(Producto producto) {
            textViewProduct.setText(producto.getTitle());
            loadImageFromUri(imageViewProduct.getContext(), producto.getImageUri(), imageViewProduct);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(position);
                }
            }
        }

        private void loadImageFromUri(Context context, Uri uri, ImageView imageView) {
            ContentResolver contentResolver = context.getContentResolver();
            try (InputStream inputStream = contentResolver.openInputStream(uri)) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void filter(String text) {
        productoList.clear();
        if (text.isEmpty()) {
            productoList.addAll(productoListFull);
        } else {
            String filterPattern = text.toLowerCase().trim();
            for (Producto item : productoListFull) {
                if (item.getTitle().toLowerCase().contains(filterPattern)) {
                    productoList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}
