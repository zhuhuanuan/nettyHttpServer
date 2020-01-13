package liu.yue.xin.chen.com.handler.dispatcher;

import java.lang.reflect.Method;

/**
 * 业务层 请求内存存储类
 * 
 * @bk https://home.cnblogs.com/u/huanuan/
 * @简书 https://www.jianshu.com/u/d29cc7d7ca49
 * @Author 六月星辰
 * @Date 2020年1月13日
 */
public class HanderServer {

	/**执行命令对象*/
	public  Object o;
	
	/**执行命令方法*/
	public  Method method;
	
	/**
	 * 业务层 业务处理
	 * @param o 请求的业务类
	 * @param method 请求的方法
	 * @throws NoSuchMethodException
	 */
	public HanderServer(Object o, Method method) throws NoSuchMethodException {
		super();
		this.o = o;
		this.method = method;
	}
}
