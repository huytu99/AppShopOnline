package com.example.demosaleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Payment extends AppCompatActivity {
    EditText editname , editsdt ,editaddress;
    Button btnmua , btnhuy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        inits();
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void inits() {
        btnhuy = findViewById(R.id.btnhuy);
        btnmua = findViewById(R.id.btnmua);
        editname = findViewById(R.id.editten);
        editsdt = findViewById(R.id.editsdt);
        editaddress = findViewById(R.id.editdiachi);
    }
}