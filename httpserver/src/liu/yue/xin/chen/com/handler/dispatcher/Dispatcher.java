package liu.yue.xin.chen.com.handler.dispatcher;

import java.util.Set;

import com.alibaba.fastjson.JSONObject;

public interface Dispatcher {
	/**
	 * 初始化处理器
	 * 
	 * @param clazzs
	 * @bk https://home.cnblogs.com/u/huanuan/
	 * @Author 六月星辰
	 * @Date 2020年1月11日
	 */
	void initHandler(Set<Class> clazzs);
	
	/**
	 * 业务层的调用
	 * @param method 请求的方式：POST、GET
	 * @param url 请求的地址
	 * @param params 请求参数
	 * @throws Exception
	 * @bk https://home.cnblogs.com/u/huanuan/
	 * @Author 六月星辰
	 * @Date 2020年1月11日
	 * @return 业务层的业务处理结果
	 */
//	Object dispatcher(String method,String url,JSONObject params) throws Exception;
	
}
