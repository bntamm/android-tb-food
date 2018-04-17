package com.example.tam.tb_food.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tam.tb_food.R;
import com.example.tam.tb_food.model.Loaisp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Tam on 4/7/2018.
 */

public class LoaispAdapter extends BaseAdapter {
    ArrayList<Loaisp> arrlistLoaisp;
    private Context context;

    public LoaispAdapter(ArrayList<Loaisp> arrlistLoaisp, Context context) {
        this.arrlistLoaisp = arrlistLoaisp;
        this.context = context;
    }


    @Override
    public int getCount() {
        return arrlistLoaisp.size();
    }

    @Override
    public Object getItem(int i) {
        return arrlistLoaisp.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        TextView txttenloaisp;
        ImageView imgloaisp;

    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_listview_loaisp , null);

            viewHolder.txttenloaisp = (TextView) view.findViewById(R.id.textviewloaisp);
            viewHolder.imgloaisp = (ImageView) view.findViewById(R.id.imageviewloaisp);

            view.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) view.getTag();

            Loaisp loaisp = (Loaisp) getItem(i);
            viewHolder.txttenloaisp.setText(loaisp.getTenloaisp());
            Picasso.with(context).load(loaisp.getHinhanhloaisp())
                    .placeholder(R.drawable.imagesno)
                    .error(R.drawable.imageerror)
                    .into(viewHolder.imgloaisp);
        }


        return view;
    }
}
