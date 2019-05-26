package com.example.myapplication.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.FrameLayout;

import com.chaychan.library.BottomBarItem;
import com.chaychan.library.BottomBarLayout;
import com.example.myapplication.R;
import com.example.myapplication.ui.fragment.LibraryFragment;
import com.example.myapplication.ui.fragment.PendingFragment;
import com.example.myapplication.ui.fragment.reading.ReadingFragment;
import com.example.myapplication.ui.fragment.SearchFragment;

/**
 * @author Phuongcong
 * @description: Phuongcong
 * @date 2019
 */
public class MainActivity extends AppCompatActivity {

    private static Context thisContext;
    private FrameLayout mFlContent;
    private BottomBarLayout mBottomBarLayout;

    private int[] mNormalIconIds = new int[]{
            R.mipmap.tab_home_normal, R.mipmap.tab_video_normal,
            R.mipmap.tab_micro_normal, R.mipmap.tab_me_normal
    };

    private int[] mSelectedIconIds = new int[]{
            R.mipmap.tab_home_selected, R.mipmap.tab_video_selected,
            R.mipmap.tab_micro_selected, R.mipmap.tab_me_selected
    };

    private int[] mTitleIds = new int[]{
            R.string.tab_home,
            R.string.tab_video,
            R.string.tab_micro,
            R.string.tab_me
    };

    public static Context getMainContext() {
        return  thisContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_add_item);

        initView();
        initData();
        initListener();
        thisContext=getApplicationContext();
    }

    private void initView() {
        mFlContent = (FrameLayout) findViewById(R.id.fl_content);
        mBottomBarLayout = (BottomBarLayout) findViewById(R.id.bbl);
    }

    private void initData() {
        for (int i = 0; i < mTitleIds.length; i++) {
            BottomBarItem item = createBottomBarItem(i);
            mBottomBarLayout.addItem(item);
        }

       changeFragment(0); //默认显示第一页
       // setFragment(0);
    }

    @NonNull
    private TabFragment createFragment(int titleId) {
        TabFragment homeFragment = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TabFragment.CONTENT, getString(titleId));
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    private BottomBarItem createBottomBarItem(int i) {
        BottomBarItem item = new BottomBarItem.Builder(this)
                .titleTextSize(8)
                .titleNormalColor(R.color.tab_normal_color)
                .titleSelectedColor(R.color.tab_selected_color)
//              .openTouchBg(false)
//              .marginTop(5)
//              .itemPadding(5)
//              .unreadNumThreshold(99)
//              .unreadTextColor(R.color.white)

                //还有很多属性，详情请查看Builder里面的方法
                //There are still many properties, please see the methods in the Builder for details.
                .create(mNormalIconIds[i], mSelectedIconIds[i], getString(mTitleIds[i]));
        return item;
    }

    private void initListener() {
        mBottomBarLayout.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final BottomBarItem bottomBarItem, int previousPosition, final int currentPosition) {
                Log.i("MainActivity", "position: " + currentPosition);

                changeFragment(currentPosition);
            }
        });
    }

   /* private void changeFragment(int currentPosition) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_content, mFragmentList.get(currentPosition));
        transaction.commit();
    }*/

    private void changeFragment(int postion) {
        Fragment fragment= null;
        switch (postion){
            case 0:
                fragment =  ReadingFragment.newInstance();
                break;
            case 1:
                fragment =  LibraryFragment.newInstance();
                break;
            case 2:
                fragment =  PendingFragment.newInstance();
                break;
            case 3:
                fragment =  SearchFragment.newInstance();
                break;

        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_content, fragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dynamic, menu);
        return true;
    }

}
