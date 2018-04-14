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

public class ThucUongAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arrayThucUong;

    public ThucUongAdapter(Context context, ArrayList<Sanpham> arrayThucUong) {
        this.context = context;
        this.arrayThucUong = arrayThucUong;
    }

    @Override
    public int getCount() {
        return arrayThucUong.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayThucUong.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txttenthucuong,txtgiathucuong,txtmotathucuong;
        public ImageView imgThucuong;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_thucuong, null);

            viewHolder.txttenthucuong = (TextView) view.findViewById(R.id.textviewThucUong);
            viewHolder.txtgiathucuong = (TextView) view.findViewById(R.id.textviewGiaThucUong);
            viewHolder.txtmotathucuong = (TextView) view.findViewById(R.id.textviewMotaThucUong);
            viewHolder.imgThucuong = (ImageView) view.findViewById(R.id.imageviewThucUong);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        Sanpham sanpham = (Sanpham) getItem(i);
        viewHolder.txttenthucuong.setText(sanpham.getTensanpham());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiathucuong.setText(""+decimalFormat.format(sanpham.getGiasanpham())+ " Đ");

        viewHolder.txtmotathucuong.setMaxLines(2);
        viewHolder.txtmotathucuong.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotathucuong.setText(sanpham.getMotasanpham());

        Picasso.with(context).load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.imagesno)
                .error(R.drawable.imageerror)
                .into(viewHolder.imgThucuong);



        return view;
    }
}
