package com.example.tam.tb_food.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tam.tb_food.R;
import com.example.tam.tb_food.activity.MainActivity;
import com.example.tam.tb_food.model.Giohang;
import com.example.tam.tb_food.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class GiohangAdapter extends BaseAdapter {
    Context context;
    ArrayList<Giohang> arraygiohang;
    ViewHolder viewHolder = null;

    public GiohangAdapter(Context context, ArrayList<Giohang> arraygiohang) {
        this.context = context;
        this.arraygiohang = arraygiohang;
    }

    @Override
    public int getCount() {
        return arraygiohang.size();
    }

    @Override
    public Object getItem(int i) {
        return arraygiohang.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }




    public class ViewHolder{
        public TextView txttengiohang, txtgiagiohang;
        public ImageView imggiohang;
        public Button btnminus, btnvalues, btnplus;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.dong_giohang , null);


            viewHolder.txttengiohang    = (TextView) view.findViewById(R.id.textviewtengiohang);
            viewHolder.txtgiagiohang    = (TextView) view.findViewById(R.id.textviewgiagiohang);
            viewHolder.imggiohang       = (ImageView) view.findViewById(R.id.imageviewgiohang);
            viewHolder.btnminus         = (Button) view.findViewById(R.id.buttonMinus);
            viewHolder.btnvalues        = (Button) view.findViewById(R.id.buttonValues);
            viewHolder.btnplus          = (Button) view.findViewById(R.id.buttonPlus);

            view.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) view.getTag();}


        Giohang giohang = (Giohang) getItem(position);
        viewHolder.txttengiohang.setText(giohang.getTensp());


        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiagiohang.setText(decimalFormat.format(giohang.getGiasp())+ " Đ");


        Picasso.with(context).load(giohang.getHinhsp())
                .placeholder(R.drawable.imagesno)
                .error(R.drawable.imageerror)
                .into(viewHolder.imggiohang);



        viewHolder.btnvalues.setText(giohang.getSoluongsp() + "");

        int sl = Integer.parseInt(viewHolder.btnvalues.getText().toString());
        if(sl >= 10){
            viewHolder.btnplus.setVisibility(View.INVISIBLE);
            viewHolder.btnminus.setVisibility(View.VISIBLE);
        }else if(sl <= 1){
            viewHolder.btnminus.setVisibility(View.INVISIBLE);
        }else if (sl >= 1){
            viewHolder.btnplus.setVisibility(View.VISIBLE);
            viewHolder.btnminus.setVisibility(View.VISIBLE);
        }



        viewHolder.btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soluongmoinhat = Integer.parseInt(viewHolder.btnvalues.getText().toString()) +1;
                int soluonghientai = MainActivity.mangGioHang.get(position).getSoluongsp();
                long giahientai = MainActivity.mangGioHang.get(position).getGiasp();

                MainActivity.mangGioHang.get(position).setSoluongsp(soluongmoinhat);
                long giamoinhat = (giahientai*soluongmoinhat) / soluonghientai;
                MainActivity.mangGioHang.get(position).setGiasp(giamoinhat);


                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                viewHolder.txtgiagiohang.setText(decimalFormat.format(giamoinhat)+ " Đ");



                com.example.tam.tb_food.activity.Giohang.EventUltil();



                if(soluongmoinhat > 9){
                    viewHolder.btnplus.setVisibility(View.INVISIBLE);
                    viewHolder.btnminus.setVisibility(View.VISIBLE);
                    viewHolder.btnvalues.setText(String.valueOf(soluongmoinhat));
                }else{

                    viewHolder.btnplus.setVisibility(View.VISIBLE);
                    viewHolder.btnminus.setVisibility(View.VISIBLE);
                    viewHolder.btnvalues.setText(String.valueOf(soluongmoinhat));
                }
            }

        });

        viewHolder.btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluongmoinhat = Integer.parseInt(viewHolder.btnvalues.getText().toString()) -1;
                int soluonghientai = MainActivity.mangGioHang.get(position).getSoluongsp();
                long giahientai = MainActivity.mangGioHang.get(position).getGiasp();



                MainActivity.mangGioHang.get(position).setSoluongsp(soluongmoinhat);
                long giamoinhat = (giahientai*soluongmoinhat) / soluonghientai;
                MainActivity.mangGioHang.get(position).setGiasp(giamoinhat);


                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                viewHolder.txtgiagiohang.setText(decimalFormat.format(giamoinhat)+ " Đ");


                com.example.tam.tb_food.activity.Giohang.EventUltil();


                if(soluongmoinhat < 2){
                    viewHolder.btnplus.setVisibility(View.VISIBLE);
                    viewHolder.btnminus.setVisibility(View.INVISIBLE);
                    viewHolder.btnvalues.setText(String.valueOf(soluongmoinhat));
                }else{
                    viewHolder.btnplus.setVisibility(View.VISIBLE);
                    viewHolder.btnminus.setVisibility(View.VISIBLE);
                    viewHolder.btnvalues.setText(String.valueOf(soluongmoinhat));
                }
            }
        });


        return view;
    }
}

