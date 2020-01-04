package org.smilesnail.hoa.services.controller;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @Description:
 * @author: xiaokuige
 * @Emial: 651023907
 * @Date： 2020/1/3 17:34
 */
public class ParameterOrder implements Serializable {
    private static final long serialVersionUID = 2823604289137689725L;
    private String origIp;
    private String serviceName;
    private String methodName;
    private JSONObject jsonObject;

    public ParameterOrder() {
    }

    public ParameterOrder(String origIp, String serviceName, String methodName, JSONObject jsonObject) {
        this.origIp = origIp;
        this.serviceName = serviceName;
        this.methodName = methodName;
        this.jsonObject = jsonObject;
    }

    public String getOrigIp() {
        return origIp;
    }

    public void setOrigIp(String origIp) {
        this.origIp = origIp;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ParameterOrder{");
        sb.append(", origIp='").append(origIp).append('\'');
        sb.append(", serviceName='").append(serviceName).append('\'');
        sb.append(", methodName='").append(methodName).append('\'');
        sb.append(", jsonObject=").append(jsonObject);
        sb.append('}');
        return sb.toString();
    }
}
