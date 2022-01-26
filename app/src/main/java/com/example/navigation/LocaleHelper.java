package com.example.navigation;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import com.example.navigation.R;

import java.util.Locale;

public class LocaleHelper extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
        public static void setLocale(Activity activity, String languageCode) {
            Locale locale = new Locale(languageCode);
            Locale.setDefault(locale);
            Resources resources = activity.getResources();
            Configuration config = resources.getConfiguration();
            config.setLocale(locale);
            resources.updateConfiguration(config, resources.getDisplayMetrics());
        }

}
