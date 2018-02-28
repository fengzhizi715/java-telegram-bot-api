package com.cv4j.telegram.bot.request;

import com.alibaba.fastjson.JSONObject;
import com.cv4j.telegram.bot.response.HttpResponse;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tony on 2018/2/28.
 */
public class Request<R extends HttpResponse> {

    private final Class<? extends R> responseClass;
    private final Map<String, Object> parameters;

    public Request(Class<? extends R> responseClass) {
        this.responseClass = responseClass;
        this.parameters = new HashMap<String, Object>();
    }

    protected void add(String name, Object val) {
        parameters.put(name, val);
    }

    protected void addAll(Map<String, Object> values) {
        parameters.putAll(values);
    }

    public String getMethodName() {
        String className = this.getClass().getSimpleName();
        return Character.toLowerCase(className.charAt(0)) + className.substring(1);
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public Type getResponseType() {
        return responseClass;
    }

    public boolean isMultipart() {
        return false;
    }

    public String getFileName() {
        return ContentTypes.GENERAL_FILE_NAME;
    }

    public String getContentType() {
        return ContentTypes.GENERAL_MIME_TYPE;
    }

    public int getTimeoutSeconds() {
        return 0;
    }

    public String toWebhookResponse() {

        Map<String, Object> fullMap = new HashMap<String, Object>(parameters);
        fullMap.put("method", getMethodName());
        return JSONObject.toJSONString(fullMap);
    }
}
