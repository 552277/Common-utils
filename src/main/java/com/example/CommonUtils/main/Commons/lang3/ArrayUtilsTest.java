package com.example.CommonUtils.main.Commons.lang3;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Weichang Zhong
 * @Date: 2018/9/10
 * @Time: 14:11
 * @Description:
 */
public class ArrayUtilsTest {
    public static void main(String[] args) {
        // 1.  静态属性新建空的数据类型数组
        boolean[] emptyBoolean = ArrayUtils.EMPTY_BOOLEAN_ARRAY;
        String[] emptyString = ArrayUtils.EMPTY_STRING_ARRAY;

        // 2. 静态方法
        // 2.1 向数据类型数组中添加元素
        String[] stringArray2 = new String[]{"first", "second"};
        String[] addedArray = ArrayUtils.add(stringArray2, "third");
        // 输出值为3
        System.out.println(addedArray.length);

        // 2.2 将多个相同数据类型的数据添加到一块
        String[] stringArray3 = ArrayUtils.addAll(addedArray, stringArray2);
        // 输出：5
        System.out.println(stringArray3.length);

        // 2.3 克隆一份数组
        String[] tempStringArray = ArrayUtils.clone(stringArray3);
        // 输出：5
        System.out.println(tempStringArray.length);

        // 判断两个数组是否相同，如果内容相同、顺序相同， 则返回为真
        System.out.println("isEquals:" + ArrayUtils.isEquals(tempStringArray, stringArray3)); // isEquals:true

        // 2.4 数组中是否包含某个数据值
        boolean isContains = ArrayUtils.contains(stringArray3, "first");
        // 输出:true
        System.out.println(isContains);

        // 2.5 获取数组长度
        int length = ArrayUtils.getLength(stringArray3);

        // 2.6 获取数组哈希码
        int hashCode = ArrayUtils.hashCode(tempStringArray);

        // 2.7 获取指定元素在数组中的索引
        int index = ArrayUtils.indexOf(tempStringArray, "second");
        // 输出：1
        System.out.println(index);

        // 2.8 从指定索引(1)开始查找元素(second)在数组(tempStringArray)中的索引
        int stringIndex = ArrayUtils.indexOf(tempStringArray, "second", 1);
        // 输出：2.8:1
        System.out.println("2.8:" +stringIndex);

        // 2.9 判断数组是否为空/是否不为空
        boolean isEmpty = ArrayUtils.isEmpty(tempStringArray);
        boolean isNotEmpty = ArrayUtils.isNotEmpty(tempStringArray);

        // 2.10 判断两个数组长度是否相同
        boolean isSameLength = ArrayUtils.isSameLength(tempStringArray, stringArray2);

        // 2.11 判断两个数组是否是相同类型
        boolean isSameType = ArrayUtils.isSameType(tempStringArray, stringArray2);
        // 输出：true
        System.out.println(isSameType);

        // 2.12 判断数组是否已按照自然排序
        boolean isSorted = ArrayUtils.isSorted(stringArray2);
        // 输出: true
        System.out.println(isSorted);
        String[] tempStringArray2 = ArrayUtils.add(stringArray2, "alive");
        // 输出：false
        System.out.println(ArrayUtils.isSorted(tempStringArray2));

        // 2.13 最后一个元素索引 tempStringArray:{"first", "second", "third", "first", "second"}
        int lastIndex = ArrayUtils.lastIndexOf(tempStringArray, "second");
        // 输出：4
        System.out.println(lastIndex);
        // 从指定位置3 开始，往前查找（遇到第一个）元素的索引，也就是索引小于指定索引3的最后一个second元素的索引
        lastIndex = ArrayUtils.lastIndexOf(tempStringArray, "second", 3);
        // 输出：1
        System.out.println(lastIndex);

        // 2.14 将null改为empty
        String[] arr = null;
        String[] isNullToEmpty = ArrayUtils.nullToEmpty(arr);
        // null无输出, 输出：empty
        if(null == isNullToEmpty) {
            System.out.println("null");
        }
        if(isNullToEmpty.length == 0) {
            System.out.println("empty");
        }

        // 2.15 移除数组指定索引的元素
        String[] result = ArrayUtils.remove(tempStringArray, 1);
        // 输出：4
        System.out.println(result.length);

        // 2.16 数组元素位置反转，此方法无返回值，执行之后，tempStringArray变为：{"second", "first", "third", "second", "first"}
        ArrayUtils.reverse(tempStringArray);

        // 2.17 数组元素位置循环移位, 2 代表每个元素都向前移动2个位置，移动后：{"second", "first", "second", "first", "third"}
        ArrayUtils.shift(tempStringArray, 2);

        // 2.18 获取数组指定区间的子数组
        String[] subsArray = ArrayUtils.subarray(tempStringArray, 1, 3);

        // 2.19 交换数组中两个指定索引位置的元素，无返回值，执行后，temStringArray:{"second", "third", "second", "first", "first"}
        ArrayUtils.swap(tempStringArray, 1,4);

        // 2.20 将基本数据类型的数组转化为包装类型的数组
        boolean[] booleans = new boolean[] {true, false};
        Boolean[] booleanArrays = ArrayUtils.toObject(booleans);

        // 2.21 将包装类型的数据转化为基本类型的数组
        boolean[] primitiveArray = ArrayUtils.toPrimitive(booleanArrays);

        // 2.22 将数组以 {,} 形式输出
        System.out.println(ArrayUtils.toString(tempStringArray)); // {second,third,second,first,first}

        // 2.23 将二维数据转为MAP
        Map map = ArrayUtils.toMap(new String[][]{{"first", "first value"},{"second", "second value"},{"third", "third value"}});
        Iterator iterator = map.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.print(" key:" + entry.getKey() + " value:" + entry.getValue()); //  key:third value:third value key:first value:first value key:second value:second value
        }
    }
}
