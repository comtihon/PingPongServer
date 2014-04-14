package com.pingpong.job;

import com.pingpong.core.Logger;
import com.pingpong.server.Config;
import com.pingpong.server.ProcessorContainer;

/**
 * Created by tihon on 05.04.14.
 */
public class DataLoaderJob extends Thread{

    private final long period;

    public DataLoaderJob() {
        period = Integer.valueOf(Config.getInstance().getProperty("data_loader_period"));
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(period);
                Logger.i("DataLoaderJob synchronising pings");
                doSync();
            } catch (InterruptedException e) {
                Logger.e("DataLoaderJob interrupted! Reason:" + e.getMessage());
            }
        }
    }

    /**
     * Gets all pingers, which were connected to server and saves their ping-numbers to database.
     * Clears their ping number in cache
     */
    private void doSync() {
        ProcessorContainer.getLoaderProcessor().syncCache();
    }
}
