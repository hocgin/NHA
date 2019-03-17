## Netty High Availability
长链接高可用方案。

## 结构划分
- body 传递实体
- core 基础/协议
- bosser 连接保持
- worker 业务处理
- client 客户端

## 使用方式
1. 架设 RocketMQ
2. 更改对应的IP, 参照`ENV.dev`文件
3. run WorkerApplication
4. run BosserApplication
5. run NettyClient

## 待
- 对消息进行`topic`精准投送