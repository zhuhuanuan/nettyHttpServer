package liu.yue.xin.chen.com.handler.dispatcher;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * HTTP 请求标签
 * 
 * @bk https://home.cnblogs.com/u/huanuan/
 * @简书 https://www.jianshu.com/u/d29cc7d7ca49
 * @Author 六月星辰
 * @Date 2020年1月11日
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface HttpParams {

	/**
	 * 请求的方式
	 * 
	 * @bk https://home.cnblogs.com/u/huanuan/
	 * @Author 六月星辰
	 * @Date 2020年1月11日
	 * @return
	 */
	String method();

	/**
	 * 请求的地址
	 * 
	 * @bk https://home.cnblogs.com/u/huanuan/
	 * @Author 六月星辰
	 * @Date 2020年1月11日
	 * @return
	 */
	String url();
}
