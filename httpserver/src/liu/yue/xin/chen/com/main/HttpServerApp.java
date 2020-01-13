package liu.yue.xin.chen.com.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import liu.yue.xin.chen.com.net.HttpServer;
import liu.yue.xin.chen.com.net.NettyHttpServer;
import lombok.extern.slf4j.Slf4j;

/**
 * Netty http 启动类
 * 
 * @bk https://home.cnblogs.com/u/huanuan/
 * @简书 https://www.jianshu.com/u/d29cc7d7ca49
 * @Author 六月星辰
 * @Date 2020年1月10日
 */
@Slf4j
public class HttpServerApp {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
		context.start();
		HttpServer bean = context.getBean(HttpServer.class);
		// 安排独立的一条线程进行启动http服务，
		new Thread(new NettyHttpServer(bean)).start();
		;
	}
}
