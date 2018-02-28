package com.cv4j.telegram.bot.request;

import com.cv4j.telegram.bot.response.SendResponse;

/**
 * Created by tony on 2018/2/28.
 */
public class SendMessage extends Request<SendResponse>{

    public SendMessage(String chatId, String text) {
        super(SendResponse.class);
        add("chat_id", chatId);
        add("text", text);
    }
}
