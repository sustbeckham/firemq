package com.mozilla.fire.consumer;

import com.mozilla.fire.common.Utils;
import com.mozilla.fire.common.constants.RequestType;
import com.mozilla.fire.common.http.Request;
import com.mozilla.fire.common.http.RequestFuture;
import com.mozilla.fire.common.http.Response;
import com.mozilla.fire.common.log.TLog;
import com.mozilla.fire.common.message.BaseMessage;
import com.mozilla.fire.consumer.netty.ConsumerClient;
import io.netty.channel.Channel;

/**
 * Created by mozilla on 2017/12/11.
 */
public class MessageConsumer {

    private Listener listener;

    private Channel channel;

    public MessageConsumer(){

        // ��Ϣ�����߽�����Broker֮��ĳ�����
        channel = ConsumerClient.connectBroker();
    }

    /**
     * ������Ϣ������
     *
     * @param listener
     */
    public void addListener(Listener listener){
       this.listener = listener;
    }

    /**
     * ��Ϣ���������
     */
    public void start(){
        while(true) {
            RequestFuture future = RequestFuture.create(buildPullRequest());
            channel.writeAndFlush(future);

            Response<BaseMessage> response = null;
            try {
                response = future.get();
            }catch (Exception e){
                TLog.error("[consumer] pull request error", e);
            }

            if(response != null && response.getResponse() != null){
                listener.process(response.getResponse());
            }
        }
    }

    /**
     * ����pullģʽ������
     *
     * @return
     */
    private Request buildPullRequest(){
        Request request = new Request();
        request.setRequestID(Utils.nextID());
        request.setRequestType(RequestType.PULL_REQUEST.getCode());
        return request;
    }
}
