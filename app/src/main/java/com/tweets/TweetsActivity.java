package com.tweets;

import android.app.ListActivity;
import android.os.Bundle;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TimelineResult;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import io.fabric.sdk.android.Fabric;

public class TweetsActivity extends ListActivity implements ScheduleInterface{

    private final static int REFRESH_RATE = 1000*5;
    private final static String TAG = "Tweets";
    private TweetTimelineListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        String twitterKey = Utils.getConfigValue(this, Consts.TWITTER_KEY);
        String twitterSecret = Utils.getConfigValue(this, Consts.TWITTER_SECRET);
        String screenName = Utils.getConfigValue(this, Consts.SCREEN_NAME);

        TwitterAuthConfig authConfig =  new TwitterAuthConfig(twitterKey, twitterSecret);
        Fabric.with(this, new Twitter(authConfig));


        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName(screenName)
                .build();
        adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                .setViewStyle(R.style.tw__TweetLightStyle)
                .build();
        setListAdapter(adapter);

        Scheduler.reschedule(REFRESH_RATE, this);
    }


    @Override
    public void schedule() {
        try {
            adapter.refresh(new Callback<TimelineResult<Tweet>>() {
                @Override
                public void success(Result<TimelineResult<Tweet>> result) {
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void failure(TwitterException exception) {
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
