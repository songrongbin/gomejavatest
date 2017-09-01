package com.bins.xmly;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import com.google.common.collect.Maps;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Mac;

public class SignatureDemo {

    private static final String DEFAULT_ENCODING = "UTF-8";
	private static final String HMAC_SHA1 = "HmacSHA1";
	
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
		String sig = hmacSHA1ToStr(paramsEncodedStr, seed);
		System.out.printf("[" + paramsBuilder.toString() + "]");
		System.out.printf("[" + paramsEncodedStr + "]");
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
		return mac.doFinal(data);
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

	public static void main (String args[])
			throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {

		String app_key = "0eecd252fd06a7f00cdea8e806a70850";
		String app_secret = "c981fa130f4bf556ef4c9b7460de9bcf";             //secret
		String url = "http://api.ximalaya.com/openapi-gateway-app/tracks/get_batch";    //请求服务地址
		String serverAuthenticateStaticKey = "EXQttaTH";

		int client_os_type = 4;
		String nonce = "l5d%2BzR7fSdDjLimXN7BJZw%3D%3D";
		// $time    = time();
		Long timestamp = (new Date()).getTime();
		String ids = "251739,252045";


		Map<String, String> paramsMap = Maps.newHashMap();
		paramsMap.put("app_secret", app_secret);
		paramsMap.put("client_os_type", client_os_type + "");
		paramsMap.put("nonce", nonce);
		paramsMap.put("timestamp", timestamp + "");
		paramsMap.put("ids", ids);

		String seed = app_secret + serverAuthenticateStaticKey;
		String sig = caculateSignature(paramsMap, seed);
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





}
