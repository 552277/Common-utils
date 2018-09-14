package com.example.CommonUtils.main.Commons.io;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.time.DateUtils;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Weichang Zhong
 * @Date: 2018/9/12
 * @Time: 15:18
 * @Description:
 */
public class FileUtilsTest {

    public static void main(String[] args) throws IOException {
        // 创建文件、目录对象，非物理层面创建
        File file1 = new File("D:\\Test\\aa.txt");
        File file2 = new File("D:\\Test\\bb.txt");// 注意，如果存在test的话，Test是不能够成功创建的
        File file3 = new File("D:\\DevDir\\cc.txt");
        File fileDirectory = new File("D:\\Test\\");
        File fileDirectory2 = new File("D:\\DevDir\\");
        // ------------------------------创建文件--------------------------------------
        // 1，强制创建文件夹,注如果pathname:D:\\Test则不会创建成功，需要最后加"\\"
        FileUtils.forceMkdir(fileDirectory);
//        FileUtils.forceMkdir(fileDirectory2);

        // 2,强制创建父文件夹,但不创建aa.txt文件
        FileUtils.forceMkdirParent(file1);
        // 3, 物理磁盘上创建文件
        if(!file1.exists()) {
            file1.createNewFile();
        }


        // -----------------------------写入内容到文件-------------------------------------
        String str = "I'd like to have a good future \r\n"; // "\r\n"表示换行
        FileWriter fileWriter = null;
        BufferedWriter out = null;
        try {
            fileWriter = new FileWriter(file1, false); // false为覆盖原来的内容，true为追加
            out = new BufferedWriter(fileWriter);
            out.write(str, 0, str.length()-1);
            out.write("ok\n");
            out.close();
            fileWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(out != null) {
                out.close();
            }
            if(fileWriter != null) {
                fileWriter.close();
            }
        }

        // ------------------------------读取文件----------------------------------------
        // (1)根据文件获取文件输入流
        FileInputStream fileInputStream = FileUtils.openInputStream(file1);
        // (2) 读取文件到字节数组
        byte[] bytes = FileUtils.readFileToByteArray(file1);
        // (3) 读取文件到字符串
        String fileContent = FileUtils.readFileToString(file1);
        // (4) 读取文件到集合中
        List<String> fileContentList = FileUtils.readLines(file1, "UTF-8");
        System.out.println(fileContentList); // [I'd like to have a good future , ok]
        // (5) 读取文件大小
        long fileSize = FileUtils.sizeOf(file1);
        System.out.println(fileSize); // 35(包括空格在内的所有字符数)


        //  -------------------------------写文件-----------------------------------------------
        // 1,根据文件获取输出流, 然后文件中内容将变为空
        FileOutputStream fileOutputStream = FileUtils.openOutputStream(file1);
        // 根据文件获取输出流 并指定是否追加到文件中，文件中内容不变为空
        FileOutputStream fileOutputStream1 = FileUtils.openOutputStream(file1, true);
        // 将字节数组内容写到文件中，文件不存在时创建
        FileUtils.writeByteArrayToFile(file3,bytes);
        // 将字节数组内容写到文件中，文件不存在时创建, true表示不覆盖原来的内容，新内容追加到文件中
        FileUtils.writeByteArrayToFile(file3, bytes, true);
        // 将集合数据按行写到文件中,并指定是否追加
        FileUtils.writeLines(file3, fileContentList, true);
        //  将字符串数据写到文件中，并指定编码和是否以追加的方式写入，此方法实践时有些问题， "UTF-8"串输出到内容中了
//        FileUtils.writeLines(file3, fileContentList, "UTF-8", true);

        // ---------------------------------获取文件及文件夹---------------------------------------
        File file = FileUtils.getFile(fileDirectory, "aa.txt");
        File tempFile = FileUtils.getTempDirectory();
        String tempFilePath = FileUtils.getTempDirectoryPath();
        //　获取临时文件路径
        System.out.println(tempFilePath); // C:\Users\ZHONGW~1\AppData\Local\Temp\
        // 获取用户文件
        System.out.println(FileUtils.getUserDirectory()); // C:\Users\zhongweichang
        // 获取用户文件路径
        System.out.println(FileUtils.getUserDirectoryPath()); // C:\Users\zhongweichang

        // ----------------------------------查找文件-----------------------------------------------
        // 是否递归查找指定扩展名的文件
        String[] extensions = new String[]{"txt", "java", "png"};
        Collection<File> fileList = FileUtils.listFiles(fileDirectory, extensions,true);

        // ----------------------------------复制文件-----------------------------------------------
        File fileDirectory3 = new File("D:\\online\\");
        // 将fileDirectory里的文件复制到fileDirectory3里
//        FileUtils.copyDirectory(fileDirectory, fileDirectory3);
        // 将fileDirectory里的文件复制到fileDirectory3里,并指定保留原文件日期
        FileUtils.copyDirectory(fileDirectory, fileDirectory3, true);
        // 复件文件到目录
        FileUtils.copyFileToDirectory(file1, fileDirectory3);
        // 将URL资源复制到指定文件
        FileUtils.copyURLToFile(new URL("https://xxx.jpg"), file2);

        // -------------------------------- 文件过滤-----------------------------------------------
        // 比较文件内容是否相同，前提是相同格式的文件
        System.out.println(FileUtils.contentEquals(file1, file2));
        File fileDirectory4 = new File("D:\\Test33\\");
        File fileDirectory5 = new File("D:\\Test\\33");
        // 判断一文件夹是否包含另一个文件夹
        System.out.println(FileUtils.directoryContains(fileDirectory, fileDirectory4));// false
        System.out.println(FileUtils.directoryContains(fileDirectory, fileDirectory5));// true ,前提是磁盘中必须真实存在相应的文件夹

        /**
         * 在指定的日期判断文件是否是新文件
         * 本人测试的以下3个值为：
         * file1:2018/9/14 14:20
         * file4:2018/9/4 14:04
         * data: 2018/9/11 14:49:42
         */
        File file4 = new File("D:\\ProgramFiles\\yu-writer-beta-0.5.3-windows-x64.zip.part");
        Date date = DateUtils.addDays(new Date(), -3);
        System.out.println(FileUtils.isFileNewer(file1,date)); // true
        System.out.println(FileUtils.isFileOlder(file1,date)); // false
        System.out.println(FileUtils.isFileNewer(file4, date)); // false
        System.out.println(FileUtils.isFileOlder(file4, date)); // true

        // --------------------------------修改文件及文件夹------------------------------------------
        // 移动文件,将file1更名为file2
//        FileUtils.moveFile(file1, file2);
        // 移动文件夹, 将fileDirectory移动到fileDirectory2，firleDirectory保留，如果fileDirectory2存在，当不会成功
        FileUtils.moveDirectory(fileDirectory, fileDirectory2);
        // 移动file1文件到fileDirectory2文件夹，目前报错，移动失败：Failed to delete original file 'D:\Test\aa.txt' after copy to 'D:\DevDir\aa.txt'
//        FileUtils.moveFileToDirectory(file1, fileDirectory2, true); // true表示如果fileDirectory2不存在就创建
        /**
         * 移动之前D:\DevDir\cc.txt  D:\Test\aa.txt
         * 移动之后D:\DevDir\Test\aa.txt，其它不变
         */
        FileUtils.moveDirectoryToDirectory(fileDirectory, fileDirectory2, true);


        // ----------------------------------删除文件及文件夹--------------------------------------
        // 强制删除文件
//        FileUtils.forceDelete(file1);
        // 温和地删除文件，不抛出异常
        FileUtils.deleteQuietly(file1);
        // 删除文件夹中的内容（包括子文件夹和文件），目前测试，都提示删除失败
//        FileUtils.cleanDirectory(fileDirectory);
        // JVM 退出时强制删除文件
        FileUtils.forceDeleteOnExit(file1);

        // --------------------------------- 涉及文件操作的数据转换-----------------------------------
        // 将文件集合转换为文件数组
        List<File>  fileList1 = new ArrayList<>();
        fileList1.add(file1);
        fileList1.add(file2);
        File[] fileArray = FileUtils.convertFileCollectionToFileArray(fileList1);
        // 将URL资源转为文件对象
        File newURLFile = FileUtils.toFile(new URL(""));
        // 将多个URL资源转为文件数组
        URL[] urls = new URL[]{};
        File[] newURLFiles = FileUtils.toFiles(urls);
        // 将文件数组转为URL资源
        URL[] urls1 = FileUtils.toURLs(newURLFiles);

        // --------------------------------- 文件迭代--------------------------------------------------
        // 迭代输出文件中的每一行内容
        LineIterator lineIterator = FileUtils.lineIterator(file1, "UTF-8");
        while (lineIterator.hasNext()) {
            System.out.println(lineIterator.next());
        }
    }

}
