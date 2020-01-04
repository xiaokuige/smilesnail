package org.smilesnail.hoa.services;


import org.smilesnail.hoa.services.netty.HttpServer;

/**
 * @Description:项目启动类
 * @author: xiaokuige
 * @Emial: 651023907
 * @Date： 2019/12/20 21:37
 */
public class HoaMain {
    public static void main(String[] args) throws Exception {
        HttpServer httpServer = new HttpServer(8001);
        httpServer.run();
    }
}