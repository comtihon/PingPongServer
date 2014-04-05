package com.pingpong.job;

import com.pingpong.core.Logger;
import com.pingpong.manager.CacheManager;
import com.pingpong.manager.DatabaseManager;
import com.pingpong.server.Config;

import java.util.Iterator;
import java.util.List;

/**
 * Created by tihon on 05.04.14.
 */
public class DataLoaderJob extends Thread {

    private final long period;

    private static class SingletonHolder {
        private static final DataLoaderJob instance = new DataLoaderJob();
    }

    public static DataLoaderJob getInstance() {
        return SingletonHolder.instance;
    }

    private DataLoaderJob() {
        period = Integer.valueOf(Config.getInstance().getProperty("data_loader_period"));
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(period);
                Logger.d("DataLoaderJob synchronising pings");
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
        List<String> pingers = DatabaseManager.getInstance().getCacheUids();    //for all pingers in cache
        if (pingers != null) {
            Iterator<String> it = pingers.iterator();
            while (it.hasNext()) {
                String uid = it.next();
                DatabaseManager.getInstance().setPingValue(uid, CacheManager.getAndClear(uid)); //move number of pings from cache to database
                it.remove();    //remove pinger from list
            }
        }
    }
}
