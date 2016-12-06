package com.example.zerox.labtop;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.zerox.labtop.ContentProvider.LaptopContract;
import com.example.zerox.labtop.ContentProvider.LaptopDBHelper;
import com.example.zerox.labtop.Model.Laptop;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int COL_ID = 0;
    public static final int COL_Laptop_ID = 1;
    public static final int COL_TITLE = 2;
    public static final int COL_URL = 3;
    public static final int COL_description = 4;
    public static final int COL_price = 5;
    private static final String[] COLUMNS = {
            LaptopContract.LaptopEntry._ID,
            LaptopContract.LaptopEntry.COLUMN_Laptop_ID,
            LaptopContract.LaptopEntry.COLUMN_Laptop_Title,
            LaptopContract.LaptopEntry.COLUMN_Laptop_Url,
            LaptopContract.LaptopEntry.COLUMN_Laptop_description,
            LaptopContract.LaptopEntry.COLUMN_Laptop_price

    };
    public static List<Laptop> list;
    ViewPager viewPager;
    TabLayout tabs;
    LaptopDBHelper laptopDBHelper;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setting ViewPager for each Tabs
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Set Tabs inside Toolbar
        tabs = (TabLayout) findViewById(R.id.tabs);
        laptopDBHelper = new LaptopDBHelper(getBaseContext());
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

// Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

// Set behavior of Navigation drawer
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    // This method will trigger on item Click of navigation menu
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Set item in checked state
                        menuItem.setChecked(true);
                        int id = menuItem.getItemId();
                        // TODO: handle navigation
                        if (id == R.id.navHome) {

                            getData();


                        }

                        if (id == R.id.navFav) {

                            fetchFavorites();

                        }
                        if (id == R.id.nav_facebook) {

                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/OptimizerGado"));
                            Intent browserChooserIntent = Intent.createChooser(browserIntent, "Choose browser of your choice");
                            startActivity(browserChooserIntent);
                        }

                        // Closing drawer on item click
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });


    }

    private void getData() {
        if (Utility.isConnected(getBaseContext()) == true) {
            final ProgressDialog pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Please wait data is Processing");
            pd.show();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference();
            DatabaseReference laptopRefrence = reference.child("laps");
            laptopRefrence.keepSynced(true);
            laptopRefrence.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    list = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Laptop laptop = snapshot.getValue(Laptop.class);
                        list.add(laptop);


                    }
                    Log.e("", "onDataChange: ");
                    setupViewPager(viewPager);
                    tabs.setupWithViewPager(viewPager);
                    pd.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("", "fail: ");
                }

            });
        } else {
            Toast.makeText(getBaseContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            fetchFavorites();
        }


    }

    private void fetchFavorites() {
        list = null;
        FetchFavorites fetchFavorites = new FetchFavorites(getBaseContext());
        fetchFavorites.execute();

        setupViewPager(viewPager);
        tabs.setupWithViewPager(viewPager);


    }


    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new ListContentFragment(), "List");
        adapter.addFragment(new TileContentFragment(), "Tile");
        adapter.addFragment(new CardContentFragment(), "Card");
        viewPager.setAdapter(adapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        getData();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);


        }


        return super.onOptionsItemSelected(item);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public class FetchFavorites extends AsyncTask<Void, Void, List<Laptop>> {
        private Context mContext;

        public FetchFavorites(Context context) {
            mContext = context;
        }

        @Override
        protected List<Laptop> doInBackground(Void... voids) {
            Cursor cursor = mContext.getContentResolver().query(
                    LaptopContract.LaptopEntry.CONTENT_URI,
                    COLUMNS,
                    null,
                    null,
                    null
            );
            List<Laptop> results = new ArrayList<>();
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Laptop Laptop = new Laptop(cursor);
                    results.add(Laptop);
                } while (cursor.moveToNext());
                cursor.close();
            }
            return results;
        }

        protected void onPostExecute(List<Laptop> favourite_list) {

            if (favourite_list != null) {
                list = favourite_list;
            }

        }
    }
}
