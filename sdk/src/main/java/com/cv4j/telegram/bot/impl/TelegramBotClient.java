package com.cv4j.telegram.bot.impl;

import com.cv4j.telegram.bot.request.Request;
import com.cv4j.telegram.bot.utils.VertxUtils;
import io.reactivex.Maybe;

import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.reactivex.core.MultiMap;
import io.vertx.reactivex.ext.web.client.HttpRequest;
import io.vertx.reactivex.ext.web.client.HttpResponse;
import io.vertx.reactivex.ext.web.client.WebClient;
import io.vertx.reactivex.ext.web.codec.BodyCodec;

/**
 * Created by tony on 2018/2/28.
 */
public class TelegramBotClient {

    private WebClient webClient;
    private String baseUrl;

    public TelegramBotClient(String baseUrl) {

        this.baseUrl = baseUrl;

        WebClientOptions options = new WebClientOptions();
        options.setKeepAlive(true).setReuseAddress(true).setFollowRedirects(true);
        options.setConnectTimeout(1000);

        this.webClient = WebClient.create(VertxUtils.reactivex_vertx, options);
    }

    public Maybe<HttpResponse<String>> send(final Request request) {

        String url = baseUrl+request.getMethodName();

        System.out.println("url="+url);

        HttpRequest<String> httpRequest = webClient
                .requestAbs(HttpMethod.POST,url)
                .ssl(true)
                .as(BodyCodec.string());

        httpRequest.putHeader("Content-type",request.getContentType());

        if (request.isMultipart()) {

            return null;
        } else {

            MultiMap form = MultiMap.caseInsensitiveMultiMap();
            form.getDelegate().addAll(request.getParameters());

            System.out.println("form="+form.toString());

            return httpRequest
                    .rxSendForm(form)
                    .toMaybe();
        }

    }
}
