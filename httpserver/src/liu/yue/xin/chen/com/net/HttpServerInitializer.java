package liu.yue.xin.chen.com.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpServerExpectContinueHandler;
import liu.yue.xin.chen.com.handler.HttpRequestHandler;

/**
 * http 初始化器
 * 
 * @bk https://home.cnblogs.com/u/huanuan/
 * @简书 https://www.jianshu.com/u/d29cc7d7ca49
 * @Author 六月星辰
 * @Date 2020年1月11日
 */
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		// TODO Auto-generated method stub
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new HttpServerCodec());// http 编解码
		/**
		 * 下面的是 http 消息聚合器 将http的get和post请求解析为统一的FullHttpRequest或者FullHttpResponse
		 * 
		 */
		pipeline.addLast("httpAggregator", new HttpObjectAggregator(1024 * 1024)); // http 消息聚合器 1024*1024为接收的最大contentlength
		pipeline.addLast(new HttpServerExpectContinueHandler());
		pipeline.addLast(new HttpRequestHandler());// 请求处理器
	}

}
