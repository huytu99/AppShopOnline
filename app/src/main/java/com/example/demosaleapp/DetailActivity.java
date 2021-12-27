package com.example.demosaleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null) {
            return;
        }

        Product product = (Product) bundle.get("product");
        TextView detailTitle = findViewById(R.id.detail_title);
        detailTitle.setText(product.getTitle());

        TextView detailPrice = findViewById(R.id.detail_price);
        detailPrice.setText(product.getPrice());

        ImageView imageView = findViewById(R.id.detail_image);
        Glide.with(this).load(product.getImage()).into(imageView);


    }

}