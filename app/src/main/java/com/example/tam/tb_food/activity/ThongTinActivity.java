package com.example.tam.tb_food.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.tam.tb_food.R;

public class ThongTinActivity extends AppCompatActivity {
    Toolbar toolbarThongtin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin);
        AnhXa();
        ActionBackToolBar();

    }


    private void ActionBackToolBar() {
        setSupportActionBar(toolbarThongtin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarThongtin.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbarThongtin = (Toolbar) findViewById(R.id.toolbarThongTin);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart , menu);

        return true;
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
}
