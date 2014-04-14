package com.pingpong.server;

import com.pingpong.processor.*;
import com.pingpong.storage.CacheManager;
import com.pingpong.storage.DatabaseManager;
import com.pingpong.storage.RedissonCache;
import com.pingpong.storage.Storage;

/**
 * Created by tihon on 14.04.14.
 */
public class ProcessorContainer {

    private static RequestsProcessor reqProc;
    private static LoaderProcessor loadProc;
    private static Storage storProc;

    public static RequestsProcessor getRequestsProcessor() {
        if (reqProc == null)
            reqProc = new RequestManager();
        return reqProc;
    }

    public static LoaderProcessor getLoaderProcessor() {
        if (loadProc == null)
            loadProc = new DataLoaderManager();
        return loadProc;
    }

    public static Storage getStorateProcessor() {
        if (storProc == null) {
            Storage redisson = new RedissonCache();
            Storage cache = new CacheManager(redisson);
            storProc = new DatabaseManager(cache);
        }
        return storProc;
    }
}
