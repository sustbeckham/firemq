package com.mozilla.fire.broker;

/**
 * Created by mozilla on 2017/12/11.
 */
public interface Store {

    /**
     * ��Ϣ�־û�
     *
     * @param message
     * @return
     */
    StoreResult write(Object message);

    /**
     * ��Ϣ���ݻ�ȡ
     *
     * @return
     */
    Object consume();
}
