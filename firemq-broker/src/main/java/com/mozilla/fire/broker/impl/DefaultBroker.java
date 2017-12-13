package com.mozilla.fire.broker.impl;

import com.mozilla.fire.broker.Broker;
import com.mozilla.fire.broker.BrokerResult;
import com.mozilla.fire.broker.Store;
import com.mozilla.fire.common.config.BeanConfig;

/**
 * Created by mozilla on 2017/12/11.
 */
public class DefaultBroker implements Broker{

    private Store store = BeanConfig.getInstance(Store.class);

    @Override
    public BrokerResult deliver(Object message) {
        store.write(message);
        return null;
    }

    @Override
    public BrokerResult pull() {
        BrokerResult brokerResult = new BrokerResult();

        Object message = store.consume();

        brokerResult.setMessage(message);
        return brokerResult;
    }
}
