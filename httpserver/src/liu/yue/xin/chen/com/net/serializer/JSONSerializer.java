package liu.yue.xin.chen.com.net.serializer;

import com.alibaba.fastjson.JSON;

/**
 * json序列化
 * 
 * @bk https://home.cnblogs.com/u/huanuan/
 * @简书 https://www.jianshu.com/u/d29cc7d7ca49
 * @Author 六月星辰
 * @Date 2020年1月11日
 */
public class JSONSerializer implements Serializer {

	@Override
	public byte[] serialize(Object object) {
		// TODO Auto-generated method stub
		return JSON.toJSONBytes(object);
	}

	@Override
	public <T> T deserialize(Class<T> clazz, byte[] bytes) {
		// TODO Auto-generated method stub
		return JSON.parseObject(bytes, clazz);
	}

}
