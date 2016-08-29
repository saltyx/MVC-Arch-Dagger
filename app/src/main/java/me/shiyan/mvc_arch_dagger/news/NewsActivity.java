package me.shiyan.mvc_arch_dagger.news;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import javax.inject.Inject;

import me.shiyan.mvc_arch_dagger.NewsApplication;
import me.shiyan.mvc_arch_dagger.R;
import me.shiyan.mvc_arch_dagger.utils.ActivityUtils;
import me.shiyan.mvc_arch_dagger.utils.Category;
import me.shiyan.mvc_arch_dagger.utils.Device;
import me.shiyan.mvc_arch_dagger.utils.Log;

public class NewsActivity extends AppCompatActivity {

    private static final String CURRENT_DEVICE = "CURRENT_DEVICE";

    private DrawerLayout mDrawerLayout;

    private Log mLog = new Log(getClass().getName());

    @Inject NewsPresenter mPresenter;

    Device mCurrentChoosedDevice = Device.ANDROID;//默认设置加载为所有

    Category mCurrentCategory = Category.TOUR_NEWS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_act);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);

        NewsFragment newsFragment = (NewsFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (newsFragment == null){
            newsFragment = NewsFragment.newInstance(mCurrentCategory,mCurrentChoosedDevice);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    newsFragment,R.id.contentFrame);
        }

        DaggerNewsComponent.builder().newsPresenterModule(new NewsPresenterModule(newsFragment,mCurrentChoosedDevice))
                .dataRepoComponent( ((NewsApplication)getApplication())
                        .getTasksRepositoryComponent()).build()
                .inject(NewsActivity.this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.list_item_all:
                        mCurrentChoosedDevice = Device.ALL;
                        break;
                    case R.id.list_item_android:
                        mCurrentChoosedDevice = Device.ANDROID;
                        break;
                    case R.id.list_item_ipad:
                        mCurrentChoosedDevice = Device.IPAD;
                        break;
                    case R.id.list_item_iphone:
                        mCurrentChoosedDevice = Device.IPHONE;
                        break;
                    case R.id.list_item_pc:
                        mCurrentChoosedDevice = Device.PC;
                        break;
                }
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }
}
