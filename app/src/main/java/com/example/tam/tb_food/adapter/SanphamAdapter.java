package com.example.tam.tb_food.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tam.tb_food.R;
import com.example.tam.tb_food.activity.ChiTietSanPham;
import com.example.tam.tb_food.model.Sanpham;
import com.example.tam.tb_food.util.CheckConection;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Tam on 4/7/2018.
 */

public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.ItemHolder> {
    Context context;
    ArrayList<Sanpham> arraysanpham;

    public SanphamAdapter(Context context, ArrayList<Sanpham> arraysanpham) {
        this.context = context;
        this.arraysanpham = arraysanpham;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sanphammoinhat , null);
        ItemHolder itemHolder = new ItemHolder(v);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {

        Sanpham sanpham = arraysanpham.get(position);
        holder.txttensanpham.setText(sanpham.getTensanpham());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgiasanpham.setText(""+decimalFormat.format(sanpham.getGiasanpham())+" Đ");

        Picasso.with(context).load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.imagesno)
                .error(R.drawable.imageerror)
                .into(holder.imghinhsanpham);

    }

    @Override
    public int getItemCount() {
        return arraysanpham.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imghinhsanpham;
        public TextView txttensanpham, txtgiasanpham;


        public ItemHolder(View itemView) {
            super(itemView);

            imghinhsanpham = (ImageView) itemView.findViewById(R.id.imageviewsanpham);
            txttensanpham = (TextView) itemView.findViewById(R.id.textviewtensanpham);
            txtgiasanpham = (TextView) itemView.findViewById(R.id.textviewgiasanpham);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context , ChiTietSanPham.class);
                    intent.putExtra("thongtinsanpham" , arraysanpham.get(getPosition()));

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    CheckConection.ShowToast_Sort(context , arraysanpham.get(getPosition()).getTensanpham());
                    context.startActivity(intent);
                }
            });
        }
    }
}
