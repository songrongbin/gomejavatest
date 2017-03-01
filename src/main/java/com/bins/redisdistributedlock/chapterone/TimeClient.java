package com.bins.redisdistributedlock.chapterone;

import java.net.SocketAddress;
import java.util.Date;


/**
 * Created by songrongbin on 2017/2/28.
 */
public class TimeClient {
    public TimeClient(SocketAddress timeServerAddr) {
    }

    public void close() {

    }

    public long currentTimeMillis() {
        return (new Date()).getTime();
    }
}
