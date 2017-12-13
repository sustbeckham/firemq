package com.mozilla.fire.common.http.encoder;

import com.mozilla.fire.common.Utils;
import com.mozilla.fire.common.http.Response;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by mozilla on 2017/6/15.
 */
public class ResponseEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        Utils.toByteBuf((Response)msg, out);
    }
}
