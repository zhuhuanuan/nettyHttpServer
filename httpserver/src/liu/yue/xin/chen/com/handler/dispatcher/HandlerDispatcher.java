package liu.yue.xin.chen.com.handler.dispatcher;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

/**
 * 模拟spring 业务层分发
 * 
 * @bk https://home.cnblogs.com/u/huanuan/
 * @简书 https://www.jianshu.com/u/d29cc7d7ca49
 * @Author 六月星辰
 * @Date 2020年1月11日
 */
@Slf4j
public class HandlerDispatcher implements Dispatcher {

	/**
	 * POST方式 业务内存
	 */
	private static Map<String, HanderServer> POST_HANDER = Maps.newConcurrentMap();

	/**
	 * GET方式业务内存
	 */
	private static Map<String, HanderServer> GET_HANDER = Maps.newConcurrentMap();

	@Override
	public void initHandler(Set<Class> clazzs) {
		// 业务处理器初始化
		if (CollectionUtils.isEmpty(clazzs)) {
			System.exit(0);// 处理器异常关闭处理器
			return;
		}
		try {
			for (Class clazz : clazzs) {
				Class[] interfaces = clazz.getInterfaces();
				boolean isHandler = false;
				for (Class class1 : interfaces) {
					if (class1.getSimpleName().equals("HttpServerHandle")) {
						isHandler = true;
					}
				}
				if (isHandler) {
					System.err.println("类 ： " + clazz + "  实现了  HttpServerHandle  接口    进行初始化业务处理器  -------------");
				} else {
					System.err.println("类  ： " + clazz + "   未实现   HttpServerHandle  接口 ");
					continue;
				}
				// 获取业务层的 指定的注解
				HttpParams hParams = (HttpParams) clazz.getAnnotation(HttpParams.class);
				if (null != hParams) {
					String requestMethod = hParams.method();// 请求的方式 ！
					if (StringUtils.isEmpty(requestMethod)) {
						continue;
					}
					Method[] declaredMethods = clazz.getDeclaredMethods();
					// Method[] methods = clazz.getMethods();
					// POST方式处理器内存存储
					switch (requestMethod) {
					case HttpMethod.POST:// POST方式处理器内存存储
						for (Method method : declaredMethods) {
							if (method.getName().equals("hanlde")) {
								Class paramType = method.getParameterTypes()[0];
								if (paramType.getSimpleName().equals("Object")) {
									continue;
								}
								POST_HANDER.put(hParams.url(), new HanderServer(clazz.newInstance(), method));
							}
						}
						break;

					case HttpMethod.GET:// GET方式处理器内存存储
						for (Method method : declaredMethods) {
							if (method.getName().equals("hanlde")) {
								Class paramType = method.getParameterTypes()[0];
								if (paramType.getSimpleName().equals("Object")) {
									continue;
								}
								GET_HANDER.put(hParams.url(), new HanderServer(clazz.newInstance(), method));
							}
						}
						break;
					default:
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static Object dispatcher(String method, String url, JSONObject params) throws Exception {
		// TODO Auto-generated method stub
		switch (method) {
		case HttpMethod.POST:
			HanderServer postHander = POST_HANDER.get(url);
			if (postHander == null) {
				return null;
			}
			Object[] argsValues = new Object[1];
			JSONObject response = new JSONObject();
			argsValues[0] = params;// 请求参数
			// argsValues[1] = response;
			Object invoke = null;
			invoke = postHander.method.invoke(postHander.o, argsValues);
			return invoke;
		case HttpMethod.GET:
			HanderServer getHander = GET_HANDER.get(url);
			if (getHander == null) {
				return null;
			}
			Object[] getArgsValues = new Object[1];
			getArgsValues[0] = params;
			Object getInvoke = null;
			getInvoke = getHander.method.invoke(getHander.o, getArgsValues);
			return getInvoke;
		default:
			break;
		}

		return null;
	}

}
