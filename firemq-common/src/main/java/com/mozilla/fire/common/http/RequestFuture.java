package com.mozilla.fire.common.http;

import com.google.common.util.concurrent.AbstractFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by mozilla on 2017/12/12.
 */
public class RequestFuture extends AbstractFuture<Object> {

    private final Request request;

    private RequestFuture(Request request) {
        this.request = request;
    }

    public Request getRequest() {
        return request;
    }

    /**
     * ����future
     *
     * @param request
     * @return
     */
    public static RequestFuture create(Request request) {
        return new RequestFuture(request);
    }

    /**
     * ����future����channel�õ���response��д��
     *
     * @param value
     * @return
     */
    @Override
    public boolean set(Object value) {
        return super.set(value);
    }

    /**
     * ����future����channel�õ���response��д��
     *
     * @param response
     */
    public void onResponse(Response response) {
        this.set(response);
    }

    /**
     * ��ȡ����˷��صĽ��
     *
     * @param timeout
     * @param unit
     * @return
     * @throws InterruptedException
     * @throws TimeoutException
     * @throws ExecutionException
     */
    @Override
    public Response get(long timeout, TimeUnit unit) throws InterruptedException, TimeoutException,
            ExecutionException {
        return (Response) super.get(timeout, unit);
    }

    /**
     * ��ȡ����˷��صĽ��
     *
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Override
    public Response get() throws InterruptedException, ExecutionException {
        try {
            return (Response) super.get(1000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            Response response = new Response(request.getRequestID());
            response.setErrorCode(-1);
            return response;
        }
    }

}

