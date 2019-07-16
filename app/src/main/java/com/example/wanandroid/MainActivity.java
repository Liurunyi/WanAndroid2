package com.example.wanandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.wanandroid.base.BaseActivity;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.home.HomeFragment;
import com.example.wanandroid.login.LoginRegisterActivity;
import com.example.wanandroid.wechat.WechatFragment;
import com.example.wanandroid.widge.view.ButtonNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private NavigationView nav_view;
    private DrawerLayout drawer_layout;
    private ButtonNavigationView mButtonNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();

        addFragment(getSupportFragmentManager(), HomeFragment.class, R.id.main_fragment_container, null);
        toolbar.setTitle("首页");

        mButtonNavigationView = findViewById(R.id.main_bottom_navigation);


        mButtonNavigationView.setOnTabChangedListener(new ButtonNavigationView.OnTabCheckedChangedListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                int id = buttonView.getId();
                Class<? extends BaseFragment> aClass = null;
                switch (id){
                    case R.id.main_button_tab_home:{
                        aClass = HomeFragment.class;
                        toolbar.setTitle("首页");
                        break;
                    }
                    /*case  R.id.main_button_tab_knowledge:{

                        break;
                    }*/
                    case R.id.main_button_tab_wechat:{
                        toolbar.setTitle("公众号");
                        aClass = WechatFragment.class;
                        break;
                    }
                }
                addFragment(getSupportFragmentManager(),aClass, R.id.main_fragment_container, null);
            }
        });

    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if(!WAApplication.mIsLogin){
            startActivity(new Intent(this, LoginRegisterActivity.class));
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        nav_view = (NavigationView) findViewById(R.id.nav_view);

        fab.setOnClickListener(this);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            case R.id.nav_view:

                break;
        }
    }
}
