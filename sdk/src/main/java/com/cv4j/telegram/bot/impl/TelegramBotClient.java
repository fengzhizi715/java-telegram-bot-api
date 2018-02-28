package com.cv4j.telegram.bot.impl;

import com.alibaba.fastjson.JSONObject;
import com.cv4j.telegram.bot.config.Config;
import com.cv4j.telegram.bot.request.Request;
import com.cv4j.telegram.bot.utils.VertxUtils;
import io.reactivex.Maybe;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.reactivex.ext.web.client.HttpResponse;
import io.vertx.reactivex.ext.web.client.WebClient;
import io.vertx.reactivex.ext.web.codec.BodyCodec;

/**
 * Created by tony on 2018/2/28.
 */
public class TelegramBotClient {

    private WebClient webClient;
    private JSONObject json;
    private String baseUrl;

    public TelegramBotClient(JSONObject json, String baseUrl) {

        this.json = json;
        this.baseUrl = baseUrl;

        WebClientOptions options = new WebClientOptions();
        options.setKeepAlive(true).setReuseAddress(true).setFollowRedirects(true);

        this.webClient = WebClient.create(VertxUtils.reactivex_vertx, options);
    }

    public Maybe<HttpResponse<String>> send(final Request request) {

        return webClient
                .get(443, Config.API_URL,request.getMethodName())
                .ssl(true)
                .as(BodyCodec.string())
                .rxSend()
                .toMaybe();
    }
}
