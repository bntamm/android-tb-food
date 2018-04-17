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

public class ComAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arrayCom;

    public ComAdapter(Context context, ArrayList<Sanpham> arrayCom) {
        this.context = context;
        this.arrayCom = arrayCom;
    }

    @Override
    public int getCount() {
        return arrayCom.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayCom.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txttencom,txtgiacom,txtmotacom;
        public ImageView imgCom;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_com, null);

            viewHolder.txttencom = (TextView) view.findViewById(R.id.textviewCom);
            viewHolder.txtgiacom = (TextView) view.findViewById(R.id.textviewGiaCom);
            viewHolder.txtmotacom = (TextView) view.findViewById(R.id.textviewMotaCom);
            viewHolder.imgCom = (ImageView) view.findViewById(R.id.imageviewCom);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        Sanpham sanpham = (Sanpham) getItem(i);
        viewHolder.txttencom.setText(sanpham.getTensanpham());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiacom.setText(""+decimalFormat.format(sanpham.getGiasanpham())+ " Đ");

        viewHolder.txtmotacom.setMaxLines(2);
        viewHolder.txtmotacom.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotacom.setText(sanpham.getMotasanpham());

        Picasso.with(context).load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.imagesno)
                .error(R.drawable.imageerror)
                .into(viewHolder.imgCom);



        return view;
    }
}
