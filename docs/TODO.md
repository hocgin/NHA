- ~~ID 生成，雪花算法~~ (channel.id(), 即雪花算法`4.1+`支持)
	- ：childHandle 尝试支持多协议
	- ：离线消息处理方式
	- ：handler 单例模式
	- ：编码/解码 合并处理
	- ：handler压缩，和之前注解的方式一样
	- netty 不要在**channelRead0**进行耗时操作，请通过线程池，所以可以用mq来进行
	- ：参照https://github.com/netty/netty/tree/4.1/example/src/main/java/io/netty/example/http/websocketx?from=groupmessage&isappinstalled=0
	- ：空闲检测，心跳保持
	- ：客户端也要空闲检测，心跳，重新登陆
	- : MQ 结合