package com.example.tam.tb_food.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tam.tb_food.R;
import com.example.tam.tb_food.adapter.GiohangAdapter;
import com.example.tam.tb_food.util.CheckConection;

import java.text.DecimalFormat;




public class Giohang extends AppCompatActivity {
    Toolbar toolbarGiohang;
    ListView lvGiohang;
    TextView txtThongbao;
    static TextView txtTongtien;
    Button btnThanhtoan, btnTieptucmua;
    GiohangAdapter giohangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        AnhXa();

        ActionBackToolBar();
        CheckData();

        EventUltil();


        CatchOnItemsListView();


        EventButton();


    }

    private void EventButton() {
        btnTieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                startActivity(intent);
            }});

        btnThanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(MainActivity.mangGioHang.size() > 0  ){
                    Intent intent = new Intent(getApplicationContext() , ThongTinThanhToanKhachHang.class);
                    startActivity(intent);
                }else{
                    CheckConection.ShowToast_Sort(getApplicationContext() , "Giỏ hàng chưa có sản phẩm nào!");
                }}});


    }

    private void CatchOnItemsListView() {
        lvGiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                android.support.v7.app.AlertDialog.Builder buider = new android.support.v7.app.AlertDialog.Builder(Giohang.this);
                buider.setTitle("Xóa sản phẩm!");
                buider.setMessage("Xóa sản phẩm này?");
                buider.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        if(MainActivity.mangGioHang.size() <= 0){
                            txtThongbao.setVisibility(View.VISIBLE);
                        }else{
                            MainActivity.mangGioHang.remove(position);
                            //cập nhật lại giỏ hàng
                            giohangAdapter.notifyDataSetChanged();
                            //cập nhật lại tổng tiền
                            EventUltil();

                            if(MainActivity.mangGioHang.size() <= 0){
                                txtThongbao.setVisibility(View.VISIBLE);
                            }else{
                                txtThongbao.setVisibility(View.INVISIBLE);
                                //cập nhật lại giỏ hàng
                                giohangAdapter.notifyDataSetChanged();
                                //cập nhật lại tổng tiền
                                EventUltil();
                            }}}});

                buider.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        giohangAdapter.notifyDataSetChanged();
                        EventUltil();
                    }});
                buider.show();


                return true;
            }
        });
    }

    public static void EventUltil() {
        long tongtien = 0;
        for(int i = 0 ; i < MainActivity.mangGioHang.size() ; i++){
            tongtien += MainActivity.mangGioHang.get(i).getGiasp();

        }

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTongtien.setText(decimalFormat.format(tongtien) + "Đ");
    }


    private void CheckData() {
        if(MainActivity.mangGioHang.size() <= 0){
            giohangAdapter.notifyDataSetChanged();
            txtThongbao.setVisibility(View.VISIBLE);
            lvGiohang.setVisibility(View.INVISIBLE);
        }else{
            giohangAdapter.notifyDataSetChanged();
            txtThongbao.setVisibility(View.INVISIBLE);
            lvGiohang.setVisibility(View.VISIBLE);
        }
    }

    private void AnhXa() {
        toolbarGiohang          = (Toolbar) findViewById(R.id.toolbarGiohang);
        lvGiohang               = (ListView) findViewById(R.id.listviewgiohang);
        txtThongbao             = (TextView) findViewById(R.id.textviewthongbao);
        txtTongtien             = (TextView) findViewById(R.id.textviewTongtien);
        btnThanhtoan            = (Button) findViewById(R.id.buttonthanhtoangiohang);
        btnTieptucmua           = (Button) findViewById(R.id.buttontieptucmuahang);

        giohangAdapter = new GiohangAdapter(Giohang.this , MainActivity.mangGioHang);
        lvGiohang.setAdapter(giohangAdapter);
    }



    private void ActionBackToolBar() {
        setSupportActionBar(toolbarGiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarGiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }});}
}
