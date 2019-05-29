package com.e_mobadara.audiomanaging;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.e_mobadara.Adapter.TabAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    ViewPager viewPager1;
    TabLayout tabLayout1;
    TabAdapter adapter;

    private static String langue = "AR"; // Par defaut
    public static String getLangue() {
        return langue;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getIntent().getExtras()!=null){langue=getIntent().getStringExtra("langue");}
        setupTablayout();
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        Log.d(TAG,"onCreateOptionsMenu");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void styleMenuButton(Menu menu) {
        int id = R.id.item1;
        if(langue.equals("AR")){id = R.id.item1;}
        else if (langue.equals("FR")){id = R.id.item2;}
        else if (langue.equals("TA")){id = R.id.item3;}
        else if (langue.equals("AN")){id = R.id.item4;}
        else if (langue.equals("DA")){id = R.id.item5;}
        // Find the menu item you want to style
        MenuItem item = menu.findItem(id);
        SpannableString spanString = new SpannableString(menu.findItem(id).getTitle().toString());
        spanString.setSpan(new ForegroundColorSpan(Color.RED), 0, spanString.length(), 0); //fix the color to white
        item.setTitle(spanString);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean result = super.onPrepareOptionsMenu(menu);
        styleMenuButton(menu);
        return result;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i1 = item.getItemId();// On peut créer le menu via le code
        if (i1 == R.id.item1) {
            Toast.makeText(this, "Langue 'arabe'",
                    Toast.LENGTH_LONG).show();
            langue = "AR";
            reloadActivity();

        } else if (i1 == R.id.item2) {
            Toast.makeText(this, "langue 'francais'",
                    Toast.LENGTH_LONG).show();
            langue = "FR";
            reloadActivity();

        } else if (i1 == R.id.item3) {
            Toast.makeText(this, "langue 'Tamazight'",
                    Toast.LENGTH_LONG).show();
            langue = "TA";
            reloadActivity();

        } else if (i1 == R.id.item4) {
            Toast.makeText(this, "Langue 'Anglais'",
                    Toast.LENGTH_LONG).show();
            langue = "AN";
            reloadActivity();

        } else if (i1 == R.id.item5) {
            Toast.makeText(this, "Langue 'Darija'",
                    Toast.LENGTH_LONG).show();
            langue = "DA";
            reloadActivity();

        } else if (i1 == android.R.id.home) {
            Intent i = new Intent(this,AudioSettingsActivity.class);
            startActivity(i);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupTablayout() {
        tabLayout1 = findViewById(R.id.tablayout1);
        viewPager1 = findViewById(R.id.viewpager1);
        tabLayout1.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout1.addTab(tabLayout1.newTab().setText("EXCELENT"));
        tabLayout1.addTab(tabLayout1.newTab().setText("GOOD"));
        tabLayout1.addTab(tabLayout1.newTab().setText("ENCOURAGEMENT"));

        adapter = new TabAdapter(getSupportFragmentManager(), tabLayout1.getTabCount());
        viewPager1.setAdapter(adapter);
        viewPager1.setOffscreenPageLimit(3);
        viewPager1.setCurrentItem(1);
        viewPager1.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout1));
        tabLayout1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager1.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
    void reloadActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("langue",langue);
        startActivity(intent);
        finish();
    }
}
