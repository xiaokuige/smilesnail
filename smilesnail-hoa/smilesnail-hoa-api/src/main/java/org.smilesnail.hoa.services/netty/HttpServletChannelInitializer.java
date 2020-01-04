package org.smilesnail.hoa.services.netty;


import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import org.smilesnail.hoa.services.controller.SpringMvcController;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;


public class HttpServletChannelInitializer extends ChannelInitializer<SocketChannel> {

    private static EventExecutorGroup handlersExecutor = new DefaultEventExecutorGroup(4);

    private static MockMvc mockMvc;

    public HttpServletChannelInitializer() {
        List<Object> controllerObjectList = SpringMvcController.getInstance().getControllerObjectList();

        FastJsonHttpMessageConverter messageConverter = new FastJsonHttpMessageConverter();
        messageConverter.getFastJsonConfig().setSerializerFeatures(SerializerFeature.WriteMapNullValue);

        mockMvc = MockMvcBuilders.standaloneSetup(controllerObjectList.toArray())
                .setMessageConverters(messageConverter).build();
    }

    @Override
    public void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(65536));
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(handlersExecutor, new HttpServerInboundHandler(mockMvc));
    }


}
