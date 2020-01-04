package org.smilesnail.hoa.services.controller;


import com.alibaba.fastjson.JSONObject;
import com.cmiot.trade.common.enums.TradeCode;
import com.cmiot.trade.common.exception.TradeException;
import com.cmiot.trade.common.utils.ResponseUtil;
import com.cmiot.trade.hoa.services.common.SpringContextUtil;
import com.cmiot.trade.hoa.services.common.SystemSetting;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * modify by zjl on 2018/1/19.
 */
public class BaseController {

    private final static Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 根据验证权限结果组装业务参数
     *
     * @param checkPermissionResultObjectMap
     * @param parameters
     * @param utype
     * @return
     */
    public static JSONObject assemblyParameters(Map<String, Object> checkPermissionResultObjectMap, JSONObject parameters, String utype) {
        if (parameters != null && StringUtils.isNotBlank(utype) && "1".equals(utype)) {
            String mUid = (String) checkPermissionResultObjectMap.get("uid");
            String mUserName = (String) checkPermissionResultObjectMap.get("userName");
            parameters.put("uid", mUid);
            parameters.put("OpenId", mUid);
            parameters.put("userName", mUserName);
        }
        return parameters;
    }

    public static Map<String, Object> invokeMethod(ParameterOrder parameterOrder) {
        String serviceName = parameterOrder.getServiceName();
        String methodName = parameterOrder.getMethodName();
        JSONObject jsonObject = parameterOrder.getJsonObject();
        Map<String, Object> resultMap;
        try {
            Object service = SpringContextUtil.getBean(serviceName);
            if (service == null) {
                return ResponseUtil.responseMap(TradeCode.COM_NONE_SERVICE);
            }
            Method method = ReflectionUtils.findMethod(service.getClass(), methodName, Map.class);
            if (method == null) {
                return ResponseUtil.responseMap(TradeCode.COM_NONE_METHOD);
            }
            resultMap = (Map<String, Object>) ReflectionUtils.invokeMethod(method, service, jsonObject);
        } catch (TradeException te) {
            resultMap = ResponseUtil.responseMap(te.getCode(), te.getMessage(), null);
        } catch (Exception e) {
            resultMap = ResponseUtil.responseMap(TradeCode.COM_SERVER_ERROR);
            logger.error("服务器内部错误:{}",e.getCause().toString());
        }
        return resultMap;
    }


    /**
     * 获取服务名
     *
     * @param jsonObject
     * @return
     */
    public static String getServiceName(JSONObject jsonObject) {
        String serviceName = jsonObject.getString("serviceName");
        if (StringUtils.isBlank(serviceName)) {
            if (jsonObject.containsKey("CmdType")) {
                String cmdType = SystemSetting.getValue(jsonObject.getString("CmdType"));
                if (StringUtils.isBlank(cmdType)) {
                    return null;
                }
                serviceName = cmdType.split(":")[0];
            }
        }
        return serviceName.trim();
    }

    /**
     * 获取方法名
     *
     * @param jsonObject
     * @return
     */
    public static String getMethodName(JSONObject jsonObject) {
        String methodName = jsonObject.getString("methodName");
        if (StringUtils.isBlank(methodName)) {
            if (jsonObject.containsKey("CmdType")) {
                String cmdType = SystemSetting.getValue(jsonObject.getString("CmdType"));
                if (StringUtils.isBlank(cmdType)) {
                    return null;
                }
                methodName = cmdType.split(":")[1];
            }
        }
        return methodName.trim();
    }


    /**
     * 获取接口参数
     *
     * @param jsonObject
     * @return
     */
    public static JSONObject getParameters(JSONObject jsonObject) {
        if (jsonObject.containsKey("parameters")) {
            return jsonObject.getJSONObject("parameters");
        } else if (jsonObject.containsKey("CmdType")) {
            return jsonObject;
        } else {
            return null;
        }
    }

}
