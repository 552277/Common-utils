package com.example.CommonUtils.main.Commons.lang3;

import org.apache.commons.lang3.ArchUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;


/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Weichang Zhong
 * @Date: 2018/9/11
 * @Time: 9:17
 * @Description:
 */
public class OtherLang3 {

    public static void main(String[] args) {


        // --------------------------- SystemUtils 系统工具类--------------------------
        // 获取JavaHome
        System.out.println(SystemUtils.getJavaHome()); // D:\ProgramFiles\Java\jdk1.8.0_92\jre
        // 临时目录位置
        System.out.println(SystemUtils.getJavaIoTmpDir()); // C:\Users\ZHONGW~1\AppData\Local\Temp

        // ------------------------------ClassUtils 类操作工具类-------------------------
        // 获取对应的的类名
        System.out.println(ClassUtils.getShortClassName(OtherLang3.class)); // OtherLang3
        // 获取对应的包名
        OtherLang3 otherLang3 = new OtherLang3();
        System.out.println(ClassUtils.getPackageName(otherLang3, null)); // com.example.CommonUtils.main.Commons

        // ----------------------------RandomStringUtils 随机数操作类------------------------------
        // 获取指定位数的字母或数字（可能全是字母、也可能全是数字，也可能是数字和字母混合
        System.out.println(RandomStringUtils.randomAlphanumeric(5)); // hO074

        // --------------------------- DateFormatUtils 日期格式化操作类----------------------------------
        System.out.println("日期格式化:" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")); // 日期格式化:2018-09-11 10:09:07
        // DateUtils日期加减操作类
        Date date = DateUtils.addDays(new Date(), 3);
        System.out.println("日期添加3天后:" + DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss")); // 日期添加3天:2018-09-14 10:09:07

        System.out.println(ArchUtils.getProcessor());

    }
}
