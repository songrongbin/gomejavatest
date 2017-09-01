package com.bins.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static final String TYPE_JSON = "application/json";

    public static final String TYPE_HTML = "text/html";

    public static final String CHARSET = "UTF-8";

    /**
     * 以get方式请求，无参数,返回结果是json
     */
    public static String get(String url) {
        return get(url, null, TYPE_JSON);
    }

    /**
     * 以get方式请求,返回结果是json
     */
    public static String getJson(String url, Map<String, Object> param) {
        return get(url, param, TYPE_JSON);
    }

    /**
     * 以get方式请求,返回结果是html
     */
    public static String getHtml(String url, Map<String, Object> param) {
        return get(url, param, TYPE_HTML);
    }

    /**
     * 以get方式请求
     *
     * @param url   待请求的url
     * @param param 待请求的参数，Map<String,Object>
     * @param type  返回类型，HttpUtil.TYPE_JSON,HttpUtil.TYPE_HTML
     * @return
     */
    public static String get(String url, Map<String, Object> param, String type) {
        url += getUrlParam(param);
        String result = null;
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("Accept", TYPE_JSON);
            httpGet.addHeader("Accept-Charset", CHARSET);
            CloseableHttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
            response.close();
            httpclient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 无参数post提交，返回json
     */
    public static String post(String url) {
        return postJson(url, null);
    }

    /**
     * post方式提交json数据并返回json数据
     */
    public static String postJson(String url, String param) {
        return postJson(url, param, TYPE_JSON);
    }

    /**
     * 以post方式提交json数据并返回json数据
     */
    public static String postForm(String url, Map<String, Object> param) {
        return postForm(url, param, TYPE_JSON);
    }

    /**
     * Accept : application/json
     * <p>
     * 以post方式提交json数据，返回结果类型由returnType决定
     *
     * @param url        待请求的url
     * @param param      待请求的参数，Map<String,Object>
     * @param returnType 返回类型，HttpUtil.TYPE_JSON,HttpUtil.TYPE_HTML
     * @return
     */
    public static String postJson(String url, String param, String returnType) {
        String result = "";
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Accept", returnType);
            httpPost.addHeader("Accept-Charset", CHARSET);
            StringEntity requestEntity = new StringEntity(param, CHARSET);
            requestEntity.setContentType(TYPE_JSON);
            httpPost.setEntity(requestEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                result = EntityUtils.toString(responseEntity, CHARSET);
            }
            response.close();
            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Accept : application/x-www-form-urlencoded
     * <p>
     * 以post方式提交form数据，返回结果类型由returnType决定
     *
     * @param url        待请求的url
     * @param params     待请求的参数，
     * @param returnType 返回类型，HttpUtil.TYPE_JSON,HttpUtil.TYPE_HTML
     * @return
     */
    public static String postForm(String url, Map<String, Object> params, String returnType) {
        String result = null;
        /*try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Accept", returnType);
            httpPost.addHeader("Accept-Charset", CHARSET);
            List<NameValuePair> formParams = new ArrayList<NameValuePair>();
            if (params != null && params.size() > 0) {
                for (String key : params.keySet()) {
                    formParams.add(new BasicNameValuePair(key, "" + params.get(key)));
                }
            }
            UrlEncodedFormEntity requestEntity = new UrlEncodedFormEntity(formParams, CHARSET);
            httpPost.setEntity(requestEntity);
            url = URLDecoder.decode(url, CHARSET);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                result = EntityUtils.toString(responseEntity, CHARSET);
            }
            response.close();
            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return result;

    }

    /**
     * 将map参数转化为url参数
     */
    public static String getUrlParam(Map<String, Object> param) {
        StringBuffer url = new StringBuffer();
        // 组装参数
        if (param != null && param.size() > 0) {
            url.append("?");
            for (String key : param.keySet()) {
                try {
                    url.append(key + "=" + URLEncoder.encode(param.get(key).toString(), CHARSET) + "&");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            url.deleteCharAt(url.length() - 1);
        }
        return url.toString();
    }

    /**
     * Accept : application/x-www-form-urlencoded
     * <p>
     * 以post方式提交form数据，返回结果类型由returnType决定
     *
     * @param url        待请求的url
     * @param params     待请求的参数
     * @param returnType 返回类型，HttpUtil.TYPE_JSON,HttpUtil.TYPE_HTML
     * @param charset
     * @param timeout
     * @return
     */
    public static String postForm(String url, Map<String, String> params, String returnType, String charset,
            Integer timeout) {
        String result = null;
        /*try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout)
                    .build();//设置请求和传输超时时间
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Accept", returnType);
            httpPost.addHeader("Accept-Charset", charset);
            httpPost.setConfig(requestConfig);
            List<NameValuePair> formParams = new ArrayList<NameValuePair>();
            if (params != null && params.size() > 0) {
                for (String key : params.keySet()) {
                    formParams.add(new BasicNameValuePair(key, params.get(key)));
                }
            }
            UrlEncodedFormEntity requestEntity = new UrlEncodedFormEntity(formParams, charset);
            httpPost.setEntity(requestEntity);
            url = URLDecoder.decode(url, charset);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                result = EntityUtils.toString(responseEntity, charset);
            }
            response.close();
            httpClient.close();
        } catch (Exception e) {
            logger.error(String.format("postForm eror url =%s", url), e);
        }*/
        return result;

    }

    public static void main(String[] args) {
        String url = "http://112.126.95.145:8083/job/client";
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject json = new JSONObject();
        json.put("jobExecUrl", "http://y2.bravetime.net/suppliers/erp/job/exec/refund");
        json.put("data", 6045);
        json.put("needFailRetry", "1");
        json.put("repeatIntervalArray", "3,2,1");
        json.put("maxRetryTimes", "3");
        map.put("job", json);
        String result = HttpUtils.postForm(url, map);
        System.out.println(result);
    }

}
