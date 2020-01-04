package org.smilesnail.hoa.services.controller;

import com.alibaba.fastjson.JSONObject;
import org.smilesnail.common.utils.SpringContextUtil;

import java.util.Map;

/**
 * @Description:
 * @author: xiaokuige
 * @Emial: 651023907
 * @Date： 2020/1/4 14:29
 */
public class BaseController {

    public static Map<String,Object> invokeMethod(ParameterOrder parameterOrder){
        String serviceName = parameterOrder.getServiceName();
        String methodName = parameterOrder.getMethodName();
        JSONObject jsonObject = parameterOrder.getJsonObject();
        Map<String,Object> resultMap;
        try{
            Object service = SpringContextUtil.getBean(serviceName);
            if(service == null){

            }
        }catch (Exception e){

        }

    }
}
