package com.mozilla.fire.broker.test;

import com.mozilla.fire.broker.netty.BrokerServer;
import com.mozilla.fire.common.log.TLog;

/**
 * Created by mozilla on 2017/12/12.
 */
public class BrokerTest {

    public static void main(String[] args) {

        BrokerServer brokerServer = new BrokerServer();
        try {
            brokerServer.start();
        }catch (Exception e){
            TLog.error("Broker启动异常。", e);
        }
    }
}
