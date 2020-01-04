package org.smilesnail.hoa.services.controller;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @Description:
 * @author: xiaokuige
 * @Emial: 651023907
 * @Date： 2020/1/4 14:11
 */
public class ParameterOrder implements Serializable {
    private static final long serialVersionUID = 2823604289137689725L;
    private String serviceName;
    private String methodName;
    private JSONObject jsonObject;

    public ParameterOrder(String autServiceName, String methodName, JSONObject jsonObject) {
        this.serviceName = autServiceName;
        this.methodName = methodName;
        this.jsonObject = jsonObject;
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
        return "ParameterOrder{" +
                "serviceName='" + serviceName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", jsonObject=" + jsonObject +
                '}';
    }
}
