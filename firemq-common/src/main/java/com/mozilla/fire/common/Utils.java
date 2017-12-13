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
     * 将一个抽象的Request转换为Netty所需要的ByteBuf对象
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
     * 将一个Netty的ByteBuf对象转换为抽象的Request
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
     * 将一个抽象的Response转换为Netty所需要的ByteBuf对象
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
     * 将一个Netty的ByteBuf对象转换为抽象的Response
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
     * 随机生成一个long型数值, 用来做测试时生成的唯一ID.
     *
     * @return
     */
    @Deprecated
    public static long nextID(){
        return new Random().nextLong();
    }
}

