package com.example.CommonUtils.main;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.CommonUtils.bean.PersonBean;
import com.example.CommonUtils.utils.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Weichang Zhong
 * @Date: 2018/9/4
 * @Time: 15:56
 * @Description:
 */
@RestController
public class HttpClientMain extends Exception{

    public static final String BASE_URL = "http://10.240.222.75:8089/person/";

    public static final String GET_PERSON_LIST = "list";

    public static final String DOWNLOAD_URL = "https://ar-scene-source.nosdn.127.net/6bc9f90b-5fd2-46cf-a28a-b020c9477e3c";

    public static final String LOCAL_FILE_PATH = "E:\\MyProject\\pic.png";

    public static void main(String[] args) throws Exception{

        HttpClientMain sendController = new HttpClientMain();

        PersonBean personBean = new PersonBean();
        personBean.setName("postName");
        personBean.setAge(333);
        personBean.setAddress("postttttt");
        JSONObject jsonObject =  (JSONObject) JSON.toJSON(personBean);
        //下载DOWNLOAD_URL文件到本地LOCAL_FILE_PATH文件
        HttpClientUtil.download(DOWNLOAD_URL, LOCAL_FILE_PATH);

        // post方式传递json对象
        sendController.httpPostJsonTest(BASE_URL, jsonObject);
        // patch方法传递json对象
        personBean.setId(8L);
        personBean.setName("patchName");
        jsonObject =  (JSONObject) JSON.toJSON(personBean);
        sendController.httpPatchTest(BASE_URL, jsonObject);
        // get请求
        sendController.httpGetTest(BASE_URL + GET_PERSON_LIST);
        // 获取get请求的状态码
        int status = HttpClientUtil.getHttpGetRequestCode(BASE_URL+GET_PERSON_LIST);
    }

    /**
     * post 请求方法，参数为json对象
     * @param url
     * @param object
     * @throws Exception
     */
    public void httpPostJsonTest(final String url, final Object object) throws Exception{
        String result = StringUtils.EMPTY;
        try {
            result = HttpClientUtil.sendJsonRequest(url, object);
        }catch (Exception e) {
            throw new Exception("远程服务出现未知错误");
        }

        JSONObject jsonObject = JSON.parseObject(result);
        if((Integer) jsonObject.get("status") != 200) {
            System.out.println(result);
            throw new Exception("保存失败");
        }

        System.out.println("保存成功");
    }

    /**
     * Path 请求方法
     * @param url
     * @param object
     * @throws Exception
     */
    public void httpPatchTest(final String url, final Object object) throws Exception {
        String result = StringUtils.EMPTY;
        try {
            result = HttpClientUtil.sendHttpPatchRequest(url, object);
        }catch (Exception e) {
            e.printStackTrace();
            throw new Exception("远程服务出现未知错误");
        }

        JSONObject jsonObject = JSON.parseObject(result);
        if((Integer) jsonObject.get("status") != 200) {
            System.out.println(result);
            throw new Exception("修改失败");
        }

        System.out.println("修改成功");
    }

    /**
     * get 请求方法
     * @param url
     * @throws Exception
     */
    public void httpGetTest(final String url) throws Exception{
        String result = StringUtils.EMPTY;
        try {
            result = HttpClientUtil.sendHttpGetRequest(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray = JSONArray.parseArray(result);
        }catch (JSONException e) {
            System.out.println("将获取的远程结果转为json对象集合失败");
            JSONObject jsonObject = JSONObject.parseObject(result);
            int status = (Integer) jsonObject.get("status");
            if(status != 200) {
                throw new Exception("远程服务未知错误！");
            }
        }

        // 使用fastjson解析
        List<PersonBean> personBeanList = JSONArray.parseArray(result, PersonBean.class);
        for(PersonBean personBean: personBeanList) {
            System.out.println(personBean);
        }
    }

}
