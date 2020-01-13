package liu.yue.xin.chen.com.net.serializer;

/**
 * 序列化接口
 * 
 * @bk https://home.cnblogs.com/u/huanuan/
 * @简书 https://www.jianshu.com/u/d29cc7d7ca49
 * @Author 六月星辰
 * @Date 2020年1月11日
 */
public interface Serializer {
	/**
	 * java 对象转换成二进制
	 * 
	 * @param object
	 * @return
	 * @bk https://home.cnblogs.com/u/huanuan/
	 * @Author 六月星辰
	 * @Date 2020年1月11日
	 */
	byte[] serialize(Object object);

	/**
	 * 二进制转换成 java 对象
	 * 
	 * @param clazz
	 * @param bytes
	 * @return
	 * @bk https://home.cnblogs.com/u/huanuan/
	 * @Author 六月星辰
	 * @Date 2020年1月11日
	 */
	<T> T deserialize(Class<T> clazz, byte[] bytes);
}
