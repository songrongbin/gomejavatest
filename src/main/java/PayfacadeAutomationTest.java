import org.apache.commons.codec.binary.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import org.springframework.util.StringUtils;

/**
 * Created by jackie on 17/4/26.
 */
public class PayfacadeAutomationTest {
    private static final String productDomain = "https://mpay.ximalaya.com/openapi-payfacade-app/open_pay/";
    private static final String productAppKey = "0eecd252fd06a7f00cdea8e806a70850";
    private static final String productAppSecret = "c981fa130f4bf556ef4c9b7460de9bcfO5UJZ6h0";



    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final String HMAC_SHA1 = "HmacSHA1";


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
        return org.apache.commons.codec.binary.Base64.encodeBase64String(data.getBytes(DEFAULT_ENCODING));
    }




    public static void main(String args[]) throws NoSuchAlgorithmException, InvalidKeyException, IOException {
        System.out.println("===test place order ===");
        Map<String, String> commonParams = getCommonParamMap();
        commonParams.put("app_key", productAppKey);
        commonParams.put("price_type", "2");
        commonParams.put("pay_content", "6922889");    // 20482799,20787226        6022071
        commonParams.put("price", "0.3");   // 0.8    0.2

        // 计算签名值
        String sig = caculateSignature(commonParams, productAppSecret);
        System.out.println("sig: " + sig);
        commonParams.put("sig", sig);

        String url = productDomain + "place_order";
        System.out.println(sendPostCall(url, commonParams));
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

    private static String sendGetCall(String url) throws IOException {
        String result = null;
        HttpGet httpGet = new HttpGet(url);
        HttpClient httpClient = new DefaultHttpClient();

        HttpResponse httpResponse = httpClient.execute(httpGet);
        if(httpResponse.getStatusLine().getStatusCode() == 200)
        {
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);//取出应答字符串
            // 一般来说都要删除多余的字符
            result.replaceAll("\r", "");//去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
        }

        return result;
    }

    private static Map<String, String> getCommonParamMap() {
        Map<String, String> commonParams = new HashMap<String, String>();
//        commonParams.put("device_id", "123456789");
        commonParams.put("client_os_type", "1");            // ==========
        commonParams.put("third_uid", "2017081014");//13344449999
//        commonParams.put("third_uid_type", "2");
        commonParams.put("nonce", "123");
        commonParams.put("timestamp", String.valueOf(System.currentTimeMillis()));
        return commonParams;
    }
}
