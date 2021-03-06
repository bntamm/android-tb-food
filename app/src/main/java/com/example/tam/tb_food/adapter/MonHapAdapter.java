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

public class MonHapAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arrayMonHap;

    public MonHapAdapter(Context context, ArrayList<Sanpham> arrayMonHap) {
        this.context = context;
        this.arrayMonHap = arrayMonHap;
    }

    @Override
    public int getCount() {
        return arrayMonHap.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayMonHap.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txttenmonhap,txtgiamonhap,txtmotamonhap;
        public ImageView imgMonhap;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_monhap, null);

            viewHolder.txttenmonhap = (TextView) view.findViewById(R.id.textviewMonHap);
            viewHolder.txtgiamonhap = (TextView) view.findViewById(R.id.textviewGiaMonHap);
            viewHolder.txtmotamonhap = (TextView) view.findViewById(R.id.textviewMotaMonHap);
            viewHolder.imgMonhap = (ImageView) view.findViewById(R.id.imageviewMonHap);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        Sanpham sanpham = (Sanpham) getItem(i);
        viewHolder.txttenmonhap.setText(sanpham.getTensanpham());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiamonhap.setText(""+decimalFormat.format(sanpham.getGiasanpham())+ " Đ");

        viewHolder.txtmotamonhap.setMaxLines(2);
        viewHolder.txtmotamonhap.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotamonhap.setText(sanpham.getMotasanpham());

        Picasso.with(context).load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.imagesno)
                .error(R.drawable.imageerror)
                .into(viewHolder.imgMonhap);



        return view;
    }
}
