package com.tweets;

import android.os.Handler;

/**
 * Created by yuval on 5/14/2016.
 */
public class Scheduler {

    private static Handler handler = new Handler();

    public static void reschedule(final long refreshRate, final ScheduleInterface scheduleInterface) {
        try {
            handler.postDelayed(new Runnable() {
                public void run() {
                    scheduleInterface.schedule();
                    handler.postDelayed(this, refreshRate);
                }
            }, refreshRate);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}