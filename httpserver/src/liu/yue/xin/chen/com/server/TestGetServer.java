package liu.yue.xin.chen.com.server;

import com.alibaba.fastjson.JSONObject;

import liu.yue.xin.chen.com.handler.dispatcher.HttpMethod;
import liu.yue.xin.chen.com.handler.dispatcher.HttpParams;

/**
 * HTTP GET测试接口
 * 
 * @bk https://home.cnblogs.com/u/huanuan/
 * @简书 https://www.jianshu.com/u/d29cc7d7ca49
 * @Author 六月星辰
 * @Date 2020年1月11日
 */
@HttpParams(method = HttpMethod.GET, url = "/test")
public class TestGetServer implements HttpServerHandle {

	@Override
	public Object hanlde(JSONObject params) {
		// TODO Auto-generated method stub 
		System.err.println("收到get请求！");
		JSONObject data = new JSONObject();
		data.put("msg", "你好");
		return data;
	}

}
