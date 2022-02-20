package per.itachi.java.vertx.netty.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        URI url = new URI("/v1/caches/1");
        String meg = "hello";

        for (int i = 0; i < 1; ++i) {
            //配置HttpRequest的请求数据和一些配置信息
            FullHttpRequest request = new DefaultFullHttpRequest(
                    HttpVersion.HTTP_1_0, HttpMethod.GET, url.toASCIIString(),
                    Unpooled.wrappedBuffer(meg.getBytes(StandardCharsets.UTF_8)));

            request.headers()
    //                .set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8")
                    //开启长连接
                    .set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE)
                    //设置传递请求内容的长度
                    .set(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
        //发送数据
            ctx.writeAndFlush(request);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpResponse response = (FullHttpResponse) msg;

        ByteBuf content = response.content();
        HttpHeaders headers = response.headers();

        log.info("content: {}", content.toString(StandardCharsets.UTF_8));
        log.info("headers: {}", headers);
    }

}
