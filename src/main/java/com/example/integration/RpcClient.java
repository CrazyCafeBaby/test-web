package com.example.integration;

/**
 * rpc调用客户端
 */
public interface RpcClient {

    /**
     * 调用远端sofa rpc接口发送消息
     *
     * @param msg 消息内容
     */
    void sendMessage(String msg);
}
