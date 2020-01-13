package liu.yue.xin.chen.com.net;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * netty HttpServer
 * 
 * @bk https://home.cnblogs.com/u/huanuan/
 * @简书 https://www.jianshu.com/u/d29cc7d7ca49
 * @Author 六月星辰
 * @Date 2020年1月11日
 */
public class HttpServer {
	/** 服务器的端口 **/
	private int port;

	public HttpServer(int port) {
		this.port = port;
	}

	public void start() throws Exception {
		ServerBootstrap bootstrap = new ServerBootstrap();
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup work = new NioEventLoopGroup();
		bootstrap.group(boss, work).channel(NioServerSocketChannel.class).childHandler(new HttpServerInitializer());
		ChannelFuture f = bootstrap.bind(new InetSocketAddress(port)).sync();
		System.err.println(" http服务器 启动  端口  : " + port);
		f.channel().closeFuture().sync();
	}

}
