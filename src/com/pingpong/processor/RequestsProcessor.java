package com.pingpong.processor;

import com.pingpong.model.Request;

/**
 * Created by tihon on 14.04.14.
 */
public interface RequestsProcessor {
    public void addRequest(final Request packet);

    public void removeRequest(final Request packet);
}
