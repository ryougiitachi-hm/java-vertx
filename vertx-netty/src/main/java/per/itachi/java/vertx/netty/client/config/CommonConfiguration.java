package per.itachi.java.vertx.netty.client.config;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import java.net.InetSocketAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import per.itachi.java.vertx.netty.client.handler.ClientHandler;

@Slf4j
@Configuration
public class CommonConfiguration {

    private static final String HOST = "127.0.0.1";

    private static final int PORT = 8080;

    @Bean
    public Bootstrap bootstrap() {
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            bootstrap.group(group)
                    .remoteAddress(new InetSocketAddress(HOST, PORT))
                    //长连接
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<Channel>() {
                        protected void initChannel(Channel channel) throws Exception {
                            //包含编码器和解码器
                            channel.pipeline().addLast(new HttpClientCodec());
                            //聚合
                            channel.pipeline().addLast(new HttpObjectAggregator(1024 * 10 * 1024));
                            //解压
                            channel.pipeline().addLast(new HttpContentDecompressor());
                            channel.pipeline().addLast(new ClientHandler());
                        }
                    });

            ChannelFuture channelFuture = bootstrap.connect().sync();

            channelFuture.channel().closeFuture().sync();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            group.shutdownGracefully();
        }
        return bootstrap;
    }
}
