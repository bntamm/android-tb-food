package com.example.tam.tb_food.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tam.tb_food.R;
import com.example.tam.tb_food.adapter.ComAdapter;
import com.example.tam.tb_food.model.Sanpham;
import com.example.tam.tb_food.util.CheckConection;
import com.example.tam.tb_food.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ComActivity extends AppCompatActivity {
    Toolbar toolbarCom;
    ListView lvCom;
    ArrayList<Sanpham> mangCom;
    ComAdapter comAdapter;

    int idcom = 0;
    int page = 1;

    View footerView;
    boolean isLoading = false;
    boolean limitData = false;
    mHandler mhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com);
        AnhXa();


        if(CheckConection.haveNetworkConnection(getApplicationContext())){
            ActionBackToolbar();
            GetIDLoaisp();
            GetDataCom(page);
            LoadMoreData();

        }else{
            CheckConection.ShowToast_Sort(getApplicationContext() , "check your internet!");
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menugiohang :
                Intent intent = new Intent(getApplicationContext() , com.example.tam.tb_food.activity.Giohang.class);
                startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart , menu);

        return true;
    }

    private void LoadMoreData() {

        lvCom.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(getApplicationContext(), ChiTietSanPham.class);
                intent.putExtra("thongtinsanpham" , mangCom.get(i));
                startActivity(intent);
            }
        });

        lvCom.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
            @Override
            public void onScroll(AbsListView view, int firstItem, int visibleItem, int totalItem) {

                if(firstItem + visibleItem == totalItem && totalItem != 0 && isLoading == false && limitData == false){
                    isLoading = true;
                    ThreadData threadData = new ThreadData();
                    threadData.start();
                }
            }});}

    public class mHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    lvCom.addFooterView(footerView);
                    break;
                case 1:
                    GetDataCom(++page);
                    isLoading = false;
                    break;}
            super.handleMessage(msg);}}

    public class ThreadData extends Thread{
        @Override
        public void run() {
            mhandler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();}

            Message message = mhandler.obtainMessage(1);
            mhandler.sendMessage(message);
            super.run();
        }}

    private void GetDataCom(int page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan = Server.Duongdancom + String.valueOf(page);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int id = 0;
                        String Tencom = "";
                        int Giacom  = 0;
                        String Hinhanhcom  = "";
                        String Motacom  = "";
                        int Idspcom  = 0;

                        if(response != null&& response.length() != 2){
                            lvCom.removeFooterView(footerView);
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                for(int i = 0 ; i< jsonArray.length(); i++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    id = jsonObject.getInt("id");
                                    Tencom = jsonObject.getString("tensp");
                                    Giacom = jsonObject.getInt("giasp");
                                    Hinhanhcom = jsonObject.getString("hinhanhsp");
                                    Motacom = jsonObject.getString("motasp");
                                    Idspcom = jsonObject.getInt("idsanpham");

                                    mangCom.add(new Sanpham(id, Tencom, Giacom, Hinhanhcom, Motacom, Idspcom));
                                    comAdapter.notifyDataSetChanged();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else{
                            limitData = true;
                            lvCom.removeFooterView(footerView);
                            CheckConection.ShowToast_Sort(getApplicationContext() , "Done!");
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<String , String>();
                params.put("idsanpham" , String.valueOf(idcom));
                return params;
            }
        };

        requestQueue.add(stringRequest);


    }

    private void GetIDLoaisp() {
        idcom = getIntent().getIntExtra("idloaisanpham" , -1);
    }

    private void ActionBackToolbar() {
        setSupportActionBar(toolbarCom);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarCom.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbarCom = (Toolbar) findViewById(R.id.toolbarCom);
        lvCom = (ListView) findViewById(R.id.listviewCom);

        mangCom = new ArrayList<>();
        comAdapter = new ComAdapter(getApplicationContext() , mangCom);
        lvCom.setAdapter(comAdapter);

        LayoutInflater ìnlater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerView = ìnlater.inflate(R.layout.progressbar , null);
        mhandler = new mHandler();
    }
}
