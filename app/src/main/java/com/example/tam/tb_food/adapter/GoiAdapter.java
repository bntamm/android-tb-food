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

public class GoiAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arrayGoi;

    public GoiAdapter(Context context, ArrayList<Sanpham> arrayGoi) {
        this.context = context;
        this.arrayGoi = arrayGoi;
    }

    @Override
    public int getCount() {
        return arrayGoi.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayGoi.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txttengoi,txtgiagoi,txtmotagoi;
        public ImageView imgGoi;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_goi, null);

            viewHolder.txttengoi = (TextView) view.findViewById(R.id.textviewGoi);
            viewHolder.txtgiagoi = (TextView) view.findViewById(R.id.textviewGiaGoi);
            viewHolder.txtmotagoi = (TextView) view.findViewById(R.id.textviewMotaGoi);
            viewHolder.imgGoi = (ImageView) view.findViewById(R.id.imageviewGoi);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        Sanpham sanpham = (Sanpham) getItem(i);
        viewHolder.txttengoi.setText(sanpham.getTensanpham());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiagoi.setText(""+decimalFormat.format(sanpham.getGiasanpham())+ " Đ");

        viewHolder.txtmotagoi.setMaxLines(2);
        viewHolder.txtmotagoi.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotagoi.setText(sanpham.getMotasanpham());

        Picasso.with(context).load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.imagesno)
                .error(R.drawable.imageerror)
                .into(viewHolder.imgGoi);



        return view;
    }
}
