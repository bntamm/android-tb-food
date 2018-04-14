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

public class MonXaoAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arrayMonXao;

    public MonXaoAdapter(Context context, ArrayList<Sanpham> arrayMonXao) {
        this.context = context;
        this.arrayMonXao = arrayMonXao;
    }

    @Override
    public int getCount() {
        return arrayMonXao.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayMonXao.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txttenmonxao, txtgiamonxao, txtmotamonxao;
        public ImageView imgmonxao;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_monxao, null);

            viewHolder.txttenmonxao = (TextView) view.findViewById(R.id.textviewMonXao);
            viewHolder.txtgiamonxao = (TextView) view.findViewById(R.id.textviewGiaMonXao);
            viewHolder.txtmotamonxao = (TextView) view.findViewById(R.id.textviewMotaMonXao);
            viewHolder.imgmonxao = (ImageView) view.findViewById(R.id.imageviewMonXao);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        Sanpham sanpham = (Sanpham) getItem(i);
        viewHolder.txttenmonxao.setText(sanpham.getTensanpham());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiamonxao.setText(""+decimalFormat.format(sanpham.getGiasanpham())+" Đ");

        viewHolder.txtmotamonxao.setMaxLines(2);
        viewHolder.txtmotamonxao.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotamonxao.setText(sanpham.getMotasanpham());

        Picasso.with(context).load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.imagesno)
                .error(R.drawable.imageerror)
                .into(viewHolder.imgmonxao);


        return view;
    }
}
