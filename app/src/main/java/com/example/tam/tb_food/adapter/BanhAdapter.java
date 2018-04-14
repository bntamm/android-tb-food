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

public class BanhAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arrayBanh;

    public BanhAdapter(Context context, ArrayList<Sanpham> arrayBanh) {
        this.context = context;
        this.arrayBanh = arrayBanh;
    }

    @Override
    public int getCount() {
        return arrayBanh.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayBanh.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txttenbanh, txtgiabanh, txtmotabanh;
        public ImageView imgBanh;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_banh , null);

            viewHolder.txttenbanh = (TextView) view.findViewById(R.id.textviewBanh);
            viewHolder.txtgiabanh = (TextView) view.findViewById(R.id.textviewGiaBanh);
            viewHolder.txtmotabanh = (TextView) view.findViewById(R.id.textviewMotaBanh);
            viewHolder.imgBanh = (ImageView) view.findViewById(R.id.imageviewBanh);

            view.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        Sanpham sanpham = (Sanpham) getItem(i);
        viewHolder.txttenbanh.setText(sanpham.getTensanpham());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiabanh.setText("" +decimalFormat.format(sanpham.getGiasanpham()) + " Đ");

        viewHolder.txtmotabanh.setMaxLines(2);
        viewHolder.txtmotabanh.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotabanh.setText(sanpham.getMotasanpham());

        Picasso.with(context).load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.imagesno)
                .error(R.drawable.imageerror)
                .into(viewHolder.imgBanh);


        return view;
    }
}
