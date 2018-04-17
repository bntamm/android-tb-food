package com.example.tam.tb_food.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tam.tb_food.R;
import com.example.tam.tb_food.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Tam on 4/8/2018.
 */

public class MonKhoAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arrayMonKho;

    public MonKhoAdapter(Context context, ArrayList<Sanpham> arrayMonKho) {
        this.context = context;
        this.arrayMonKho = arrayMonKho;
    }

    @Override
    public int getCount() {
        return arrayMonKho.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayMonKho.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txttenmonkho,txtgiamonkho,txtmotamonkho;
        public ImageView imgMonkho;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_monkho, null);

            viewHolder.txttenmonkho = (TextView) view.findViewById(R.id.textviewMonKho);
            viewHolder.txtgiamonkho = (TextView) view.findViewById(R.id.textviewGiaMonKho);
            viewHolder.txtmotamonkho = (TextView) view.findViewById(R.id.textviewMotaMonKho);
            viewHolder.imgMonkho = (ImageView) view.findViewById(R.id.imageviewMonKho);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        Sanpham sanpham = (Sanpham) getItem(i);
        viewHolder.txttenmonkho.setText(sanpham.getTensanpham());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiamonkho.setText(""+decimalFormat.format(sanpham.getGiasanpham())+ " Đ");

        viewHolder.txtmotamonkho.setMaxLines(2);
        viewHolder.txtmotamonkho.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotamonkho.setText(sanpham.getMotasanpham());

        Picasso.with(context).load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.imagesno)
                .error(R.drawable.imageerror)
                .into(viewHolder.imgMonkho);



        return view;
    }
}
