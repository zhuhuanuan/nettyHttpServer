package liu.yue.xin.chen.com.server;

import com.alibaba.fastjson.JSONObject;

/**
 * 业务层处理接口
 * 
 * @bk https://home.cnblogs.com/u/huanuan/
 * @简书 https://www.jianshu.com/u/d29cc7d7ca49
 * @Author 六月星辰
 * @Date 2020年1月11日
 */
public interface HttpServerHandle {
	/**
	 * 业务逻辑
	 * @param params 请求参数
	 * @bk https://home.cnblogs.com/u/huanuan/
	 * @Author 六月星辰
	 * @Date 2020年1月11日
	 * @return
	 */
	Object hanlde(JSONObject params);
}
