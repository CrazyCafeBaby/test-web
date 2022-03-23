package com.example.integration.impl;

import com.example.integration.RpcClient;
import com.example.server.api.TestApi;

/**
 * 客户端实现类
 */
public class RpcClientImpl implements RpcClient {

    /**
     * sofa rpc接口引用
     */
    private TestApi testApi;

    /**
     * @see RpcClient#sendMessage(String)
     */
    public void sendMessage(String msg) {
        testApi.sendMessage(msg);
    }

    public void setTestApi(TestApi testApi) {
        this.testApi = testApi;
    }
}
