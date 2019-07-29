package netty.forthexample.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author 杨伟健
 * Create on 2018/10/10 16:28
 **/
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;

            String eventType = null;

            switch (event.state()) {
                case READER_IDLE:
                    eventType = "读空闲";break;
                case WRITER_IDLE:
                    eventType = "写空闲";break;
                case ALL_IDLE:
                    eventType = "读写空闲";break;
            }

            Channel channel = ctx.channel();
            System.out.println(channel.remoteAddress() + " 超时事件: " + eventType);
            channel.close();
        }
    }
}
