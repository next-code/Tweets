package com.tweets;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class ConfigTest {
    Context ctx;

    @Before
    public void setup() {
        ctx = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void config_test() {
        String twitterKey = Utils.getConfigValue(ctx, Consts.TWITTER_KEY);
        assertNotNull(twitterKey);
        String twitterSecret = Utils.getConfigValue(ctx, Consts.TWITTER_SECRET);
        assertNotNull(twitterSecret);
        String screenName = Utils.getConfigValue(ctx, Consts.SCREEN_NAME);
        assertNotNull(screenName);
    }


}