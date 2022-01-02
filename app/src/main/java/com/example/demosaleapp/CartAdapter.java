package com.example.demosaleapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.demosaleapp.EventBus.totalEvent;
import com.example.demosaleapp.UtilsService.UtilService;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder>{
    Context context;
    List<Cart> cartList;
    private int sl;

    public CartAdapter(Context context, List<Cart> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listgiohang, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Cart cart = cartList.get(position);
        holder.titlesp.setText(cart.getTitle());
        holder.btnvalue.setText(cart.getSlsp()+"");
        Glide.with(context).load(cart.getImage()).into(holder.imageViewsp);
        DecimalFormat format = new DecimalFormat("###,###,###");
        holder.pricesp.setText("Giá: " + format.format(cart.getPrice()) +"Đ");
        holder.btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cartList.get(position).getSlsp() > 1) {
                    sl = cartList.get(position).getSlsp() - 1;
                    cartList.get(position).setSlsp(sl);
                    holder.btnvalue.setText(cartList.get(position).getSlsp() + "");
                    int gia = cartList.get(position).getSlsp() * cartList.get(position).getPrice();
                    EventBus.getDefault().postSticky(new totalEvent());
                }else if(cartList.get(position).getSlsp() == 1){
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có muốn xóa sản phẩm này không ?");
                    builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            UtilService.cartList.remove(position);
                            notifyDataSetChanged();
                            EventBus.getDefault().postSticky(new totalEvent());
                        }
                    });
                    builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.show();
                }
            }
        });
        holder.btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cartList.get(position).getSlsp() < 10) {
                    sl = cartList.get(position).getSlsp() + 1;
                    cartList.get(position).setSlsp(sl);
                    holder.btnvalue.setText(cartList.get(position).getSlsp() + "");
                    int gia = cartList.get(position).getSlsp() * cartList.get(position).getPrice();
                    EventBus.getDefault().postSticky(new totalEvent());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewsp;
        TextView titlesp , pricesp;
        Button btnplus, btnvalue, btnminus;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewsp = itemView.findViewById(R.id.imagegiohang);
            titlesp = itemView.findViewById(R.id.tenspgiohang);
            pricesp = itemView.findViewById(R.id.giaspgiohang);
            btnplus = itemView.findViewById(R.id.btncongsl);
            btnminus = itemView.findViewById(R.id.btntrusl);
            btnvalue = itemView.findViewById(R.id.btnhtsl);

        }
    }
}
