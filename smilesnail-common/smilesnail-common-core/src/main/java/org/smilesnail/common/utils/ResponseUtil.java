package org.smilesnail.common.utils;

import java.util.Map;

/**
 * @Description: 返回相应工具类
 * @author: xiaokuige
 * @Emial: 651023907
 * @Date： 2020/1/4 14:58
 */
public class ResponseUtil {
    public static Map<String, Object> responseMap(TradeCode tradeCode) {
        Map<String, Object> resultMap = new LinkedHashMap<>(2);
        resultMap.put(ContextConstant.CODE, tradeCode.getCode());
        resultMap.put(ContextConstant.MESSAGE, tradeCode.getMsg());
        return resultMap;
    }

    public static Map<String, Object> responseMap(String resultCode, String resultMsg, Object data) {
        Map<String, Object> resultMap = new LinkedHashMap<>(4);
        resultMap.put(ContextConstant.CODE, resultCode);
        resultMap.put(ContextConstant.MESSAGE, resultMsg);
        if (!StringUtil.isEmptyOrNull(data)) {
            resultMap.put(ContextConstant.DATA_KEY, data);
        }
        return resultMap;
    }

    public static Map<String, Object> successResponseMap(Object data) {
        return responseMap(TradeCode.COM_SUCCESS.getCode(), TradeCode.COM_SUCCESS.getMsg(), data);
    }
}
