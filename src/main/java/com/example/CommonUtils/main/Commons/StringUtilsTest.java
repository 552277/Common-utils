package com.example.CommonUtils.main.Commons;

import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Weichang Zhong
 * @Date: 2018/9/10
 * @Time: 16:22
 * @Description:
 */
public class StringUtilsTest {
    public static void main(String[] args) {
        // 1，将字符串的第一个字符改为大写
        System.out.println(StringUtils.capitalize("hello world")); // Hello world

        // 2.使用空格填充字符串至指定长度，并将字符串位于中间
        System.out.println(StringUtils.center("hello world", 15));// "  hello world  "

        // 3，使用指定字符填充字符串至指定长度，并将字符串位于中间
        System.out.println(StringUtils.center("hello wold", 15, "*")); // **hello wold***
        // 注意:填写的字符串有几个字符，原字符串长度加几，如果超过了长度，两头平均优先取替换字符的前x个字符
        System.out.println(StringUtils.center("hello world", 15, "!@@@@@@!")); // !@hello world!@

        // 4，移除字符串中的最后一个字符
        System.out.println(StringUtils.chop("hello world")); // hello worl

        // 5，按照字典顺序比较两个字符串
        System.out.println(StringUtils.compare("Aabc", "aabb")); // -32 说明aabb较大，靠后,a对应的ASCII比A的大

        // 6,按照字典顺序比较两个字符串，忽略大小写
        System.out.println(StringUtils.compareIgnoreCase("Aabc", "aabb")); // 1

        // 7,为null返回空的字符串，否则返回对应的串值
        System.out.println(StringUtils.defaultString("Aabc")); // Aabc

        // 8, 如果字符串为null，返回默认字符串，如果不为null（如果为空""也视为不为null），直接返回对应值
        System.out.println(StringUtils.defaultString(null, "**")); // **

        // 9, 去除字符串中的空格
        System.out.println(StringUtils.deleteWhitespace("he llo world")); // helloworld

        // 10，返回第二个字符串与第一个字符串不同的内容
        System.out.println(StringUtils.difference("hello world", "hello java"));

        // 11，返回字符串中的数字
//        System.out.println(StringUtils.getDigits());

        // 12, 返回字符串索引
        System.out.println(StringUtils.indexOf("hello java hello", "llo")); // 2

        // 13,从指定位置开始查找,返回字符串索引
        System.out.println(StringUtils.indexOf("hello java hello", "llo", 3 )); // 13

        // 14 返回字符串开始不同索引, 两个字符串对比，从第4个索引位置开始不同
        System.out.println(StringUtils.indexOfDifference("hello java hello", "helle")); // 4

        // 15, 忽略大小比较是否包含字符串，返回字符串索引
        System.out.println(StringUtils.indexOfIgnoreCase("hello java hello", "jAva"));// 6
        System.out.println(StringUtils.indexOfIgnoreCase("hello java hello", "jAvw")); // -1

        // 16 判断字符串是否是null、""，" "
        System.out.println(StringUtils.isEmpty(null)); // true
        System.out.println(StringUtils.isEmpty("")); // true
        System.out.println(StringUtils.isEmpty(" ")); // false

        System.out.println(StringUtils.isAnyEmpty(null)); // true
        System.out.println(StringUtils.isAnyEmpty("")); // true
        System.out.println(StringUtils.isAnyEmpty(" ")); // false

        // 17 判断字符串是否都是小写
        System.out.println(StringUtils.isAllLowerCase("java")); // true
        System.out.println(StringUtils.isAllLowerCase("java Hello")); // false

        // 18 判断字符串是否都是大写
        System.out.println(StringUtils.isAllUpperCase("java H")); // false

        // 19 判断字符串是否都是字母
        System.out.println(StringUtils.isAlpha("java")); // true
        System.out.println(StringUtils.isAllLowerCase("java2")); // false

        // 20 判断字符串是否都是字母或数字
        System.out.println(StringUtils.isAlphanumeric("java hello")); // false
        System.out.println(StringUtils.isAlphanumeric("java2hello")); // true

        // 21 判断字符串是否都是字母、数字或空格组成
        System.out.println(StringUtils.isAlphanumericSpace("java2hello")); // true
        System.out.println(StringUtils.isAlphanumericSpace("java2hello")); // true

        // 22 判断字符串是否都是字母或空格组成
        System.out.println(StringUtils.isAlphaSpace("java2hello")); // false
        System.out.println(StringUtils.isAlphaSpace("java hello")); // true

        // 23 判断字符串非null,""," "
        System.out.println(StringUtils.isNotBlank(null)); // false
        System.out.println(StringUtils.isNotBlank("")); // false
        System.out.println(StringUtils.isNotBlank(" ")); //false
        System.out.println(StringUtils.isBlank(" ")); // true

        // 24 判断字符串是否由数字组成,字符串中不能有空格
        System.out.println(StringUtils.isNumeric("123")); // true

        // 25 判断字符串是否由空格组成
        System.out.println(StringUtils.isWhitespace("222    22")); // false
        System.out.println(StringUtils.isWhitespace("  ")); // true
        // 删除所有空格
        System.out.println(StringUtils.deleteWhitespace("2 22  33")); // 22233

        // 26, 将数组中的元素按照给定的字符连接
        String[] arr = new String[]{"aa", "bb", "cc"};
        System.out.println(StringUtils.join(arr, "*")); // aa*bb*cc

        // 27 将数组中指定区间的元素按照给定的字符连接
        System.out.println(StringUtils.join(arr, "*", 1,3)); // bb*cc

        // 28 在字符串中查找指定字符串的最后索引
        System.out.println(StringUtils.lastIndexOf("hello world", "or")); // 7

        // 29 在字符串中查找字符串索引， 忽略大小写
        System.out.println(StringUtils.lastIndexOfIgnoreCase("hello world", "WOR")); // 6

        // 30 从指定索引位置30往前查找到的第一个llo的位置的索引, 忽略大小写
        System.out.println(StringUtils.lastIndexOfIgnoreCase("hello world hello", "llo", 5)); // 2

        // 31 返回字符串左侧指定长度的字符串
        System.out.println(StringUtils.left("hello world", 5)); // hello

        // 32 在左侧用空格填充字符串
        System.out.println(StringUtils.leftPad("hell", 10)); // "      hell"
         // 在右侧用空格填充字符串
        System.out.println(StringUtils.rightPad("hello", 10)); // "hello     "

        // 33 在左侧用指定字符串填充字符串
        System.out.println(StringUtils.leftPad("hello", 10, "123"));// 12312hello

        // 获取一个字符串左边的两个字符
        System.out.println(StringUtils.left("abcdd", 2)); // ab

        // 34 字符串反转
        System.out.println(StringUtils.reverse("hello")); // olleh

    }
}
