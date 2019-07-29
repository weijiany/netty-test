package netty.firstexample;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * 执行顺序：added -> registered -> active -> channelRead0 -> inactive -> unregistered -> handlerRemoved
 *
 * @author 杨伟健
 * Create on 2018/10/07 20:08
 **/
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
        if (httpObject instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) httpObject;
            System.out.println("method: " + request.method().name());

            URI uri = new URI(request.uri());
            String path = uri.getPath();
            System.out.println("path: " + path);

            HttpResponse response;

            if (path.equals("/favicon.ico")) {
                response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND);
                response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
                response.setStatus(HttpResponseStatus.NOT_FOUND);
            } else {
                ByteBuf content = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
                response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                        HttpResponseStatus.OK, content);
                response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
                response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            }

            channelHandlerContext.writeAndFlush(response);
        }
    }
}
