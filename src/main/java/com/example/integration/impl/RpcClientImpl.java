package com.example.integration.impl;

import com.example.integration.RpcClient;
import com.example.server.api.TestApi;

public class RpcClientImpl implements RpcClient {

    private TestApi testApi;

    public void sendMessage(String msg) {
        testApi.sendMessage(msg);
    }

    public void setTestApi(TestApi testApi) {
        this.testApi = testApi;
    }
}
