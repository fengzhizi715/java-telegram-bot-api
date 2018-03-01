package com.cv4j.telegram.bot;

import com.cv4j.telegram.bot.impl.TelegramBotClient;
import com.cv4j.telegram.bot.request.Request;

/**
 * Created by tony on 2018/2/27.
 */
public class TelegramBot {

    private final TelegramBotClient api;

    public TelegramBot(String botToken) {
        this(new Builder(botToken));
    }

    TelegramBot(Builder builder) {
        this.api = builder.api;
    }

    public void execute(Request request) {
        api.send(request);
    }

    public static final class Builder {

        static final String API_URL = "https://api.telegram.org/bot";

        private final String botToken;
        private TelegramBotClient api;

        private String apiUrl;

        public Builder(String botToken) {
            this.botToken = botToken;
            api = new TelegramBotClient(apiUrl(API_URL, botToken));
        }

        public Builder apiUrl(String apiUrl) {
            this.apiUrl = apiUrl;
            return this;
        }

        public TelegramBot build() {

            return new TelegramBot(this);
        }

        private static String apiUrl(String apiUrl, String botToken) {
            return apiUrl + botToken + "/";
        }
    }
}
