package com.example.geeklearning.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.ReferenceCountUtil;

import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static org.springframework.http.HttpHeaders.CONNECTION;

/**
 * @ClassName HttpHandler
 * @Author 10932
 * @Date 8/21/2021 8:54 PM
 **/
public class HttpHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
            String uri = fullHttpRequest.uri();
            if (uri.contains("/test")) {
                handlerTest(fullHttpRequest, ctx);
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    private void handlerTest(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        FullHttpResponse response = null;
        String value = "h";
        try {
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK, Unpooled.wrappedBuffer(value.getBytes()));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length",
                    response.content().readableBytes());
        } finally {
            boolean keepAlive = HttpUtil.isKeepAlive(fullHttpRequest);
            if (!keepAlive) {
                ctx.write(response).addListener(ChannelFutureListener.CLOSE);
            }
            if (keepAlive && null != response) {
                response.headers().set(CONNECTION, KEEP_ALIVE);
                ctx.write(response);
            }
        }

    }
}
