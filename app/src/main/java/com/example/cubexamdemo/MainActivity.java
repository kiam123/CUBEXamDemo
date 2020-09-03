package com.example.cubexamdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ArrayList<Fragment> fragmentArrayList;
    private TabLayout mTabLayout;
    private List<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();
        initView();
    }

    public void initFragment() {
        fragmentArrayList = new ArrayList<Fragment>();
        fragmentArrayList.add(new Tab1Fragment());
        fragmentArrayList.add(new Tab2Fragment());
    }

    public void initView() {
        titles = new ArrayList<>();
        titles.add("TAB1");
        titles.add("TAB2");

        viewPager = (ViewPager) this.findViewById(R.id.viewPager);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager.setAdapter(new SimpleFragmentPagerAdapter(getSupportFragmentManager(), titles, fragmentArrayList, this));
        mTabLayout.setupWithViewPager(viewPager);
        ((Tab1Fragment)fragmentArrayList.get(0)).setViewPager(viewPager);
//        setupTabIcons();
//        initTabLayoutEvent();
    }

    //初始化tab
    private void setupTabIcons() {
        mTabLayout.getTabAt(0).setCustomView(getTabView(0));
        mTabLayout.getTabAt(1).setCustomView(getTabView(1));
    }

    //自定義tab
    public View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_tab, null);
        TextView txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_title.setText(titles.get(position));

        return view;
    }

    //設定滑動另一頁後的事件
    private void initTabLayoutEvent() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                changeTabSelect(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab){}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void changeTabSelect(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        TextView txt_title = (TextView) view.findViewById(R.id.txt_title);

        if (txt_title.getText().toString().equals("TAB1")) {
            viewPager.setCurrentItem(0);
        } else if (txt_title.getText().toString().equals("TAB2")) {
            viewPager.setCurrentItem(1);
        }
    }


    //設定viewpager的adapter
    class SimpleFragmentPagerAdapter extends FragmentStatePagerAdapter {
        Context mContext;
        private List<String> titles;
        ArrayList<Fragment> fragmentArrayList = new ArrayList<Fragment>();

        public SimpleFragmentPagerAdapter(FragmentManager fm,List<String> titles, ArrayList<Fragment> fragmentArrayList, Context context) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT );
            this.titles = titles;
            this.fragmentArrayList = fragmentArrayList;
            mContext = context;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
