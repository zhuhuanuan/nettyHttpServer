package liu.yue.xin.chen.com.handler;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.net.util.Charsets;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.util.AsciiString;
import liu.yue.xin.chen.com.handler.dispatcher.HandlerDispatcher;
import liu.yue.xin.chen.com.net.exceotion.LogicException;
import liu.yue.xin.chen.com.net.serializer.JSONSerializer;
import liu.yue.xin.chen.com.net.serializer.JsonResult;

/**
 * http消息处理器
 * 
 * @bk https://home.cnblogs.com/u/huanuan/
 * @简书 https://www.jianshu.com/u/d29cc7d7ca49
 * @Author 六月星辰
 * @Date 2020年1月11日
 */
public class HttpRequestHandler extends SimpleChannelInboundHandler<HttpObject> {

	private HttpHeaders headers;
	private HttpRequest request;
	private FullHttpRequest fullRequest;

	private static final String FAVICON_ICO = "/favicon.ico";
	private static final AsciiString CONTENT_TYPE = AsciiString.of("Content-Type");
	private static final AsciiString CONTENT_LENGTH = AsciiString.of("Content-Length");
	private static final AsciiString CONNECTION = AsciiString.of("Connection");
	private static final AsciiString KEEP_ALIVE = AsciiString.of("keep-alive");

	private static final AsciiString ACCESS_CONTROL_ALLOW_ORIGIN = AsciiString.of("Access-Control-Allow-Origin");
	private static final AsciiString ACCESS_CONTROL_ALLOW_HEADERS = AsciiString.of("Access-Control-Allow-Headers");
	private static final AsciiString ACCESS_CONTROL_ALLOW_METHODS = AsciiString.of("Access-Control-Allow-Methods");
	private static final AsciiString ACCESS_CONTROL_ALLOW_CREDENTIALS = AsciiString.of("Access-Control-Allow-Credentials");

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
		// TODO Auto-generated method stub
		if (msg instanceof HttpRequest) {
			request = (HttpRequest) msg;
			headers = request.headers();
			String uri = request.uri();
			if (uri.equals(FAVICON_ICO)) {
				return;
			}
			HttpMethod method = request.method();// 获取请求的方式
			if (method.equals(HttpMethod.GET)) {
				System.err.println("收到GET的请求！ 请求地址 = " + uri);
				QueryStringDecoder queryDecoder = new QueryStringDecoder(uri, Charsets.toCharset(CharEncoding.UTF_8));
				Map<String, List<String>> uriAttributes = queryDecoder.parameters();
				// 此处仅打印请求参数（你可以根据业务需求自定义处理）
				for (Map.Entry<String, List<String>> attr : uriAttributes.entrySet()) {
					for (String attrVal : attr.getValue()) {
						System.err.println("Key = " + attr.getKey() + "  values = " + attrVal);
					}
				}
				String path = queryDecoder.path();
				System.err.println("请求的路径 = " + path);
				// TODO 这里进行分发操作，这里就不编写分发了
				getDispense(ctx, path, uriAttributes);
				JsonResult jsonResult = new JsonResult();
				JSONSerializer jsonSerializer = new JSONSerializer();
				responsePush(ctx, jsonSerializer.serialize(jsonResult));
			} else if (method.equals(HttpMethod.POST) || method.equals(HttpMethod.OPTIONS)) {
				System.err.println("收到POST或者OPTIONS的请求！ 请求地址 = " + uri);
				// POST请求,由于你需要从消息体中获取数据,因此有必要把msg转换成FullHttpRequest
				fullRequest = (FullHttpRequest) msg;
				// 分发操作
				postDispense(ctx, uri);
			} else if (method.equals(HttpMethod.HEAD)) {
				System.err.println("收到HEAD请求！ 请求地址 = " + uri);
			} else {
				System.err.println("收到其他方式的请求！ 请求的地址 = " + uri);
			}
		}

	}

	/**
	 * GET请求，请求分发操作
	 * 
	 * @param ctx
	 *            请求的用户
	 * @param path
	 *            请求的地址
	 * @param uriAttributes
	 *            请求的参数
	 * @bk https://home.cnblogs.com/u/huanuan/
	 * @Author 六月星辰
	 * @Date 2020年1月13日
	 */
	private void getDispense(ChannelHandlerContext ctx, String path, Map<String, List<String>> uriAttributes) {
		// TODO 请求的分发操作
		System.err.println("暂时不进行分发操作！");
	}

	/**
	 * post请求，请求进行分发操作
	 * 
	 * @param ctx
	 *            用户的链接
	 * @param uri
	 *            用户请求的地址
	 * @bk https://home.cnblogs.com/u/huanuan/
	 * @Author 六月星辰
	 * @Date 2020年1月13日
	 */
	private void postDispense(ChannelHandlerContext ctx, String url) {
		// TODO Auto-generated method stub
		String typeStr2 = headers.get("Accept").toString();
		String[] list = typeStr2.split(";");
		String contentType = list[0];
		switch (contentType) {
		case "application/json":
			byte[] content = null;
			String jsonStr = fullRequest.content().toString(Charsets.toCharset(CharEncoding.UTF_8));
			JSONObject params = JSON.parseObject(jsonStr);
			try {
				Object response = HandlerDispatcher.dispatcher(liu.yue.xin.chen.com.handler.dispatcher.HttpMethod.POST, url, params);
				if (response == null) {
					JsonResult jsonResult = new JsonResult();
					JSONSerializer jsonSerializer = new JSONSerializer();
					content = jsonSerializer.serialize(jsonResult);
				} else {
					JsonResult jsonResult = new JsonResult(response);
					JSONSerializer jsonSerializer = new JSONSerializer();
					content = jsonSerializer.serialize(jsonResult);
				}
				responsePush(ctx, content);
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				if (e.getTargetException() instanceof LogicException) {
					// 如果是主动抛出异常
					LogicException e1 = (LogicException) e.getTargetException();
					JsonResult jsonResult = new JsonResult();
					jsonResult.setState(e1.getCode());
					JSONSerializer jsonSerializer = new JSONSerializer();
					content = jsonSerializer.serialize(jsonResult);
					responsePush(ctx, content);
				} else {
					// 非主动抛出异常
					JsonResult jsonResult = new JsonResult();
					jsonResult.setState(-1);
					JSONSerializer jsonSerializer = new JSONSerializer();
					content = jsonSerializer.serialize(jsonResult);
					responsePush(ctx, content);

				}
			} catch (Exception e) {
				JsonResult jsonResult = new JsonResult();
				JSONSerializer jsonSerializer = new JSONSerializer();
				content = jsonSerializer.serialize(jsonResult);
				responsePush(ctx, content);
			}
			break;
		case "application/x-www-form-urlencoded":
			System.err.println("接受到application/x-www-form-urlencoded类型的请求！暂时不进行处理操作！");
			break;
		case "multipart/form-data":
			System.err.println("接受到multipart/form-data类型的请求！暂时不进行处理操作！");
			break;
		default:
			System.err.println("服务器不进行其他请求方式的业务处理！");
			break;
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		System.err.println("链接异常！ ");
		cause.printStackTrace();
		ctx.close();
	}

	/**
	 * 响应信息
	 * 
	 * @param ctx
	 *            响应的玩家 </br>
	 * @param content
	 *            响应的数据</br>
	 * @bk https://home.cnblogs.com/u/huanuan/
	 * @Author 六月星辰
	 * @Date 2020年1月11日
	 */
	private void responsePush(ChannelHandlerContext ctx, byte[] content) {
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(content));
		// 允许跨域访问
		response.headers().set(CONTENT_TYPE, "application/json;charset=UTF-8");
		response.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		response.headers().set(ACCESS_CONTROL_ALLOW_HEADERS, "*");// 允许headers自定义
		response.headers().set(ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT,DELETE");
		response.headers().set(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
		response.headers().set(CONNECTION, KEEP_ALIVE);
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}

}
