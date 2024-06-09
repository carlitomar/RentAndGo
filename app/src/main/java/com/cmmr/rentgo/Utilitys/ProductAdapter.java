package com.cmmr.rentgo.Utilitys;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.cmmr.rentgo.R;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> productList;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewProduct;
        private ImageView imageViewProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewProduct = itemView.findViewById(R.id.textViewProduct);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);

        }

        public void bind(Product product) {
            textViewProduct.setText(product.getTitle());
            imageViewProduct.setImageResource(product.getImageResId());
        }
    }
}
