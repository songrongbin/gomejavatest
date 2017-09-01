import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import com.google.common.collect.Maps;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Mac;

public class SignatureDemo {

	/*private static final String productDomain = "https://mpay.ximalaya.com/openapi-payfacade-app/open_pay/";
	private static final String productAppKey = "0eecd252fd06a7f00cdea8e806a70850";
	private static final String productAppSecret = "c981fa130f4bf556ef4c9b7460de9bcfO5UJZ6h0";*/

	private static final String productDomain = "http://api.ximalaya.com/openapi-gateway-app/tracks/get_batch";
	private static final String productAppKey = "0eecd252fd06a7f00cdea8e806a70850";
	private static final String productAppSecret = "c981fa130f4bf556ef4c9b7460de9bcf";
	private static final String clientOsType = "1";

    private static final String DEFAULT_ENCODING = "UTF-8";
	private static final String HMAC_SHA1 = "HmacSHA1";

	private static String nonce = "234";
	
	/**
	 * 计算参数的签名值 demo
	 * @param paramsMap
	 * @param seed
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws UnsupportedEncodingException 
	 */
	public static String caculateSignature(Map<String, String> paramsMap, String seed) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
		if(paramsMap == null || paramsMap.isEmpty() || StringUtils.isEmpty(seed)) {
			throw new IllegalArgumentException("paramsMap and seed should not empty");
		}
		
		if(!(paramsMap instanceof TreeMap<?, ?>)) {
			paramsMap = new TreeMap<String, String> (paramsMap);
		}
		StringBuilder paramsBuilder = new StringBuilder();
		for(Map.Entry<String, String> entry: paramsMap.entrySet()) {
			paramsBuilder.append(entry.getKey());
			paramsBuilder.append("=");
			paramsBuilder.append(entry.getValue());
			paramsBuilder.append("&");
		}
		paramsBuilder.deleteCharAt(paramsBuilder.length()-1);
		String paramsEncodedStr = base64Encode(paramsBuilder.toString());

		System.out.println("paramsEncodedStr:=" + paramsEncodedStr);

		String sig = hmacSHA1ToStr(paramsEncodedStr, seed);
		System.out.println("sig:=" + sig);

		System.out.printf("[" + paramsBuilder.toString() + "]");
		System.out.printf("[" + paramsEncodedStr + "]");
		System.out.printf("[" + sig + "]");
		return sig;
	}

	public static String caculateSignature(String source, String seed) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
		String sig = hmacSHA1ToStr(source, seed);
		System.out.println("sig:=" + sig);

		System.out.printf("[" + sig + "]");
		return sig;
	}

	/**
	 * HMAC SHA1签名或摘要算法
	 * @param data
	 * @param key
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 */
	private static String hmacSHA1ToStr(String data, String key) throws InvalidKeyException, NoSuchAlgorithmException {
		if(data == null || key == null) {
			return null;
		}
		return hmacSHA1ToStr(data.getBytes(), key.getBytes());
	}

		/**
	 * HMAC SHA1签名或摘要算法
	 * @param data 待摘要的字节数据
	 * @param key  使用的key
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	private static String hmacSHA1ToStr(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException  {
		return DigestUtils.md5Hex(hmacSHA1(data, key));
	}

	private static byte[] hmacSHA1(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
		if(data == null || key == null) {
			return null;
		}
		
		SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);
		Mac mac = Mac.getInstance(HMAC_SHA1);
		mac.init(signingKey);
		byte[] hmacSHA1String = mac.doFinal(data);
		System.out.println("hmacSHA1String:=" + hmacSHA1String);
		return hmacSHA1String;
	}

	/**
	 * Base64编码
	 * @param data 字符串数据
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private static String base64Encode(String data) throws UnsupportedEncodingException {
		if(data == null) {
			return null;
		}
		return Base64.encodeBase64String(data.getBytes(DEFAULT_ENCODING));
	}

	public static void main (String args[]) throws NoSuchAlgorithmException, InvalidKeyException, IOException {

		 xmly();
		// testXmly();
		//String s = "YXBwX2tleT0wZWVjZDI1MmZkMDZhN2YwMGNkZWE4ZTgwNmE3MDg1MCZjbGllbnRfb3NfdHlw";
		//System.out.println(s);
		//System.out.println(hmacSHA1ToStr(s, "0eecd252fd06a7f00cdea8e806a70850"));

		//String paramsEncodedStr = base64Encode("dfdfdfdfdfdfdfdfdf");
		//String s1 = caculateSignature(paramsEncodedStr, "c981fa130f4bf556ef4c9b7460de9bcfO5UJZ6h0");
		//System.out.println("s1=:" + s1);
	}



	public static void xmly() throws NoSuchAlgorithmException, InvalidKeyException, IOException {
		System.out.println("===test place order ===");
		Map<String, String> commonParams = getDvdParamMap();
		String ids = "47427001";
		commonParams.put("ids", ids);
		commonParams.put("app_key", productAppKey);
		// 计算签名值
		String sig = caculateSignature(commonParams, productAppSecret + "O5UJZ6h0");
		System.out.println("sig: " + sig);
		commonParams.put("sig", sig);

		// String app_key = "0eecd252fd06a7f00cdea8e806a70850";
		// 674d74f9c1e0112819edda6811de62c5
		// 674d74f9c1e0112819edda6811de62c5

		// String app_secret = "c981fa130f4bf556ef4c9b7460de9bcf";             //secret
		// String url = "http://api.ximalaya.com/openapi-gateway-app/tracks/get_batch";    //请求服务地址
		// String serverAuthenticateStaticKey = "O5UJZ6h0";

		// int client_os_type = 1;
		// String nonce = "sdsd";
		// $time    = time();
		//Long timestamp = (new Date()).getTime();
		// String ids = "251739,252045";

		// String params = "?app_key=" + productAppKey + "&client_os_type=" + clientOsType + "&nonce=" + nonce + "&timestamp=" + timestamp + "&ids=" + ids + "&sig=" + sig;
		// String xmly_url = url + params;
		String url = productDomain;

		commonParams.put("sig", sig);
		System.out.println(sendGetCall(url, commonParams));
	}



	private static Map<String, String> getDvdParamMap() {
		Map<String, String> paramsMap = Maps.newHashMap();
		paramsMap.put("client_os_type", "1");            // ==========
		// paramsMap.put("third_uid", "2017081014");//13344449999
		//        commonParams.put("third_uid_type", "2");
		paramsMap.put("nonce", "123");
		 paramsMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
		// paramsMap.put("timestamp", "1503049813627");

		/*String ids = "47427001";
		Map<String, String> paramsMap = Maps.newHashMap();
		paramsMap.put("app_key", productAppKey);
		paramsMap.put("client_os_type", clientOsType);
		paramsMap.put("nonce", nonce);
		paramsMap.put("third_uid", "2017081014");1503049813627
		// paramsMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
		paramsMap.put("timestamp", "1503049813627");
		paramsMap.put("ids", ids);*/
		return paramsMap;
	}

	private static String sendPostCall(String url, Map<String, String> commonParams) throws IOException {
		HttpPost httppost=new HttpPost(url);
		HttpClient httpClient = new DefaultHttpClient();
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : commonParams.entrySet()) {
			list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}

		if(list.size() > 0){
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, HTTP.UTF_8);
			httppost.setEntity(entity);
		}

		String result = "";
		HttpResponse response = httpClient.execute(httppost);
		if(response != null){
			HttpEntity resEntity = response.getEntity();
			if(resEntity != null){
				result = EntityUtils.toString(resEntity, HTTP.UTF_8);
			}
		}

		return result;
	}

	private static String sendGetCall(String url, Map<String, String> paramsMap) throws IOException {
		StringBuilder paramsBuilder = new StringBuilder();
		for(Map.Entry<String, String> entry: paramsMap.entrySet()) {
			paramsBuilder.append(entry.getKey());
			paramsBuilder.append("=");
			paramsBuilder.append(entry.getValue());
			paramsBuilder.append("&");
		}
		paramsBuilder.deleteCharAt(paramsBuilder.length()-1);

		String fullUrl = url + "?" + paramsBuilder.toString();

		System.out.println("fullUrl:=" + fullUrl);

		String result= "";
		//      HttpGet httpRequst = new HttpGet(URI uri);
		//      HttpGet httpRequst = new HttpGet(String uri);
		//      创建HttpGet或HttpPost对象，将要请求的URL通过构造方法传入HttpGet或HttpPost对象。
		HttpGet httpRequst = new HttpGet(fullUrl);

		//      new DefaultHttpClient().execute(HttpUriRequst requst);
		try {
			//使用DefaultHttpClient类的execute方法发送HTTP GET请求，并返回HttpResponse对象。
			HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);//其中HttpGet是HttpUriRequst的子类
			if(httpResponse.getStatusLine().getStatusCode() == 200)
			{
				HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity);//取出应答字符串
				// 一般来说都要删除多余的字符
				result.replaceAll("\r", "");//去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
			} else{
				httpRequst.abort();
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.getMessage().toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.getMessage().toString();
		}
		System.out.println("result:=" + result);
		return result;
	}

	public static void testXmly() throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
		String app_key = "0eecd252fd06a7f00cdea8e806a70850";
		String app_secret = "c981fa130f4bf556ef4c9b7460de9bcf";             //secret
		String url = "http://api.ximalaya.com/openapi-gateway-app/tracks/get_batch";    //请求服务地址
		String serverAuthenticateStaticKey = "O5UJZ6h0";

		int client_os_type = 4;
		String nonce = "l5d%2BzR7fSdDjLimXN7BJZw%3D%3D";
		// $time    = time();
		Long timestamp = (new Date()).getTime();
		String ids = "251739,252045";


		Map<String, String> paramsMap = Maps.newHashMap();
		paramsMap.put("app_key", app_key);
		paramsMap.put("client_os_type", clientOsType);
		paramsMap.put("nonce", nonce);
		paramsMap.put("timestamp", timestamp + "");
		paramsMap.put("ids", ids);

		String seed = app_secret + serverAuthenticateStaticKey;
		String sig = caculateSignature(paramsMap, app_secret);
		System.out.println(sig);

		String params = "?app_key=" + app_key + "&client_os_type=" + client_os_type + "&nonce=" + nonce + "&timestamp=" + timestamp + "&ids=" + ids + "&sig=" + sig;
		String xmly_url = url + params;
		System.out.println(xmly_url);


		//1.使用默认的配置的httpclient
		CloseableHttpClient client = HttpClients.createDefault();
		//2.使用get方法
		HttpGet httpGet = new HttpGet(xmly_url);
		InputStream inputStream = null;
		CloseableHttpResponse response = null;

		try {
			//3.执行请求，获取响应
			response = client.execute(httpGet);
			//看请求是否成功，这儿打印的是http状态码
			System.out.println(response.getStatusLine().getStatusCode());
			//4.获取响应的实体内容，就是我们所要抓取得网页内容
			HttpEntity entity = response.getEntity();
			//5.将其打印到控制台上面
			//方法一：使用EntityUtils
			if (entity != null) {
				System.out.println(EntityUtils.toString(entity, "utf-8"));
			}
			EntityUtils.consume(entity);
		} catch (UnsupportedOperationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	private static Map<String, String> getCommonParamMap() {
		Map<String, String> commonParams = new HashMap<String, String>();
		//        commonParams.put("device_id", "123456789");
		commonParams.put("client_os_type", clientOsType);            // ==========
		commonParams.put("third_uid", "2017081014");//13344449999
		//        commonParams.put("third_uid_type", "2");
		commonParams.put("nonce", nonce);
		commonParams.put("timestamp", String.valueOf(System.currentTimeMillis()));
		return commonParams;
	}



}
