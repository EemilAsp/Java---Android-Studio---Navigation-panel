package com.example.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class Settings extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Button deny, access, fontsize;
    Boolean choice;
    Spinner colors, fonttype, bolds, languages;
    String color, font, size, username, language;
    String bolding = "normal";
    Context context;
    SeekBar sb;
    asetukset asetus;
    EditText ed;
    String data;
    Resources resources;
    Intent intent;
    
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        asetus = (asetukset) getIntent().getSerializableExtra("asetukset");
        setContentView(R.layout.fragment_settings);
        Button fontsize = findViewById(R.id.fontsize);
        Button deny = findViewById(R.id.denyEdit);
        Button allow = findViewById(R.id.allowEdit);
        Spinner colors = findViewById(R.id.spinner);
        Spinner fonttype = findViewById(R.id.fonts);
        Spinner bolds = findViewById(R.id.bolds);
        Spinner languages = findViewById(R.id.lang);
        sb = (SeekBar)findViewById(R.id.seekBar);
        sb.setProgress(8);
        ed = findViewById(R.id.editTextTextPersonName2);
        context = Settings.this;
        ArrayAdapter<CharSequence> coloradapter = ArrayAdapter.createFromResource(this, R.array.font_colors, android.R.layout.simple_spinner_item);
        coloradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colors.setAdapter(coloradapter);

        ArrayAdapter<CharSequence> fontadapter = ArrayAdapter.createFromResource(this, R.array.Font, android.R.layout.simple_spinner_item);
        fontadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fonttype.setAdapter(fontadapter);

        ArrayAdapter<CharSequence> Typeadapter = ArrayAdapter.createFromResource(this, R.array.Type, android.R.layout.simple_spinner_item);
        Typeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bolds.setAdapter(Typeadapter);

        ArrayAdapter<CharSequence> langadapter = ArrayAdapter.createFromResource(this, R.array.Languages, android.R.layout.simple_spinner_item);
        langadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languages.setAdapter(langadapter);

        languages.setOnItemSelectedListener(this);
        bolds.setOnItemSelectedListener(this);
        fonttype.setOnItemSelectedListener(this);
        colors.setOnItemSelectedListener(this);
        deny.setOnClickListener(this);
        allow.setOnClickListener(this);


        ed.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    username = ed.getText().toString();
                    return true;
                }
                return false;
            }
        });

        fontsize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size = fontsize.getText().toString();
                asetus.setSize(size);
            }
        });


        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                fontsize.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        colors = (Spinner)parent;
        fonttype = (Spinner)parent;
        bolds = (Spinner)parent;
        languages = (Spinner)parent;
        if(colors.getId() == R.id.spinner){
         if (parent.getItemAtPosition(position).toString().isEmpty()){
             color = "black";
         }else{
             color = parent.getItemAtPosition(position).toString();
         }

        }
        if(fonttype.getId() == R.id.fonts){
            if(parent.getItemAtPosition(position).toString() == null){
                font = "roboto";
            }else{
                font = parent.getItemAtPosition(position).toString();
            }
        }
        if(bolds.getId() == R.id.bolds){
            if(parent.getItemAtPosition(position).toString()== null){
                bolding = "normal";
            }else {
                bolding = parent.getItemAtPosition(position).toString();
            }

        }
        if(languages.getId() == R.id.lang){
            language = parent.getItemAtPosition(position).toString();
            if(language.equals("Finnish") || language.equals("Suomi") || language.equals("Finska")){
                setLocale(this, "fi");
            }

            if(language.equals("English") || language.equals("Englanti") || language.equals("Engelsk")){
                setLocale(this, "en");
                }

            if(language.equals("Swedish") || language.equals("Ruotsi") || language.equals("Svenska")){
                setLocale(this, "sv");
                }
            }
        }




    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.allowEdit:
                choice = true;
                data = Boolean.toString(choice);
                break;
            case R.id.denyEdit:
                choice = false;
                data = Boolean.toString(choice);
                break;
            default:
                break;
        }
        asetus.setChoice(data);
        intent = getIntent();
        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    public void setLocale(Activity activity, String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());

    }

    public Intent getIntent(){
        Intent intent = new Intent();
        asetukset asetus = new asetukset(font, size, color, bolding, language, data);
        intent.putExtra("asetukset", asetus);
        intent.putExtra("user", username);
        setResult(RESULT_OK, intent);
        return intent;
    }

}
