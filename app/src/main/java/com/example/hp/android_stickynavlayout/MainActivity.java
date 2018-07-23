package com.example.hp.android_stickynavlayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.hp.android_stickynavlayout.custom.SimpleViewPagerIndicator;
import com.example.hp.android_stickynavlayout.custom.StickNavLayout;
import com.example.hp.android_stickynavlayout.util.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SimpleViewPagerIndicator.IndicatorClickListener, StickNavLayout.MyStickyListener{

    public static final String UID = "UID";
    public static final String[] titles = new String[]{"单曲","专辑","MV","歌手信息"};

    @Bind(R.id.id_stickynavlayout)
    StickNavLayout mStickNavLayout;

    @Bind(R.id.id_stickynavlayout_avatar)
    ImageView iv_avatar;
    @Bind(R.id.id_stickynavlayout_indicator)
    SimpleViewPagerIndicator mIndicator;
    @Bind(R.id.id_stickynavlayout_viewpager)
    ViewPager mViewPager;
    private TabFragmentPagerAdapter mAdapter;

    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if(Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            //int option = View.SYSTEM_UI_FLAG_VISIBLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            int option = View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
//            getWindow().setStatusBarColor(Color.parseColor("#9C27B0"));
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        initView();
        initData();
    }

    protected void initData() {
    }

    protected void initView() {
        mIndicator.setIndicatorClickListener(this);
        mIndicator.setTitles(titles);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mIndicator.scroll(position,positionOffset);
            }
            @Override
            public void onPageSelected(int position) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        for(int i=0;i<titles.length;i++){
            mFragments.add(ADetailSongFragment.newInstance());
        }
        mAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager(),mFragments);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);

        mStickNavLayout.setScrollListener(this);

        int height = DisplayUtil.getScreenHeight(MainActivity.this)-DisplayUtil.dip2px(MainActivity.this,65)-DisplayUtil.dip2px(MainActivity.this,40);
        LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) mViewPager.getLayoutParams();
        layoutParams.height = height;
        mViewPager.setLayoutParams(layoutParams);
    }

    public static void toArtistDetailActivity(Context context, String uid){
        Intent intent = new Intent(context,MainActivity.class);
        intent.putExtra(UID,uid);
        context.startActivity(intent);
    }

    @Override
    public void onClickItem(int k) {
        mViewPager.setCurrentItem(k);
    }


    private float getMobileWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        return width;
    }

    @Override
    public void imageScale(float bottom) {
        float height = DisplayUtil.dip2px(MainActivity.this,220);
        float mScale = bottom/height;
        float width = getMobileWidth()*mScale;
        float dx = (width-getMobileWidth())/2;
        iv_avatar.layout((int)(0-dx),0,(int)(getMobileWidth()+dx),(int)bottom);
    }
}
