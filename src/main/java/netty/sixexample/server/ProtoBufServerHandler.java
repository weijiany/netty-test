package netty.sixexample.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.sixexample.MyDataInfo;

public class ProtoBufServerHandler extends SimpleChannelInboundHandler<MyDataInfo.Pack> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Pack msg) throws Exception {
        MyDataInfo.PackType type = msg.getPackType();

        switch (type) {
            case NUMBER:
                System.out.println(msg.getNumber());
                break;
            case STUDENT:
                System.out.println(msg.getStudent());
                break;
            default:
                System.out.println("null message");
                break;
        }
    }
}
