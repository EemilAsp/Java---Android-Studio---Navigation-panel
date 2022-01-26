package com.example.navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;
import java.util.Locale;
import java.util.zip.Inflater;

public class MainActivity<username> extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    String value, text;
    asetukset asetus = new asetukset("roboto", "36", "Black", "normal", "English", "true");
    String username, language;
    TextView textView;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        textView = findViewById(R.id.Username);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        View header = LayoutInflater.from(this).inflate(R.layout.nav_header, null);
        navigationView.addHeaderView(header);

        View Head = navigationView.getHeaderView(0);

        textView = (TextView)header.findViewById(R.id.headerv);
        textView.setText("Olio-ohjelmointi viikon 11 tehtävät");
        }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch(item.getItemId()){
            case R.id.nav_settings:
                intent = new Intent(MainActivity.this, Settings.class);
                intent.putExtra("asetukset", asetus);
                startActivityForResult(intent, 1);
                break;
            case R.id.edit_text:
                intent = new Intent(MainActivity.this, Text.class);
                intent.putExtra("asetukset", asetus);
                intent.putExtra("txt", text);
                startActivityForResult(intent, 1);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                asetus = (asetukset) data.getSerializableExtra("asetukset");
                username = data.getStringExtra("user");
                System.out.println(asetus.getBold());
                System.out.println(asetus.getChoice());
                System.out.println(asetus.getName());
                System.out.println(asetus.getSize());
                System.out.println(asetus.getColor());
                System.out.println(asetus.getLanguage());
                setLocale(this, asetus.getLanguage());
                testText();
            }
            if (resultCode == RESULT_FIRST_USER){
                text = data.getStringExtra("txt");
            }
        }
    }

    private void testText() {
        if (username == null){
        }else{
            textView.setText(username);
        }
    }

    public void setLocale(Activity activity, String language) {
        String languageCode = null;
        if(language.equals("Finnish") || language.equals("Suomi") || language.equals("Finska")){
            languageCode = "fi";
        }

        if(language.equals("English") || language.equals("Englanti") || language.equals("Engelsk")){
            languageCode = "en";
        }

        if(language.equals("Swedish") || language.equals("Ruotsi") || language.equals("Svenska")){
            languageCode = "sv";
        }
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

}