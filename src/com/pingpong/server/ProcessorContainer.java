package com.pingpong.server;

import com.pingpong.processor.DataLoaderManager;
import com.pingpong.processor.LoaderProcessor;
import com.pingpong.processor.RequestManager;
import com.pingpong.processor.RequestsProcessor;
import com.pingpong.storage.*;

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
            StorageInt redisson = new RedissonCache();
            Storage cache = new CacheManager(redisson);
            storProc = new DatabaseManager(cache);
        }
        return storProc;
    }
}
