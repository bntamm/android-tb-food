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

public class MonChienAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arrayMonchien;

    public MonChienAdapter(Context context, ArrayList<Sanpham> arrayMonchien) {
        this.context = context;
        this.arrayMonchien = arrayMonchien;
    }

    @Override
    public int getCount() {
        return arrayMonchien.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayMonchien.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txttenmonchien, txtgiamonchien, txtmotamonchien;
        public ImageView imgmonchien;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_monchien, null);

            viewHolder.txttenmonchien = (TextView) view.findViewById(R.id.textviewMonChien);
            viewHolder.txtgiamonchien = (TextView) view.findViewById(R.id.textviewGiaMonChien);
            viewHolder.txtmotamonchien = (TextView) view.findViewById(R.id.textviewMotaMonChien);
            viewHolder.imgmonchien = (ImageView) view.findViewById(R.id.imageviewMonChien);

            view.setTag(viewHolder);


        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        Sanpham sanpham = (Sanpham) getItem(i);
        viewHolder.txttenmonchien.setText(sanpham.getTensanpham());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiamonchien.setText(""+decimalFormat.format(sanpham.getGiasanpham())+ " Đ");

        viewHolder.txtmotamonchien.setMaxLines(2);
        viewHolder.txtmotamonchien.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotamonchien.setText(sanpham.getMotasanpham());

        Picasso.with(context).load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.imagesno)
                .error(R.drawable.imageerror)
                .into(viewHolder.imgmonchien);



        return view;
    }
}
