package com.jd;

import com.jd.entity.Request;
import com.jd.entity.Response;
import com.jd.yip.YApi;

public interface FirstApi {

    @YApi(url = "/test/echo", applicationPath = "testmanager")
    Response echo(Request request);
}
