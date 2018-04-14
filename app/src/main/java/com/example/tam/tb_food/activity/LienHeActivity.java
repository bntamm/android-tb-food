package com.example.tam.tb_food.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.tam.tb_food.R;

public class LienHeActivity extends AppCompatActivity {
    Toolbar toolbarLienhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lien_he);
        AnhXa();
        ActionBackToolBar();

    }

    private void ActionBackToolBar() {
        setSupportActionBar(toolbarLienhe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarLienhe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbarLienhe = (Toolbar) findViewById(R.id.toolbarLienHe);
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
