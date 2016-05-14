package com.tweets;

import android.content.Context;
import android.content.res.Resources;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by yuval on 5/14/2016.
 */
public class Utils {
    public static String getConfigValue(Context context, String name) {
        String configValue = null;
        try {
            Resources resources = context.getResources();
            InputStream rawResource = resources.openRawResource(R.raw.config);
            Properties properties = new Properties();
            properties.load(rawResource);
            configValue = properties.getProperty(name);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return configValue;
    }
}