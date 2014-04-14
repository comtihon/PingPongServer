package com.pingpong.processor;

import com.pingpong.server.ProcessorContainer;
import com.pingpong.storage.CacheManager;
import com.pingpong.storage.DatabaseManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tihon on 14.04.14.
 */
public class DataLoaderManager implements LoaderProcessor {
    @Override
    public void syncCache() {
        List<String> pingers = ProcessorContainer.getStorateProcessor().getCacheUids();    //for all pingers in cache
        if (pingers != null) {
            List<String> clearList = new ArrayList<>();
            Iterator<String> it = pingers.iterator();
            while (it.hasNext()) {
                String uid = it.next();
                DatabaseManager.getInstance().setPingValue(uid, CacheManager.getAndClear(uid)); //move number of pings from cache to database
                clearList.add(uid);
            }
            pingers.removeAll(clearList);   //remove copied from list
        }
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
