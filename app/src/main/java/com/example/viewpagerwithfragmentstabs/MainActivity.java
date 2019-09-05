package com.example.viewpagerwithfragmentstabs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.viewpagerwithfragmentstabs.Adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tlMain;
    ViewPager vpMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tlMain=findViewById(R.id.tlMain);
        vpMain=findViewById(R.id.vpMain);

        vpMain.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        vpMain.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlMain));
        getSupportActionBar().setTitle("Save Category");
        tlMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                vpMain.setCurrentItem(tab.getPosition());
                String title="";
                switch (tab.getPosition())
                {
                    case 0:
                        title="Save Category";
                        break;
                    case 1:
                        title="Save Game";
                        break;
                    case 2:
                        title="Game List";
                }

            getSupportActionBar().setTitle(title);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
