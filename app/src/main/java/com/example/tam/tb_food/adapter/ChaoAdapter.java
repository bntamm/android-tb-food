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

public class ChaoAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arrayChao;

    public ChaoAdapter(Context context, ArrayList<Sanpham> arrayChao) {
        this.context = context;
        this.arrayChao = arrayChao;
    }

    @Override
    public int getCount() {
        return arrayChao.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayChao.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txttenchao,txtgiachao,txtmotachao;
        public ImageView imgChao;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_chao, null);

            viewHolder.txttenchao = (TextView) view.findViewById(R.id.textviewChao);
            viewHolder.txtgiachao = (TextView) view.findViewById(R.id.textviewGiaChao);
            viewHolder.txtmotachao = (TextView) view.findViewById(R.id.textviewMotaChao);
            viewHolder.imgChao = (ImageView) view.findViewById(R.id.imageviewChao);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        Sanpham sanpham = (Sanpham) getItem(i);
        viewHolder.txttenchao.setText(sanpham.getTensanpham());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiachao.setText(""+decimalFormat.format(sanpham.getGiasanpham())+ " Đ");

        viewHolder.txtmotachao.setMaxLines(2);
        viewHolder.txtmotachao.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotachao.setText(sanpham.getMotasanpham());

        Picasso.with(context).load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.imagesno)
                .error(R.drawable.imageerror)
                .into(viewHolder.imgChao);



        return view;
    }
}
