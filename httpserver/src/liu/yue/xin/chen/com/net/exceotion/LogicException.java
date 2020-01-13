package liu.yue.xin.chen.com.net.exceotion;

/**
 * 自定义逻辑异常
 * 
 * @bk https://home.cnblogs.com/u/huanuan/
 * @简书 https://www.jianshu.com/u/d29cc7d7ca49
 * @Author 六月星辰
 * @Date 2020年1月13日
 */
public class LogicException extends RuntimeException {

	/**
	 * 异常码
	 */
	private final int code;
	/**
	 * 异常信息
	 */
	private final String resultInfo;

	/**
	 * <p>
	 * Title:
	 * </p>
	 *
	 * <p>
	 * Description:异常构造器
	 * </p>
	 *
	 * @param code
	 */
	public LogicException(int code) {
		this.code = code;
		this.resultInfo = "";
	}

	/**
	 * <p>
	 * Title:
	 * </p>
	 *
	 * <p>
	 * Description:异常构造器
	 * </p>
	 *
	 * @param code
	 */
	public LogicException(int code, String resultInfo) {
		this.code = code;
		this.resultInfo = resultInfo;
	}

	public int getCode() {
		return code;
	}

	public String getResultInfo() {
		return resultInfo;
	}
}
