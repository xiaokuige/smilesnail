package org.smilesnail.hoa.services.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.CharsetUtil;
import org.springframework.test.web.servlet.MockMvc;

import java.net.InetSocketAddress;

/**
 * @Description:
 * @author: xiaokuige
 * @Emial: 651023907
 * @Date： 2020/1/2 15:01
 */
public class HttpServerInboundHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private MockMvc mockMvc;

    public HttpServerInboundHandler(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        InetSocketAddress inSocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = inSocket.getAddress().getHostAddress();
        String requestUri = request.uri().length() <= 1 ? "/private" : request.uri();
        String appId = request.headers().get("appId");
        String methodName = request.headers().get("methodName");
        String serviceName = request.headers().get("serviceName");
        String mfrs = request.headers().get("mfrs");
        String md5 = request.headers().get("md5");
        String timestamp = request.headers().get("timestamp");
        String nonce = request.headers().get("nonce");
        String sign = request.headers().get("sign");
        String requestData = request.content().toString(CharsetUtil.UTF_8);
        ctx.executor().execute(new HttpServerDispatchHandler(mockMvc, ctx, clientIp, requestUri, requestData,appId,methodName,serviceName,mfrs,md5,timestamp,nonce,sign));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
