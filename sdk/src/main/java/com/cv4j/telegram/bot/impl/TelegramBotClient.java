package com.cv4j.telegram.bot.impl;

import com.cv4j.telegram.bot.request.HttpMethod;
import com.cv4j.telegram.bot.request.Request;
import com.cv4j.telegram.bot.response.HttpResponse;
import io.reactivex.Maybe;

import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.functions.Function;
import okhttp3.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by tony on 2018/2/28.
 */
public class TelegramBotClient {

    private OkHttpClient client;
    private String baseUrl;

    public TelegramBotClient(String baseUrl) {

        this.baseUrl = baseUrl;
        this.client = new OkHttpClient.Builder().build().newBuilder().readTimeout(1000, TimeUnit.MILLISECONDS).build();
    }

    public Maybe<HttpResponse<String>> send(final Request request) {

        String url = baseUrl + request.getMethodName();

        okhttp3.Request.Builder requestBuilder = null;

        if (request.getHttpMethod() == HttpMethod.POST) {

            Map<String, Object> parameters = request.getParameters();

            if (parameters!=null) {

                FormBody.Builder builder = new FormBody.Builder();
                for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                    builder.add(entry.getKey(), String.valueOf(entry.getValue()));
                }
                RequestBody requestBody =  builder.build();

                requestBuilder = new okhttp3.Request.Builder().url(url).post(requestBody);
            }
        } else {

            requestBuilder = new okhttp3.Request.Builder().url(url);
        }

        okhttp3.Request okrequest = requestBuilder.build();

        return Maybe.create(new MaybeOnSubscribe<okhttp3.Response>() {

            @Override
            public void subscribe(MaybeEmitter<okhttp3.Response> emitter) throws Exception {

                emitter.onSuccess(client.newCall(okrequest).execute());
            }
        }).map(new Function<Response, HttpResponse<String>>() {

            @Override
            public HttpResponse<String> apply(okhttp3.Response resp) throws Exception {

                HttpResponse response = new HttpResponse();
                response.setData(resp.body().string());
                return response;
            }
        });
    }
}
