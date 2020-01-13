package liu.yue.xin.chen.com.main;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import liu.yue.xin.chen.com.handler.dispatcher.HandlerDispatcher;
import liu.yue.xin.chen.com.net.HttpServer;
import liu.yue.xin.chen.com.utils.ClassPathScanner;

/**
 * 服务配置
 * 
 * @bk https://home.cnblogs.com/u/huanuan/
 * @简书 https://www.jianshu.com/u/d29cc7d7ca49
 * @Author 六月星辰
 * @Date 2020年1月10日
 */
@Configuration
@PropertySource("classpath:/httpserver.properties")
@ImportResource("classpath:/quartz.xml")
public class BeanConfig {
	@Autowired
	Environment env;

	@Autowired
	void init() {
		// 系统配置 设置
		String ip = env.getProperty("ip");
		int port = env.getProperty("port", int.class);
	}

	@Bean
	HandlerDispatcher dispatcher() {
		// 注册业务处理器
		HandlerDispatcher dispatcher = new HandlerDispatcher();
		Set<Class> clazzs = ClassPathScanner.scan("liu.yue.xin.chen.com.server", false, true, false, null);
		dispatcher.initHandler(clazzs);
		return dispatcher;
	}

	@Bean
	HttpServer httpServer() {
		HttpServer httpServer = new HttpServer(env.getProperty("port", int.class));
		return httpServer;
	}

}
