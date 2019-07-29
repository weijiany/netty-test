package netty.sixexample.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.sixexample.MyDataInfo;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ProtoBufClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Pack> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Pack msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

        service.scheduleWithFixedDelay(() -> {
            Random random = new Random();
            int value = random.nextInt(2);
            System.out.println(value);
            MyDataInfo.PackType type = MyDataInfo.PackType.forNumber(value);

            switch (type) {
                case STUDENT:
                    MyDataInfo.Student.Builder studentBuilder = MyDataInfo.Student.newBuilder()
                            .setName("zhang san")
                            .setAge(20)
                            .setAddress("Beijing");
                    ctx.writeAndFlush(MyDataInfo.Pack.newBuilder()
                            .setPackType(MyDataInfo.PackType.STUDENT)
                            .setStudent(studentBuilder));
                    break;
                case NUMBER:
                    MyDataInfo.Number.Builder numberBuilder= MyDataInfo.Number.newBuilder()
                            .setId(new Random().nextInt(10000000))
                            .setValue(UUID.randomUUID().toString());
                    ctx.writeAndFlush(MyDataInfo.Pack.newBuilder()
                            .setPackType(MyDataInfo.PackType.NUMBER)
                            .setNumber(numberBuilder));
                    break;
                default:
                    ctx.writeAndFlush(null);
                    break;
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
}
