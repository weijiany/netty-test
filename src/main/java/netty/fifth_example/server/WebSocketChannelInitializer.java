package netty.fifth_example.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author 杨伟健
 * Create on 2018/10/11 17:11
 **/
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // http 编解码
        pipeline.addLast(new HttpServerCodec());
        // 以 chunk 的形式去写
        pipeline.addLast(new ChunkedWriteHandler());
        // 把分块后的 http 请求合为一个 FullHttpRequest / FullHttpResponse
        pipeline.addLast(new HttpObjectAggregator(1024 * 1024 * 10));
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws", null, false, 65535));

        pipeline.addLast(new TextWebSocketFrameHandler());
    }
}
