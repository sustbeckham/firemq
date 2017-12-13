package com.mozilla.fire.common.log;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 * Created by mozilla on 2017/9/19.
 */
public class TLog {

    private final static Logger logger = Logger.getRootLogger();

    // ======================== info ========================

    public static void info(String msg) {
        if (logger.isInfoEnabled()) {
            logger.info(wrapMessage(msg));
        }
    }

    public static void info (String msg, String... kv) {
        if (logger.isInfoEnabled()) {
            logger.info(wrapMessage(msg, kv));
        }
    }

    // ======================== warn ========================

    public static void warn (String msg, String... kv) {
        if (logger.isEnabledFor(Priority.WARN)) {
            logger.warn(wrapMessage(msg, kv));
        }
    }

    public static void warn(String msg, Throwable throwable, String... kv) {
        if (logger.isEnabledFor(Priority.WARN)) {
            logger.warn(wrapMessage(msg, kv), throwable);
        }
    }

    public static void warn(String msg, Throwable throwable) {
        if (logger.isEnabledFor(Priority.WARN)) {
            logger.warn(msg, throwable);
        }
    }

    public static void warn(String msg) {
        if (logger.isEnabledFor(Priority.WARN)) {
            logger.warn(wrapMessage(msg));
        }
    }

    // ======================== error ========================

    public static void error(String msg, Throwable throwable) {
        logger.error(wrapMessage(msg), throwable);
    }

    public static void error(String msg, String... kv) {
        logger.error(wrapMessage(msg, kv));
    }

    public static void error(String msg, Throwable throwable, String... kv) {
        logger.error(wrapMessage(msg, kv), throwable);
    }

    /**
     * 将异常数据对象以JSON格式输出
     *
     * @param data
     * @return
     */
    public static String buildJSONData(Object data){
        return JSONObject.toJSONString(data);
    }

    /**
     * 对日志进行加工
     *
     * @param msg
     * @return
     */
    private static String wrapMessage(String msg) {
        return msg;
    }

    private static String wrapMessage(String msg, String... kv) {
        StringBuilder sb = new StringBuilder(msg);
        sb.append("[");
        if (kv != null && kv.length > 0) {
            for (int i = 0; i < kv.length; i++) {
                sb.append(kv[i]);
                if (i != kv.length - 1) {
                    sb.append(",");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
