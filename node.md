### Thrift 数据传输格式

- TBinaryProtocol —— 二进制传输格式
- TCompatProtocol —— 压缩的二进制格式
- TJSONProtocol —— JSON 格式
- TSimpleJSONProtocol —— 提供 JSON 只写协议
- TDebugProtocol —— 使用易于读懂的文本格式，用于 debug

### Thrift 数据传输方式

- TSocket —— 阻塞式 Socket
- TFramedTransport —— 以 frame 方式进行传输，在非阻塞服务中使用
- TFileTransport —— 以文件形式进行传输
- TMemoryTransport —— 将内存用于 I/O，Java 中使用 ByteArrayOutputStream
- TZlibTransport —— 使用 zlib 进行压缩，与其他传输方式联合使用

### Thrift 服务模型

- TSimpleServer —— 简单的单线程服务模型，用于测试
- TThreadPoolServer —— 多线程服务模型，使用标准的阻塞式 I/O
- TNonblockingServer —— 多线程服务模型，使用非阻塞式 I/O （必须使用 TFramedTransport 传输方式）
- THsHaServer —— 引入线程池，把读写任务放到线程池去处理。
Half-sync/Half-async 的处理模式：
Half-async 是在处理I/O事件上（accept/read/write io），
Half-sync 用于 handler 对 rpc 的同步处理。

