package com.example.demosaleapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> products){
        this.context = context;
        productList = products;

    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        final Product product = productList.get(position);
        holder.title.setText(product.getTitle());
        holder.price.setText(product.getPrice());
        Glide.with(context).load(product.getImage()).into(holder.imageView);

        holder.layoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoToDetail(product);
            }
        });

    }

    private void onClickGoToDetail(Product product) {
        Intent intent = new Intent(context, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("product", product);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder{
        private ConstraintLayout layoutMain;

        ImageView imageView, imageAddToCart;
        TextView title, price, describe;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            layoutMain = itemView.findViewById(R.id.main_layout);
            imageView = itemView.findViewById(R.id.imageview);
            title = itemView.findViewById(R.id.title_product);
            price = itemView.findViewById(R.id.price_product);
        }
    }
}
