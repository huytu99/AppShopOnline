package com.example.demosaleapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.example.demosaleapp.UtilsService.UtilService;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements Serializable {
    Spinner spinner;
    Product products;

    private DecimalFormat format = new DecimalFormat("###,###,###");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Button btnthem = findViewById(R.id.detail_btn);
        spinner = findViewById(R.id.spinnersl);
        CatchEventSpinner();
        Bundle bundle = getIntent().getExtras();
        if(bundle == null) {
            return;
        }else {
            products = (Product) bundle.get("product");
            TextView detailTitle = findViewById(R.id.detail_title);
            detailTitle.setText(products.getTitle());

            TextView detailPrice = findViewById(R.id.detail_price);
            detailPrice.setText(products.getPrice());

            ImageView imageView = findViewById(R.id.detail_image);
            Glide.with(this).load(products.getImage()).into(imageView);
            btnthem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addinCart();
                    Intent intent = new Intent(DetailActivity.this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Đã thêm sản phẩm vào giỏ hàng",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void addinCart() {
        if(UtilService.cartList.size() > 0){
            boolean flag = false;
            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
            for(int i = 0 ; i < UtilService.cartList.size(); i++){
                if(UtilService.cartList.get(i).getTitle() == products.getTitle()){
                    UtilService.cartList.get(i).setSlsp(soluong + UtilService.cartList.get(i).getSlsp());
                    int gia = Integer.parseInt(products.getPrice()) * UtilService.cartList.get(i).getSlsp();
                    UtilService.cartList.get(i).setPrice(gia);
                    flag = true;
                }
            }
            if(flag == false){
                int gia = Integer.parseInt(products.getPrice());
                Cart cart = new Cart();
                cart.setTitle(products.getTitle());
                cart.setPrice(gia);
                cart.setImage(products.getImage());
                cart.setSlsp(soluong);
                UtilService.cartList.add(cart);
            }
        }else {
            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
            int gia = Integer.parseInt(products.getPrice()) ;
            Cart cart = new Cart();
            cart.setTitle(products.getTitle());
            cart.setPrice(gia);
            cart.setImage(products.getImage());
            cart.setSlsp(soluong);
            UtilService.cartList.add(cart);
        }
    }
    private void CatchEventSpinner() {
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayAdapter);
    }
}