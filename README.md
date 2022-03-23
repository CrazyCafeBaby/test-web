
启动类：com.example.client.ClientApplication

com.example.web.Controller 中提供两个HTTP请求：
+ curl localhost:8080/message/send?msg={消息} 。通过msg传递任意消息内容，该请求会调用 test-server 中的sofa-rpc接口，test-server会根据收到的请求通过rocketmq发送一个消息。
+ curl localhost:8080/message/get。查询最近三次发送的消息内容

同时：com.example.mq.listener.DefaultConsumerListener 也会注册rocketmq的消息监听器，当收到消息后，会将消息内容写入数据库中

# 提示
application.yml 中部分配置需要根据实际情况进行替换

+ com.alipay.sofa.rpc.registry.address 为sofa-registry地址
+ rocketmq.producer.namesrvAddr 为rocket的namesrv地址
+ rocketmq.producer.group 为rocket的group配置
+ spring.datasource.url 中是连接数据库地址信息
+ spring.datasource.username / password 是数据库的 用户名/密码 配置

使用命令：mvn clean package spring-boot:repackage 可打包可执行文件
