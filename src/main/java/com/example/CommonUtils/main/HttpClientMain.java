package com.example.CommonUtils.main;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.CommonUtils.bean.PersonBean;
import com.example.CommonUtils.utils.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;

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

    public static void main(String[] args) throws Exception{

        HttpClientMain sendController = new HttpClientMain();

        PersonBean personBean = new PersonBean();
        personBean.setName("postName");
        personBean.setAge(333);
        personBean.setAddress("postttttt");
        JSONObject jsonObject =  (JSONObject) JSON.toJSON(personBean);

        // post方式传递json对象
        sendController.httpPostJsonTest(BASE_URL, jsonObject);
        // get请求
        sendController.httpGetTest(BASE_URL + GET_PERSON_LIST);
    }

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
