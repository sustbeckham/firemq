package com.mozilla.fire.common.http.encoder;

import com.mozilla.fire.common.Utils;
import com.mozilla.fire.common.http.Request;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by mozilla on 2017/12/12.
 */
public class RequestEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        Utils.toByteBuf((Request)msg, out);
    }
}
