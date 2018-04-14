package com.example.tam.tb_food.adapter;

import android.content.Context;
import android.media.Image;
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

public class LauAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arrayLau;

    public LauAdapter(Context context, ArrayList<Sanpham> arrayLau) {
        this.context = context;
        this.arrayLau = arrayLau;
    }

    @Override
    public int getCount() {
        return arrayLau.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayLau.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txttenlau, txtgialau, txtmotalau;
        public ImageView imglau;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_lau , null);


            viewHolder.txttenlau = (TextView) view.findViewById(R.id.textviewLau);
            viewHolder.txtgialau = (TextView) view.findViewById(R.id.textviewGiaLau);
            viewHolder.txtmotalau = (TextView) view.findViewById(R.id.textviewMotaLau);
            viewHolder.imglau = (ImageView) view.findViewById(R.id.imageviewLau);

            view.setTag(viewHolder);


        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        Sanpham sanpham = (Sanpham) getItem(i);

        viewHolder.txttenlau.setText(sanpham.getTensanpham());


        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgialau.setText("" +decimalFormat.format(sanpham.getGiasanpham())+" Đ");

        viewHolder.txtmotalau.setMaxLines(2);
        viewHolder.txtmotalau.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotalau.setText(sanpham.getMotasanpham());

        Picasso.with(context).load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.imagesno)
                .error(R.drawable.imageerror)
                .into(viewHolder.imglau);




        return view;
    }
}
