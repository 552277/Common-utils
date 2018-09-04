package com.example.CommonUtils.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Weichang Zhong
 * @Date: 2018/9/4
 * @Time: 16:39
 * @Description:
 */
@Data
@ToString
public class PersonBean implements Serializable {

    private Long id;

    private String name;

    private Integer age;

    private String address;

    private Date gmtCreate;

    private Date gmtModified;

}
