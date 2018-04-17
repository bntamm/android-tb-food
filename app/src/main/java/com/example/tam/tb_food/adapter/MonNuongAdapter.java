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

public class MonNuongAdapter extends BaseAdapter{
    Context context;
    ArrayList<Sanpham> arrayMonnuong;

    public MonNuongAdapter(Context context, ArrayList<Sanpham> arrayMonnuong) {
        this.context = context;
        this.arrayMonnuong = arrayMonnuong;
    }

    @Override
    public int getCount() {
        return arrayMonnuong.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayMonnuong.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txttenmonnuong, txtgiamonnuong, txtmotamonnuong;
        public ImageView imgmonnuong;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_monnuong , null);


            viewHolder.txttenmonnuong = (TextView) view.findViewById(R.id.textviewMonnuong);
            viewHolder.txtgiamonnuong = (TextView) view.findViewById(R.id.textviewGiaMonnuong);
            viewHolder.txtmotamonnuong = (TextView) view.findViewById(R.id.textviewMotaMonnuong);
            viewHolder.imgmonnuong = (ImageView) view.findViewById(R.id.imageviewMonnuong);

            view.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        Sanpham sanpham = (Sanpham) getItem(i);

        viewHolder.txttenmonnuong.setText(sanpham.getTensanpham());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiamonnuong.setText(""+decimalFormat.format(sanpham.getGiasanpham())+" Đ");

        viewHolder.txtmotamonnuong.setMaxLines(2);
        viewHolder.txtmotamonnuong.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotamonnuong.setText(sanpham.getMotasanpham());

        Picasso.with(context).load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.imagesno)
                .error(R.drawable.imageerror)
                .into(viewHolder.imgmonnuong);

        return view;
    }
}
