package liu.yue.xin.chen.com.server;

import com.alibaba.fastjson.JSONObject;

import liu.yue.xin.chen.com.handler.dispatcher.HttpMethod;
import liu.yue.xin.chen.com.handler.dispatcher.HttpParams;

/**
 * Http POST请求测试接口
 * 
 * @bk https://home.cnblogs.com/u/huanuan/
 * @简书 https://www.jianshu.com/u/d29cc7d7ca49
 * @Author 六月星辰
 * @Date 2020年1月11日
 */
@HttpParams(method = HttpMethod.POST, url = "")
public class TestPostServer implements HttpServerHandle {

	@Override
	public Object hanlde(JSONObject params) {
		// TODO Auto-generated method stub
		return null;
	}

}
