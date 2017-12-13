package com.mozilla.fire.common;

import com.mozilla.fire.common.http.Request;
import com.mozilla.fire.common.http.Response;
import io.netty.buffer.ByteBuf;

import java.util.Random;

/**
 * Created by mozilla on 2017/12/12.
 */
public class Utils {

    /**
     * ��һ�������Requestת��ΪNetty����Ҫ��ByteBuf����
     *
     * @param request
     * @return
     */
    public static ByteBuf toByteBuf(Request request, ByteBuf buffer){
        byte[] encode = Hessian4Serializable.encode(request.getMessage());
        buffer.writeLong(request.getRequestID());
        buffer.writeInt(request.getRequestType());
        buffer.writeBytes(encode);
        return buffer;
    }

    /**
     * ��һ��Netty��ByteBuf����ת��Ϊ�����Request
     *
     * @param byteBuf
     * @return
     */
    public static Request toRequest(ByteBuf byteBuf){
        Request request = new Request();
        request.setRequestID(byteBuf.readLong());
        request.setRequestType(byteBuf.readInt());

        int readable = byteBuf.readableBytes();
        Object message = Hessian4Serializable.decode(byteBuf.readBytes(readable).array());
        request.setMessage(message);
        return request;
    }

    /**
     * ��һ�������Responseת��ΪNetty����Ҫ��ByteBuf����
     *
     * @param response
     * @return
     */
    public static ByteBuf toByteBuf(Response response, ByteBuf buffer){
        byte[] encode = Hessian4Serializable.encode(response.getResponse());
        buffer.writeLong(response.getRequestID());
        buffer.writeBoolean(response.isSuccess());
        buffer.writeInt(response.getErrorCode());
        buffer.writeBytes(encode);
        return buffer;
    }

    /**
     * ��һ��Netty��ByteBuf����ת��Ϊ�����Response
     *
     * @param byteBuf
     * @return
     */
    public static Response toResponse(ByteBuf byteBuf){
        Response response = new Response(byteBuf.readLong());
        response.setSuccess(byteBuf.readBoolean());
        response.setErrorCode(byteBuf.readInt());

        int readable = byteBuf.readableBytes();
        Object message = Hessian4Serializable.decode(byteBuf.readBytes(readable).array());
        response.setResponse(message);
        return response;
    }

    /**
     * �������һ��long����ֵ, ����������ʱ���ɵ�ΨһID.
     *
     * @return
     */
    @Deprecated
    public static long nextID(){
        return new Random().nextLong();
    }
}

