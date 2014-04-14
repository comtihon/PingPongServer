package com.pingpong.processor;

import com.pingpong.core.Logger;
import com.pingpong.model.Request;
import com.pingpong.packet.gen.Error;
import com.pingpong.server.Config;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.pingpong.packet.gen.Packet.FullPacket.PacketType;

/**
 * Created by tihon on 04.04.14.
 */
public class RequestManager implements RequestsProcessor {

    private static final long KEEP_ALIVE_TIME = 1L;

    private ThreadPoolExecutor poolExecutor;

    public RequestManager() {
        int maxRequestPool = Integer.parseInt(Config.getInstance().getProperty("max_request_pool"));

        poolExecutor = new ThreadPoolExecutor(
                maxRequestPool / 10,
                maxRequestPool,
                KEEP_ALIVE_TIME,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>()
        );
    }

    @Override
    public void addRequest(final Request packet) {
        if (packet == null) {
            return;
        }
        try {
            poolExecutor.execute(packet);
        } catch (RejectedExecutionException re) {
            Logger.e("Pool with " + poolExecutor.getQueue().size() + " queue size rejected packet.");
            Error.ErrorPacket error = Error.ErrorPacket.newBuilder()
                    .setCode(500)
                    .setDescr("Can't process packet!")
                    .build();
            packet.sendResponse(error.toByteString(), PacketType.Error);
        }
        Logger.d("Queue size: " + poolExecutor.getQueue().size());
        Logger.d("Active count : " + poolExecutor.getActiveCount());
        Logger.d("Tasks count : " + poolExecutor.getTaskCount());
        Logger.d("Completed task count : " + poolExecutor.getCompletedTaskCount());
        Logger.d("PoolSize :" + poolExecutor.getPoolSize());
    }

    @Override
    public void removeRequest(final Request packet) {
        if (packet != null) {
            poolExecutor.remove(packet);
        }
    }
}
