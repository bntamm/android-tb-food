package com.example.tam.tb_food.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tam.tb_food.R;
import com.example.tam.tb_food.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChiTietSanPham extends AppCompatActivity {
    Toolbar toolbarChitiet;
    ImageView imgChitiet;
    TextView txttenchitiet,txtgiachitiet,txtmotachitiet;
    Spinner spinner;
    Button btndatmuahang;

    int id = 0;
    String TenChitiet = "";
    int GiaChiTiet = 0;
    String HinhAnhChitiet = "";
    String MoTaChiTiet = "";
    int IdSanphamchitiet = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        AnhXa();
        ActionBackToolbar();
        GetInformation();
        CatchEventSpinner();
        EventButton();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menugiohang :
                Intent intent = new Intent(getApplicationContext() , Giohang.class);
                startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart , menu);

        return true;
    }

    private void EventButton() {
        btndatmuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(MainActivity.mangGioHang.size() > 0){

                    int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exists = false;
                    for(int i = 0 ; i < MainActivity.mangGioHang.size() ; i++){

                        if(MainActivity.mangGioHang.get(i).getIdsp() == id){
                            MainActivity.mangGioHang.get(i).setSoluongsp(MainActivity.mangGioHang.get(i).getSoluongsp() + sl);

                            if(MainActivity.mangGioHang.get(i).getSoluongsp() >= 10){
                                MainActivity.mangGioHang.get(i).setSoluongsp(10);
                            }

                            MainActivity.mangGioHang.get(i).setGiasp(GiaChiTiet * MainActivity.mangGioHang.get(i).getSoluongsp());
                            exists = true;
                        }
                    }
                    if(exists == false){
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        long Giamoi = soluong*GiaChiTiet;
                        MainActivity.mangGioHang.add(new com.example.tam.tb_food.model.Giohang(id , TenChitiet , Giamoi , HinhAnhChitiet , soluong));
                    }
                }else{
                    //móc giá trị số lượng trong spinner ra
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());

                    long Giamoi = soluong*GiaChiTiet;
                    MainActivity.mangGioHang.add(new com.example.tam.tb_food.model.Giohang(id,TenChitiet,Giamoi,HinhAnhChitiet,soluong));
                }
                //Chuyển sang màn hình Giohang
                Intent intent = new Intent(getApplicationContext() ,Giohang.class);
                startActivity(intent);
            }});}







    private void CatchEventSpinner() {

        Integer [] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this , android.R.layout.simple_spinner_dropdown_item, soluong);
        spinner.setAdapter(arrayAdapter);

    }

    private void GetInformation() {
        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("thongtinsanpham");
        id = sanpham.getID();
        TenChitiet = sanpham.getTensanpham();
        GiaChiTiet = sanpham.getGiasanpham();
        HinhAnhChitiet = sanpham.getHinhanhsanpham();
        MoTaChiTiet = sanpham.getMotasanpham();
        IdSanphamchitiet = sanpham.getIDSanpham();

        txttenchitiet.setText(TenChitiet);

        DecimalFormat decimal = new DecimalFormat("###,###,###");
        txtgiachitiet.setText("" + decimal.format(GiaChiTiet) + " Đ");

        txtmotachitiet.setText(MoTaChiTiet);

        Picasso.with(getApplicationContext()).load(HinhAnhChitiet)
                .placeholder(R.drawable.imagesno)
                .error(R.drawable.imageerror)
                .into(imgChitiet);




    }

    private void ActionBackToolbar() {
        setSupportActionBar(toolbarChitiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbarChitiet      = (Toolbar) findViewById(R.id.toolbarchitietsanpham);
        imgChitiet          = (ImageView) findViewById(R.id.imageviewchitietsanpham);
        txttenchitiet              = (TextView) findViewById(R.id.textviewtenchitietsanpham);
        txtgiachitiet              = (TextView) findViewById(R.id.textviewgiachitietsanpham);
        txtmotachitiet             = (TextView) findViewById(R.id.textviewmotachitietsanpham);
        spinner             = (Spinner) findViewById(R.id.spinner);
        btndatmuahang       = (Button) findViewById(R.id.buttondatmuahang);
    }
}
