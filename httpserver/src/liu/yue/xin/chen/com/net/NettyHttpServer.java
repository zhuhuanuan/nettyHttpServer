package liu.yue.xin.chen.com.net;

import lombok.extern.slf4j.Slf4j;

/**
 * http服务 独立线程启动
 * 
 * @bk https://home.cnblogs.com/u/huanuan/
 * @简书 https://www.jianshu.com/u/d29cc7d7ca49
 * @Author 六月星辰
 * @Date 2020年1月11日
 */
@Slf4j
public class NettyHttpServer implements Runnable {

	private HttpServer httpServer;

	public NettyHttpServer(HttpServer httpServer) {
		// TODO Auto-generated constructor stub
		this.httpServer = httpServer;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			this.httpServer.start();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("启动http服务异常！ ");
			e.printStackTrace();
			System.exit(0);
		}
	}

}
