package com.pingpong.processor;

import com.pingpong.core.Logger;
import com.pingpong.server.ProcessorContainer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by tihon on 14.04.14.
 */
public class DataLoaderManager implements LoaderProcessor {

    private List<String> pingers;

    public DataLoaderManager() {
        pingers = new CopyOnWriteArrayList<>();
    }

    @Override
    public void syncCache() {
        List<String> clearList = new ArrayList<>();
        Iterator<String> it = pingers.iterator();
        while (it.hasNext()) {
            String uid = it.next();
            ProcessorContainer.getStorateProcessor().savePingValue(uid);    //move number of pings from cache to database
            clearList.add(uid);
        }
        pingers.removeAll(clearList);   //remove copied from list
    }

    /**
     * Add connected Pinger to array. This array is used to synchronize data between
     * database and cache.
     *
     * @param uid
     */
    @Override
    public void registerPinger(String uid) {
        if (!pingers.contains(uid)) {
            pingers.add(uid);
            Logger.d("New pinger %s registered.", uid);
        }
    }
}
