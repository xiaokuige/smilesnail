package org.smilesnail.hoa.services.controller;

import com.alibaba.fastjson.JSONObject;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Description:
 * @author: xiaokuige
 * @Emial: 651023907
 * @Date： 2020/1/2 16:21
 */
@Controller
public class AppController extends BaseController{
    public static Logger logger = LoggerFactory.getLogger(AppController.class);

    @ResponseBody
    @RequestMapping(value = "/app",method = RequestMethod.POST)
    public Map<String,Object> app(@RequestBody JSONObject jsonObject){
        //鉴权服务名
        String autServiceName = "authService";
        //鉴权方法名
        String autMethodName = "authorization";
        //uid
        String uid = jsonObject.getString("uid");
        //token
        String token = jsonObject.getString("token");
        //获取业务服务名
        String serviceName = jsonObject.getString("serviceName");
        //获取业务方法名
        String methodName = jsonObject.getString("methodName");

        JSONObject autParameters = new JSONObject();
        autParameters.put("uid",uid);
        autParameters.put("token",token);
        autParameters.put("serviceName",serviceName);
        autParameters.put("methodName",methodName);

        Map<String, Object> checkPermissionResultObjectMap = invokeMethod(new ParameterOrder( autServiceName, autMethodName, autParameters));
        if (Integer.parseInt(String.valueOf(checkPermissionResultObjectMap.get(ContextConstant.CODE))) != 0) {
            return checkPermissionResultObjectMap;
        }

        JSONObject parameters = getParameters(jsonObject);
        Object rule = checkPermissionResultObjectMap.get(ContextConstant.DATA_KEY);
        if(!StringUtil.isEmptyOrNull(rule)){
            parameters.put("rule",rule);
        }

        return invokeMethod(new ParameterOrder(serviceName, methodName, parameters));


    }
}
