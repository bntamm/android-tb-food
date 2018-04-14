package com.example.tam.tb_food.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tam.tb_food.R;
import com.example.tam.tb_food.util.CheckConection;
import com.example.tam.tb_food.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThongTinThanhToanKhachHang extends AppCompatActivity {
    EditText edttenkhachhang, edtsodienthoai, edtdiachi;
    Button btnxannhan, btntrove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_thanh_toan_khach_hang);
        AnhXa();

        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if(CheckConection.haveNetworkConnection(getApplicationContext())){
            EventClickButton();
        }else{
            CheckConection.ShowToast_Sort(getApplicationContext() , "Check your internet!");
        }
    }

    private void EventClickButton() {
        btnxannhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ten = edttenkhachhang.getText().toString().trim();
                final String sodt = edtsodienthoai.getText().toString().trim();
                final String email = edtdiachi.getText().toString().trim();


                if(ten.length() > 0 && sodt.length() > 0 && email.length() >0){
                    final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.DuongdanDonhang,

                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(final String madonhang) {
                                    Log.d("madonhang", madonhang);

                                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                    StringRequest request = new StringRequest(Request.Method.POST,
                                            Server.DuongdanChitietdonhang, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            if (response.equals("1")) {
                                                MainActivity.mangGioHang.clear();
                                                CheckConection.ShowToast_Sort(getApplicationContext(),
                                                        "You have successfully added your shopping cart data. ");
                                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                startActivity(intent);
                                                CheckConection.ShowToast_Sort(getApplicationContext(),
                                                        "Please continue to purchase. ");
                                            } else {
                                                CheckConection.ShowToast_Sort(getApplicationContext(),
                                                        "Your cart data has been corrupted. ");
                                            }
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                        }
                                    }) {
                                        @Override
                                        protected Map<String, String> getParams() throws AuthFailureError {
                                            JSONArray jsonArray = new JSONArray();
                                            for (int i = 0; i < MainActivity.mangGioHang.size(); i++) {
                                                JSONObject jsonObject = new JSONObject();
                                                try {
                                                    jsonObject.put("madonhang", madonhang);
                                                    jsonObject.put("masanpham", MainActivity.mangGioHang.get(i).getIdsp());
                                                    jsonObject.put("tensanpham", MainActivity.mangGioHang.get(i).getTensp());
                                                    jsonObject.put("giasanpham", MainActivity.mangGioHang.get(i).getGiasp());
                                                    jsonObject.put("soluongsanpham", MainActivity.mangGioHang.get(i).getSoluongsp());

                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                                jsonArray.put(jsonObject);
                                            }
                                            HashMap<String, String> hashMap = new HashMap<String, String>();
                                            hashMap.put("json", jsonArray.toString());
                                            return hashMap;
                                        }
                                    };
                                    queue.add(request);



                                }},
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                }}){

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String , String> hashMap = new HashMap<String , String>();
                            hashMap.put("tenkhachhang" , ten);
                            hashMap.put("sodienthoai" , sodt);
                            hashMap.put("diachi" , email);
                            return hashMap;
                        }};
                    requestQueue.add(stringRequest);


                }else{
                    CheckConection.ShowToast_Sort(getApplicationContext() , "Kiểm tra và nhập đủ thông tin!");
                }}});


    }


    private void AnhXa() {
        edttenkhachhang = (EditText) findViewById(R.id.edittextTenkhachhang);
        edtdiachi = (EditText) findViewById(R.id.edittextDiachikhachhang);
        edtsodienthoai = (EditText) findViewById(R.id.edittextSodienthoai);

        btnxannhan = (Button) findViewById(R.id.buttonxacnhan);
        btntrove = (Button) findViewById(R.id.buttontrove);
    }
}
