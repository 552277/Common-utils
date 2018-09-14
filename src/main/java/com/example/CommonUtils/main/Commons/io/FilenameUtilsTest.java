package com.example.CommonUtils.main.Commons.io;

import org.apache.commons.io.FilenameUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Weichang Zhong
 * @Date: 2018/9/12
 * @Time: 13:20
 * @Description: 文件名工具类
 */
public class FilenameUtilsTest {
    /** 备注：本地存在文件;D:\test\file.txt **/
    public static void main(String[] args) throws IOException {
        String directory = "D:\\test\\dev";
        String fileName = "firstFile.txt";
        String fileFullName = directory + "\\" + fileName;
        // 1,判断父目录是否包含子元素（文件或目录）,即使本地不存在test22文件夹，也是true，说明没有检索磁盘
        System.out.println(FilenameUtils.directoryContains("D:\\test","D:\\test22\\f22ile2.txt")); // true
        System.out.println(FilenameUtils.directoryContains("D:\\test","D:\\te2st\\f22ile2.txt")); // false

        // 2,判断两个文件名是否相等,区分大小写
        System.out.println(FilenameUtils.equals("D:\\test", "D:\\tEst")); // false

        // 3, 两个文件名标准化后判断是否相等
        System.out.println(fileFullName); // D:\test\dev\firstFile.txt
        String normalizeFile = FilenameUtils.normalize(fileFullName, true);
        System.out.println(normalizeFile); // D:/test/dev/firstFile.txt
        System.out.println(FilenameUtils.equalsNormalized(fileFullName, normalizeFile)); // true

        // 4，获取文件基本名
        System.out.println(FilenameUtils.getBaseName(fileFullName)); // firstFile

        // 5，获取文件扩展名
        System.out.println(FilenameUtils.getExtension(fileFullName)); // txt
        // 获取文件扩展名索引:D:/test/dev/firstFile  .  txt
        System.out.println(FilenameUtils.indexOfExtension(fileFullName)); // 21

        // 6，获取单独的文件名及其后缀
        System.out.println(FilenameUtils.getName(fileFullName)); // firstFile.txt

        // 7，获取文件完整路径，不含文件名
        System.out.println("7: " + FilenameUtils.getFullPath(fileFullName)); // 7: D:\test\dev\

        // 8, 获取文件不含结尾分隔符的完整路径，不含文件名
        System.out.println(FilenameUtils.getFullPathNoEndSeparator(fileFullName)); // D:\test\dev
        // 获取不含后缀的文件路径和文件名
        System.out.println(FilenameUtils.removeExtension(fileFullName)); // D:\test\dev\firstFile

        // 9，获取不含前缀的完整文件路径，不含文件名
        System.out.println(FilenameUtils.getPath(fileFullName)); // test\dev\


        // 10,获取不含前缀和结尾分隔符的完整文件路径，不含文件名
        System.out.println(FilenameUtils.getPathNoEndSeparator(fileFullName)); // test\dev

        // 11,获取文件路径前缀
        System.out.println(FilenameUtils.getPrefix(fileFullName)); // D:\

        // 12,获取文件路径前缀长度
        System.out.println(FilenameUtils.getPrefixLength(fileFullName)); // 3

        // 13,获取文件最后一个分隔符索引:D:/test/dev   /   firstFile.txt
        System.out.println(FilenameUtils.indexOfLastSeparator(fileFullName)); // 11

        // 14, 判断文件扩展名是否符合给定的
        List<String> suffixList = new ArrayList<String>();
        suffixList.add("png");
        suffixList.add("txt");
        suffixList.add("xlsx");
        System.out.println(FilenameUtils.isExtension(fileFullName, suffixList)); // true
        System.out.println(FilenameUtils.isExtension(fileFullName, "txt")); // true

        // 15 转换文件分割为系统分隔符
        System.out.println(FilenameUtils.separatorsToSystem(fileFullName)); // D:\test\dev\firstFile.txt
        // 16 转换文件分割为unix系统分隔符
        System.out.println(FilenameUtils.separatorsToUnix(fileFullName)); // D:/test/dev/firstFile.txt
        // 17 转换文件分割为windows系统分隔符
        System.out.println(FilenameUtils.separatorsToWindows(fileFullName)); // D:\test\dev\firstFile.txt

        // 18 判断文件是否符合指定的规则
        System.out.println(FilenameUtils.wildcardMatch(fileFullName, "*.txt")); // true
    }
}
