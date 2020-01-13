package liu.yue.xin.chen.com.net.serializer;

/**
 * json数据封装
 * 
 * @bk https://home.cnblogs.com/u/huanuan/
 * @简书 https://www.jianshu.com/u/d29cc7d7ca49
 * @Author 六月星辰
 * @Date 2020年1月13日
 */
public class JsonResult {

	public static final int SUCCESS = 200;
	public static final int ERROR = 201;
	/**
	 * 返回是否成功的状态,200表示成功, 201或其他值 表示失败
	 */
	private int state = SUCCESS;
	/**
	 * 成功时候,返回的JSON数据
	 */
	private Object data = null;
	/**
	 * 是错误时候的错误消息
	 */
	private String message = null;

	/**
	 * 服务器的时间
	 */
	private long sysTime = System.currentTimeMillis();

	public JsonResult() {
	}

	public JsonResult(int state, Object data, String message) {
		this.state = state;
		this.data = data;
		this.message = message;
	}

	public JsonResult(Throwable e) {
		state = ERROR;
		data = null;
		message = e.getMessage();
	}

	public JsonResult(Object data) {
		state = SUCCESS;
		this.data = data;
		message = "";
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getSysTime() {
		return sysTime;
	}

	public void setSysTime(long sysTime) {
		this.sysTime = sysTime;
	}

	@Override
	public String toString() {
		return "JsonResult [state=" + state + ", data=" + data + ", message=" + message + ",sysTime=" + sysTime + "]";
	}

}
