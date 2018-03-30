package com.besieged.ktreader.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.besieged.ktreader.BaseActivity;
import com.besieged.ktreader.R;
import com.besieged.ktreader.factory.FragmentFactory;
import com.besieged.ktreader.ui.fragment.ZhihuFragment;
import com.besieged.ktreader.utils.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fragment_main)
    FrameLayout fragmentMain;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    private void initView(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_zhihu);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(this);
        navView.setCheckedItem(R.id.nav_zhihu);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_main, new ZhihuFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                CommonUtil.ShowTips(MainActivity.this, "再点一次，退出");
                exitTime = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        int id = item.getItemId();
        int groupId = item.getGroupId();
        if (groupId == R.id.nav_group_fragment) {
            fragmentTransaction.replace(R.id.fragment_main, FragmentFactory.getInstance().getFragment(id)).commit();
        }
        switch (id) {
            case R.id.nav_zhihu :
                toolbar.setTitle(R.string.title_zhihu);
                break;
            case R.id.nav_douban :
                toolbar.setTitle(R.string.title_douban);
                break;
            case R.id.nav_qiwen :
                toolbar.setTitle(R.string.title_qiwen);
                break;
            case R.id.nav_tupian :
                toolbar.setTitle(R.string.title_tupian);
                break;
            case R.id.nav_history :
//                startActivity(HistoryActivity.class, false);
                break;
            case R.id.nav_save :
//                startActivity(CollectActivity.class, false);
                break;
            case R.id.nav_setting :
//                startActivity(SettingsActivity.class, false);
                break;
            case R.id.nav_about :
//                startActivity(AboutActivity.class, false);
                break;
            default:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
