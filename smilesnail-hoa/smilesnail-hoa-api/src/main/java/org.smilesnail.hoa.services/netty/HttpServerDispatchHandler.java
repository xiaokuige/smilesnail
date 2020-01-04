package org.smilesnail.hoa.services.netty;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;



public class HttpServerDispatchHandler implements Runnable {

    private MockMvc mockMvc;
    private ChannelHandlerContext ctx;
    private String clientIp;
    private String requestUri;
    private String requestData;
    private String appId;
    private String methodName;
    private String serviceName;
    private String mfrs;
    private String md5;
    private String timestamp;
    private String nonce;
    private String sign;

    private FullHttpResponse nettyResponse;

    public HttpServerDispatchHandler(MockMvc mockMvc, ChannelHandlerContext ctx, String clientIp, String requestUri, String requestData,String appId, String methodName, String serviceName, String mfrs, String md5, String timestamp, String nonce, String sign) {
        this.mockMvc = mockMvc;
        this.ctx = ctx;
        this.clientIp = clientIp;
        this.requestUri = requestUri;
        this.requestData = requestData;
        this.nettyResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        this.appId = appId;
        this.methodName = methodName;
        this.serviceName = serviceName;
        this.mfrs = mfrs;
        this.md5 = md5;
        this.timestamp = timestamp;
        this.nonce = nonce;
        this.sign = sign;
    }

    @Override
    public void run() {
        try {
            MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post(requestUri)
                    .header("Orig-Ip", clientIp)
                    .header("Content-Type", "application/json")
                    .header("appId",appId)
                    .header("methodName",methodName)
                    .header("serviceName",serviceName)
                    .header("mfrs",mfrs)
                    .header("md5",md5)
                    .header("timestamp",timestamp)
                    .header("nonce",nonce)
                    .header("sign",sign)
                    .accept(MediaType.APPLICATION_JSON_UTF8).content(requestData);

            MockHttpServletResponse mockResponse = mockMvc.perform(mockRequest).andReturn().getResponse();
            nettyResponse.setStatus(HttpResponseStatus.valueOf(mockResponse.getStatus()));
            nettyResponse.content().writeBytes(mockResponse.getContentAsByteArray());

            for (String name : mockResponse.getHeaderNames()) {
                nettyResponse.headers().set(name, mockResponse.getHeaderValue(name));
            }
        } catch (Exception e) {
            nettyResponse.setStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR);
        }
        ctx.writeAndFlush(nettyResponse).addListener(ChannelFutureListener.CLOSE);
    }


}
