package com.example.CommonUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.CommonUtils.bean.PersonBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonUtilsApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void fastjsonTest() {
		PersonBean personBean1 = new PersonBean();
		personBean1.setId(1l);
		personBean1.setAge(11);
		personBean1.setName("小1");
		PersonBean personBean2 = new PersonBean();
		personBean2.setId(2l);
		personBean2.setAge(22);
		personBean2.setName("小2");
		List<PersonBean> personBeans = new ArrayList<>();
		personBeans.add(personBean1);
		personBeans.add(personBean2);

		// 1, java 对象转成json串
		String jsonStr = JSON.toJSONString(personBean1);
		System.out.println("1,java对象转成的json串：" + jsonStr);
		// 2, list集合转成json串
		String jsonArrayStr = JSON.toJSONString(personBeans);
		System.out.println("2,java对象集合转成json串：" + jsonArrayStr);
		System.out.println("---------------------------------------------------------");
		// 3, json串转成java对象
		PersonBean personBean = JSON.parseObject(jsonStr, PersonBean.class);
		System.out.println("3,json串转成的java对象：" + personBean);
		// 4, json集合串转成list对象集合
		List<PersonBean> personBeanList = JSONArray.parseArray(jsonArrayStr, PersonBean.class);
		System.out.println("4,json集合串转成list对象集合：" + personBeanList);
		System.out.println("---------------------------------------------------------");
		// 5 json串转成json 对象
		JSONObject jsonObject = JSON.parseObject(jsonStr);
		System.out.println(jsonObject.get("name"));
		System.out.println("5,json串转成json对象：" + jsonObject);
		JSONArray jsonArray = JSONArray.parseArray(jsonArrayStr);
		System.out.println("6,json集合串转成json集合对象: " + jsonArrayStr);

	}
}
