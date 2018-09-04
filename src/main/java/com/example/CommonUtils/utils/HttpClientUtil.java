package com.example.CommonUtils.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpMethod;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Weichang Zhong
 * @Date: 2018/9/4
 * @Time: 15:14
 * @Description:
 */
public class HttpClientUtil {

    private static PoolingHttpClientConnectionManager connectionManager;
    private static String UTF_8 = "UTF-8";

    public static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    private static void init() {
        if (connectionManager == null) {
            connectionManager = new PoolingHttpClientConnectionManager();
            connectionManager.setMaxTotal(50);// 整个连接池最大连接数
            connectionManager.setDefaultMaxPerRoute(5);// 每路由最大连接数，默认值是2
        }
    }

    /**
     * 通过连接池获取HttpClient
     *
     * @return
     */
    private static CloseableHttpClient getHttpClient() {
        init();
        return HttpClients.custom().setConnectionManager(connectionManager).build();
    }

    /**
     * 发送http get请求
     *
     * @param url 请求url
     * @return 请求结果
     * @throws URISyntaxException, IOException
     */
    public static String sendHttpGetRequest(String url) throws URISyntaxException, IOException {
        return sendHttpGetRequest(url, null, null);
    }

    /**
     * 发送http get请求
     *
     * @param url 请求url
     * @param params key value参数对
     * @return 请求结果
     * @throws URISyntaxException, IOException
     */
    public static String sendHttpGetRequest(String url, Map<String, String> params)
            throws URISyntaxException, IOException {
        return sendHttpGetRequest(url, params, null);
    }

    /**
     * 发送http get请求
     *
     * @param url 请求url
     * @param params key value参数对
     * @param headers http header
     * @return 请求结果
     * @throws URISyntaxException, IOException
     */
    public static String sendHttpGetRequest(String url, Map<String, String> params, Map<String, String> headers)
            throws URISyntaxException, IOException {
        HttpRequestBase httpRequestBase = getHttpRequestBase(url, params, headers, HttpMethod.GET);
        return getResult(httpRequestBase);
    }

    /**
     * 发送http get请求
     *
     * @param url 请求url
     * @return 状态码
     * @throws URISyntaxException, IOException
     */
    public static int getHttpGetRequestCode(String url) throws URISyntaxException, IOException {
        return getHttpGetRequestCode(url, null, null);
    }

    /**
     * 发送http get请求
     *
     * @param url 请求url
     * @param params key value参数对
     * @return 状态码
     * @throws URISyntaxException, IOException
     */
    public static int getHttpGetRequestCode(String url, Map<String, String> params)
            throws URISyntaxException, IOException {
        return getHttpGetRequestCode(url, params, null);
    }

    /**
     * 发送http get请求
     *
     * @param url 请求url
     * @param params key value参数对
     * @param headers http header
     * @return 状态码
     * @throws URISyntaxException, IOException
     */
    public static int getHttpGetRequestCode(String url, Map<String, String> params, Map<String, String> headers)
            throws URISyntaxException, IOException {
        HttpRequestBase httpRequestBase = getHttpRequestBase(url, params, headers, HttpMethod.GET);
        return getReturnCode(httpRequestBase);
    }

    /**
     * 发送http post请求
     *
     * @param url 请求url
     * @return 请求结果
     */
    public static String sendHttpPostRequest(String url) throws URISyntaxException, IOException {
        return sendHttpPostRequest(url, null, null);
    }

    /**
     * 发送http post请求
     *
     * @param url 请求url
     * @param params key value参数对
     * @return 请求结果
     * @throws IOException
     * @throws URISyntaxException
     */
    public static String sendHttpPostRequest(String url, Map<String, String> params)
            throws URISyntaxException, IOException {
        return sendHttpPostRequest(url, params, null);
    }

    /**
     * 发送http post请求
     *
     * @param url 请求url
     * @param params key value参数对
     * @param headers http header
     * @return 请求结果
     * @throws URISyntaxException, IOException
     */
    public static String sendHttpPostRequest(String url, Map<String, String> params, Map<String, String> headers)
            throws URISyntaxException, IOException {
        HttpRequestBase httpRequestBase = getHttpRequestBase(url, params, headers, HttpMethod.POST);
        return getResult(httpRequestBase);
    }

    /**
     * 发送http post请求
     *
     * @param url 请求url
     * @return 状态码
     * @throws URISyntaxException, IOException
     */
    public static int getHttpPostRequestCode(String url) throws URISyntaxException, IOException {
        return getHttpPostRequestCode(url, null, null);
    }

    /**
     * 发送http post请求
     *
     * @param url 请求url
     * @param params key value参数对
     * @return 状态码
     * @throws URISyntaxException, IOException
     */
    public static int getHttpPostRequestCode(String url, Map<String, String> params)
            throws URISyntaxException, IOException {
        return getHttpPostRequestCode(url, params, null);
    }

    /**
     * 发送http post请求
     *
     * @param url 请求url
     * @param params key value参数对
     * @param headers http header
     * @return 状态码
     * @throws URISyntaxException, IOException
     */
    public static int getHttpPostRequestCode(String url, Map<String, String> params, Map<String, String> headers)
            throws URISyntaxException, IOException {
        HttpRequestBase httpRequestBase = getHttpRequestBase(url, params, headers, HttpMethod.POST);
        return getReturnCode(httpRequestBase);
    }

    /**
     * 发送json请求
     *
     * @param url 请求url
     * @param obj 参数对象,会被转成json传到服务端
     * @return 请求结果
     * @throws IOException
     */
    public static String sendJsonRequest(String url, Object obj) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(new ObjectMapper().writeValueAsString(obj), UTF_8);
        entity.setContentEncoding(UTF_8);
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        return getResult(httpPost);
    }

    /**
     * 下载文件
     *
     * @param url 目标地址
     * @param filename 文件名
     * @return file 文件
     * @throws IOException
     * @throws Exception
     */
    // public static File download(String url, String filename) throws MalformedURLException, IOException {
    // // 打开连接
    // URLConnection con = new URL(url).openConnection();
    // // 输入流
    // InputStream is = con.getInputStream();
    //
    // File file = new File(filename);
    // OutputStream os = new FileOutputStream(file);
    // byte[] bs = new byte[1024];
    // int len;
    // // 开始读取
    // while ((len = is.read(bs)) != -1) {
    // os.write(bs, 0, len);
    // }
    // // 完毕，关闭所有链接
    // os.close();
    // is.close();
    // return file;
    // }

    private static HttpRequestBase getHttpRequestBase(String url, Map<String, String> params,
                                                      Map<String, String> headers, HttpMethod httpMethod)
            throws URISyntaxException, UnsupportedEncodingException {
        HttpRequestBase httpRequestBase = null;
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);

        // 构造请求参数
        if (httpMethod == HttpMethod.GET) {
            if (null != params && !params.isEmpty()) {
                List<NameValuePair> pairs = covertParams2NVPS(params);
                ub.setParameters(pairs);
            }
            httpRequestBase = new HttpGet(ub.build());
        } else if (httpMethod == HttpMethod.POST) {
            httpRequestBase = new HttpPost(url);
            if (null != params && !params.isEmpty()) {
                List<NameValuePair> pairs = covertParams2NVPS(params);
                ((HttpPost) httpRequestBase).setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
            }
        }

        // 构造请求头
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> param : headers.entrySet()) {
                httpRequestBase.addHeader(param.getKey(), param.getValue());
            }
        }
        return httpRequestBase;
    }

    private static List<NameValuePair> covertParams2NVPS(Map<String, String> params) {
        List<NameValuePair> pairs = Lists.newArrayList();
        for (Map.Entry<String, String> param : params.entrySet()) {
            pairs.add(new BasicNameValuePair(param.getKey(), param.getValue()));
        }
        return pairs;
    }

    /**
     * 返回请求结果
     *
     * @param request
     * @return 请求结果
     * @throws IOException
     */
    private static String getResult(HttpRequestBase request) throws IOException {
        String result = StringUtils.EMPTY;
        CloseableHttpClient httpClient = getHttpClient();
        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            result = EntityUtils.toString(entity);
        }
        response.close();
        return result;
    }

    /**
     * 得到请求结果的状态码
     *
     * @param request
     * @return 状态码
     * @throws IOException
     */
    private static int getReturnCode(HttpRequestBase request) throws IOException {
        CloseableHttpClient httpClient = getHttpClient();
        CloseableHttpResponse response = httpClient.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();
        response.close();
        return statusCode;
    }

    public static void download(String urlString, String path) throws IOException {
        String contentType = downloadWebWithHtml(urlString, path);
        if ((contentType.contains("text/html"))) {
            logger.info("因为是html，所以重新下载了哦");
            downloadWebWithHtml(urlString, path);
        }
    }

    public static String downloadWebWithHtml(String urlString, String path) throws IOException {
        long start = System.nanoTime();
        String contentType;
        InputStream in = null;
        OutputStream os = null;
        try {
            URL uri = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
            conn.setConnectTimeout(12000);
            conn.setReadTimeout(12000);
            conn.connect();
            in = conn.getInputStream();
            contentType = conn.getContentType();
            byte[] bs = new byte[1024];
            int len;
            validatePathExist(path);
            os = new FileOutputStream(path);
            while ((len = in.read(bs)) > 0) {
                os.write(bs, 0, len);
            }
        } finally {
            if (os != null)
                try {
                    os.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }
        }
        long end = System.nanoTime();
        logger.info("下载时间为" + (end - start) / 1000000 + "ms");
        logger.info(contentType);
        return contentType;
    }

    public static void validatePathExist(String file) {
        File f = new File(file);
        File parent = f.getParentFile();
        if (parent == null) {
            return;
        }
        if (!parent.exists()) {
            parent.mkdirs();
        }
    }
}