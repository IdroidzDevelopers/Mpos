package com.app.mpos;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.*;
import com.ToxicBakery.viewpager.transforms.DepthPageTransformer;
import com.mpos.catalogue.view.CategoryFragment;
import com.mpos.catalogue.view.DiscountsFragment;
import com.mpos.catalogue.view.ItemFragment;
import com.mpos.catalogue.view.OtherChargesFragment;
import com.mpos.catalogue.view.SubCategoryFragment;

public class CatalogueActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogue);
        mButton=(Button)findViewById(R.id.drwaerbutton);



        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        //StackTransformer,FlipHorizontalTransformer,
        //viewPager.setPageTransformer(true, new StackTransformer());
        FragmentManager fm=getSupportFragmentManager();
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(),CatalogueActivity.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Toast.makeText(getApplicationContext(),"Catalogue",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(getApplicationContext(),"Sales",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(getApplicationContext(),SalesActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Toast.makeText(getApplicationContext(),"Transactions",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 5;
        private String tabTitles[] = new String[] { "ITEM", "CATEGORY", "SUB-CATEGORY", "DISCOUNT", "CHARGES" };
        private Context context;

        public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment=null;
            switch(position){
                case 0:{
                    fragment= new ItemFragment();
                    break;
                }
                case 1:{
                    fragment= new CategoryFragment();
                    break;
                }
                case 2:{
                    fragment= new SubCategoryFragment();
                    break;
                }
                case 3:{
                    fragment= new DiscountsFragment();
                    break;
                }
                case 4:{
                    fragment= new OtherChargesFragment();
                    break;
                }
                default:{
                    fragment= new SaleFragment();
                    break;
                }
            }
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            return tabTitles[position];
        }
    }
}
