package com.example.demosaleapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.example.demosaleapp.EventBus.totalEvent;
import com.example.demosaleapp.UtilsService.UtilService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartFragment extends Fragment {
    private DecimalFormat format;
    private View view;
    private MainActivity home;
    private TextView tvthongbaoEmpty;
    private RecyclerView cartlist;
    private TextView tvtongtien;
    private Button btnmua , btntieptuc;
    private CartAdapter cartAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart,container,false);
        initsss();
        initcontrol();
        total();
        btntieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home.tranhome();
            }
        });
        btnmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Payment.class);
                startActivity(intent);
            }
        });
        return view;

    }

    public void total() {
        int tongtien = 0;
        for(int i = 0; i < UtilService.cartList.size(); i++){
            tongtien += UtilService.cartList.get(i).getPrice() * UtilService.cartList.get(i).getSlsp();
        }
        DecimalFormat format = new DecimalFormat("###,###,###");
        tvtongtien.setText(format.format(tongtien));
    }

    private void initcontrol() {
        cartlist.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        cartlist.setLayoutManager(layoutManager);
        if(UtilService.cartList.size() == 0){
            tvthongbaoEmpty.setVisibility(View.VISIBLE);
        }else {
            cartAdapter = new CartAdapter(getContext(),UtilService.cartList);
            cartlist.setAdapter(cartAdapter);
            tvthongbaoEmpty.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initsss() {
        tvthongbaoEmpty = view.findViewById(R.id.txtthongbao);
        tvtongtien = view.findViewById(R.id.txttongtien);
        cartlist = view.findViewById(R.id.listgiohang);
        btnmua = view.findViewById(R.id.btnthanhtoan);
        btntieptuc = view.findViewById(R.id.btntieptuc);
        home = (MainActivity) getActivity();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void eventTotal(totalEvent event){
        if(event != null){
            total();
        }
    }
}