package com.example.tam.tb_food.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tam.tb_food.R;
import com.example.tam.tb_food.adapter.LoaispAdapter;
import com.example.tam.tb_food.adapter.SanphamAdapter;
import com.example.tam.tb_food.model.Giohang;
import com.example.tam.tb_food.model.Loaisp;
import com.example.tam.tb_food.model.Sanpham;
import com.example.tam.tb_food.util.CheckConection;
import com.example.tam.tb_food.util.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.tam.tb_food.R.id.buttonGoogle;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewmanhinhchinh;
    NavigationView navigationView;
    ListView listViewmanhinhchinh;
    DrawerLayout drawerLayout;
    Button btnGoogle, btnInfo;

    ArrayList<Loaisp> mangloaisp;
    LoaispAdapter loaispAdapter;

    ArrayList<Sanpham> mangsanpham;
    SanphamAdapter sanphamAdapter;

    


    public static ArrayList<Giohang> mangGioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            AnhXa();

        CatchEventButonGoogle();
        CatchEventButonInfo();



        if(CheckConection.haveNetworkConnection(getApplicationContext())){
            ActionBar();
            ActionViewFlipper();
            GetDuLieuLoaisp();
            GetDuLieuSPMoiNhat();
            BatSuKienMenuListView();

        }else{
            CheckConection.ShowToast_Sort(getApplicationContext(), "Check your internet!");
            finish();
        }

        
    }

    private void CatchEventButonInfo() {
            btnInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowMenu();
                }
            });


    }

    private void ShowMenu(){
        PopupMenu popup = new PopupMenu(this , btnInfo);
        popup.getMenuInflater().inflate(R.menu.popup_menu_info , popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent();
                switch(item.getItemId()){
                    case R.id.menuFacebook:
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("https://www.facebook.com/tam.bn.tt"));
                        startActivity(intent);
                        break;

                    case R.id.menuGithub:
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("https://github.com/bntamm"));
                        startActivity(intent);
                        break;

                    case R.id.menuLinkedin:
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("www.linkedin.com/in/tam-bui-301194"));
                        startActivity(intent);
                        break;
                }


                return false;
            }
        });

        popup.show();
    }

    private void CatchEventButonGoogle() {
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://google.com"));
                startActivity(intent);
            }
        });
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

    private void BatSuKienMenuListView() {
        listViewmanhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                switch(i){
                    case 0:
                        if(CheckConection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this , MainActivity.class);
                            startActivity(intent);
                        }else{
                            CheckConection.ShowToast_Sort(getApplication() , "Check your internet!");

                        }

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case 1:
                        if(CheckConection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this , MonNuongActivity.class);
                            intent.putExtra("idloaisanpham" , mangloaisp.get(i).getId());
                            startActivity(intent);
                        }else{
                            CheckConection.ShowToast_Sort(getApplication() , "Check your internet!");

                        }

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case 2:
                        if(CheckConection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this , LauActivity.class);
                            intent.putExtra("idloaisanpham" , mangloaisp.get(i).getId());
                            startActivity(intent);
                        }else{
                            CheckConection.ShowToast_Sort(getApplication() , "Check your internet!");

                        }

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if(CheckConection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this , MonXaoActivity.class);
                            intent.putExtra("idloaisanpham" , mangloaisp.get(i).getId());
                            startActivity(intent);
                        }else{
                            CheckConection.ShowToast_Sort(getApplication() , "Check your internet!");

                        }

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if(CheckConection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this , MonChienActivity.class);
                            intent.putExtra("idloaisanpham" , mangloaisp.get(i).getId());
                            startActivity(intent);
                        }else{
                            CheckConection.ShowToast_Sort(getApplication() , "Check your internet!");

                        }

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 5:
                        if(CheckConection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this , ComActivity.class);
                            intent.putExtra("idloaisanpham" , mangloaisp.get(i).getId());
                            startActivity(intent);
                        }else{
                            CheckConection.ShowToast_Sort(getApplication() , "Check your internet!");

                        }

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 6:
                        if(CheckConection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this , GoiActivity.class);
                            intent.putExtra("idloaisanpham" , mangloaisp.get(i).getId());
                            startActivity(intent);
                        }else{
                            CheckConection.ShowToast_Sort(getApplication() , "Check your internet!");

                        }

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 7:
                        if(CheckConection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this , ThucUongActivity.class);
                            intent.putExtra("idloaisanpham" , mangloaisp.get(i).getId());
                            startActivity(intent);
                        }else{
                            CheckConection.ShowToast_Sort(getApplication() , "Check your internet!");

                        }

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 8:
                        if(CheckConection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this , ChaoActivity.class);
                            intent.putExtra("idloaisanpham" , mangloaisp.get(i).getId());
                            startActivity(intent);
                        }else{
                            CheckConection.ShowToast_Sort(getApplication() , "Check your internet!");

                        }

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 9:
                        if(CheckConection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this , MonHapActivity.class);
                            intent.putExtra("idloaisanpham" , mangloaisp.get(i).getId());
                            startActivity(intent);
                        }else{
                            CheckConection.ShowToast_Sort(getApplication() , "Check your internet!");

                        }

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 10:
                        if(CheckConection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this , MonKhoActivity.class);
                            intent.putExtra("idloaisanpham" , mangloaisp.get(i).getId());
                            startActivity(intent);
                        }else{
                            CheckConection.ShowToast_Sort(getApplication() , "Check your internet!");

                        }

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 11:
                        if(CheckConection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this , BanhActivity.class);
                            intent.putExtra("idloaisanpham" , mangloaisp.get(i).getId());
                            startActivity(intent);
                        }else{
                            CheckConection.ShowToast_Sort(getApplication() , "Check your internet!");

                        }

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case 12:
                        if(CheckConection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this , LienHeActivity.class);
                            startActivity(intent);
                        }else{
                            CheckConection.ShowToast_Sort(getApplication() , "Check your internet!");

                        }

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 13:
                        if(CheckConection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this , ThongTinActivity.class);
                            startActivity(intent);
                        }else{
                            CheckConection.ShowToast_Sort(getApplication() , "Check your internet!");

                        }

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                }

            }
        });


    }

    private void GetDuLieuSPMoiNhat() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.Duongdansanphammoinhat,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response != null){
                            int ID = 0;
                            String Tensanpham = "";
                            Integer Giasanpham = 0;
                            String Hinhanhsanpham ="";
                            String Motasanpham = "";
                            int IDsanpham = 0;

                            for(int i = 0 ; i < response.length() ; i++){

                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    ID              = jsonObject.getInt("id");
                                    Tensanpham      = jsonObject.getString("tensp");
                                    Giasanpham      = jsonObject.getInt("giasp");
                                    Hinhanhsanpham  = jsonObject.getString("hinhanhsp");
                                    Motasanpham     = jsonObject.getString("motasp");
                                    IDsanpham       = jsonObject.getInt("idsanpham");


                                    mangsanpham.add(new Sanpham (ID, Tensanpham ,Giasanpham ,Hinhanhsanpham , Motasanpham , IDsanpham));


                                    sanphamAdapter.notifyDataSetChanged();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }


                        }}},
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        CheckConection.ShowToast_Sort(getApplicationContext() , error.toString());
                    }});
        requestQueue.add(jsonArrayRequest);
    }

    private void GetDuLieuLoaisp() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.Duongdanloaisp,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response != null){
                            int id = 0;
                            String tenloaisp = "";
                            String hinhanhloaisp = "";

                            for(int i = 0 ; i < response.length() ; i++){

                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    id = jsonObject.getInt("id");
                                    tenloaisp = jsonObject.getString("tenloaisp");
                                    hinhanhloaisp = jsonObject.getString("hinhanhloaisp");


                                    mangloaisp.add(new Loaisp(id, tenloaisp , hinhanhloaisp));


                                    loaispAdapter.notifyDataSetChanged();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            mangloaisp.add(12 , new Loaisp(0 , "Liên Hệ" , "http://mayxaythitlamgio.com/wp-content/uploads/2016/01/Li%C3%AAn-h%E1%BB%87.png"));
                            mangloaisp.add(13 , new Loaisp(0 , "Thông Tin" ,"http://cdn1.iconfinder.com/data/icons/Pretty_office_icon_part_2/128/personal-information.png" ));
                        }}},
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        CheckConection.ShowToast_Sort(getApplicationContext() , error.toString());
                    }});
        requestQueue.add(jsonArrayRequest);
    }

    private void ActionViewFlipper() {
        ArrayList<String> mangQuangCao = new ArrayList<>();

        mangQuangCao.add("http://media.giadinhvietnam.com/files/news/2014/11/06/1499133512.jpg");
        mangQuangCao.add("http://elpis.edu.vn/Uploads/images/news/nhung-tinh-tu-co-ban-de-mieu-ta-do-an-trong-tieng-anh.jpg");
        mangQuangCao.add("https://photographer.vn/wp-content/uploads/2017/12/chup-anh-do-uong-4-800x533.jpg");
        mangQuangCao.add("http://img2.news.zing.vn/2013/02/23/a2-21.jpg");
        mangQuangCao.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTOC-HHUVuJfzHgABlS4wCIKvZSCLCqP-8JUiEVCwxyu01pAnAd2Q");
        mangQuangCao.add("https://2.bp.blogspot.com/-gpYysYsbXJs/V9FADJQnHpI/AAAAAAAABoY/IlDcob1Br803FZuAK5sU1Kl0Avum2eEVgCLcB/s1600/thuc-pham-oi-thiu-hu-hong-2.jpg");
        mangQuangCao.add("http://bizsplus.com/wp-content/uploads/2017/12/500x217x4-loai-do-uong-co-hai-cho-rang-mieng_01-720x312.jpg.pagespeed.ic.sJraG6ochp.jpg");
        mangQuangCao.add("http://thongtinhanquoc.com/wp-content/uploads/2014/11/tthq-muoi-kim-chi-324x160.jpg");

        for(int i = 0 ; i < mangQuangCao.size() ; i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mangQuangCao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            viewFlipper.addView(imageView);
        }

        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);

        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext() , R.anim.slide_is_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext() , R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);


    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_more);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    private void AnhXa() {
        toolbar = (Toolbar) findViewById(R.id.toolbarManHinhChinh);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        recyclerViewmanhinhchinh = (RecyclerView) findViewById(R.id.recyclerView);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        listViewmanhinhchinh = (ListView) findViewById(R.id.listviewManHinhChinh);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        btnGoogle = (Button) findViewById(buttonGoogle);
        btnInfo = (Button) findViewById(R.id.buttonInfo);

        mangloaisp = new ArrayList<>();
        mangloaisp.add(0 , new Loaisp(0 , "Trang Chính" , "http://www.rangdonghotel.com.vn/uploads/userfiles/image/home%20icon.png"));
        loaispAdapter = new LoaispAdapter(mangloaisp , getApplicationContext());
        listViewmanhinhchinh.setAdapter(loaispAdapter);


        mangsanpham = new ArrayList<>();
        sanphamAdapter = new SanphamAdapter(getApplicationContext() , mangsanpham);
        recyclerViewmanhinhchinh.setHasFixedSize(true);
        recyclerViewmanhinhchinh.setLayoutManager(new GridLayoutManager(getApplicationContext() , 3));
        recyclerViewmanhinhchinh.setAdapter(sanphamAdapter);


        if(mangGioHang != null){

        }else{
            mangGioHang = new ArrayList<>();
        }

    }


}
