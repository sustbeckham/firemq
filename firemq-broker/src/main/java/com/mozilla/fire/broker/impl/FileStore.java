package com.mozilla.fire.broker.impl;

import com.mozilla.fire.broker.Store;
import com.mozilla.fire.broker.StoreResult;
import com.mozilla.fire.common.log.TLog;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 存储类型:Linux文件类型
 *
 * Created by mozilla on 2017/12/11.
 */
public class FileStore implements Store{

    private ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<Object>(200);

    @Override
    public StoreResult write(Object message) {

        TLog.info("[Store] message write success. message=" + message);

        queue.offer(message);

        // 1. 写PageCache

        // 2. 异步刷盘

        return null;
    }

    @Override
    public Object consume() {

        TLog.info("[Store] message ready to consume。");

        Object result = null;

        try{
            result = queue.take();
        }catch (Exception e){
            TLog.error("[Store] message read error。", e);
        }

        TLog.info("[Store] message read success。");

        return result;
    }
}
