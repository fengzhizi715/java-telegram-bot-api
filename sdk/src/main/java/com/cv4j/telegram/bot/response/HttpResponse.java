package com.cv4j.telegram.bot.response;

import lombok.Data;

/**
 * Created by tony on 2018/2/28.
 */
@Data
public class HttpResponse<T> {

    private boolean ok;

    private T data;
}
