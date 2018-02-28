package com.cv4j.telegram.bot.impl;

import com.alibaba.fastjson.JSONObject;
import io.vertx.rxjava.ext.web.client.WebClient;

/**
 * Created by tony on 2018/2/28.
 */
public class TelegramBotClient {

    private WebClient client;
    private JSONObject json;
    private String baseUrl;

    public TelegramBotClient(WebClient client, JSONObject json, String baseUrl) {
        this.client = client;
        this.json = json;
        this.baseUrl = baseUrl;
    }
}
