package com.example.navigation;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.io.Serializable;
import java.util.Locale;

public class Text extends AppCompatActivity {
        TextView tv;
        EditText ed;
        Button back;
        String text, fontlocation, color, size, language;
        asetukset asetus;
    Intent intent;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_text);

        tv = findViewById(R.id.textView);
        ed = findViewById(R.id.editTextTextPersonName);
        back = findViewById(R.id.backButton);

        Boolean choice = Boolean.parseBoolean((getIntent().getStringExtra("key")));
        asetus = (asetukset) getIntent().getSerializableExtra("asetukset");
        text = ((getIntent().getStringExtra("txt")));
        setLocale(this, asetus.getLanguage());
        checkFont();
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/"+fontlocation);
        tv.setTypeface(type);
        checkColor();
        size = asetus.getSize();
        tv.setTextSize(Float.parseFloat(size));
        if (asetus.getChoice().equals("false")){
            testText();
            ed.setFocusable(false);
        }else{
            ed.setFocusableInTouchMode(true);
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = ed.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("txt", text);
                setResult(RESULT_FIRST_USER, intent);
                finish();
            }
        });
    }

    private void checkColor() {
        if(asetus.getColor().equals("Red")) {tv.setTextColor(Color.RED);}
        if(asetus.getColor().equals("Blue")) {tv.setTextColor(Color.BLUE);}
        if(asetus.getColor().equals("Purple")) {tv.setTextColor(getResources().getColor(R.color.purple));}
        if(asetus.getColor().equals("Black")) {tv.setTextColor(Color.BLACK);}
        if(asetus.getColor().equals("Yellow")) {tv.setTextColor(Color.YELLOW);}
        if(asetus.getColor().equals("Green")) {tv.setTextColor(Color.GREEN);}
        }

    private void checkFont(){
        if((asetus.getName()).equals("chunkfive") || (asetus.getName()).equals("greatvibes")){
            fontlocation = asetus.getName()+".otf";
        }else{
            fontlocation = asetus.getName()+".ttf";
        }
    }

    private void checkBold(){
        if(asetus.getBold().equals("bold")){tv.setTypeface(null, Typeface.BOLD);}
        if(asetus.getBold().equals("italic")){tv.setTypeface(null, Typeface.BOLD_ITALIC);}
        if(asetus.getBold().equals("normal")){tv.setTypeface(null, Typeface.NORMAL);}
    }

    public static void setLocale(Activity activity, String language) {
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

    public void testText(){
        if(text == null){

        }else{
            tv.setText(text);
        }
    }
}
