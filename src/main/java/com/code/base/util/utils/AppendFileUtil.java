package com.code.base.util.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class AppendFileUtil {

    public static void method1(String file, String conent) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
            out.write(conent + "\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 追加文件：使用FileWriter
     *
     * @param fileName
     * @param content
     */
    public static void method2(String fileName, String content) {
        FileWriter writer = null;
        try {
            // 打开�?个写文件器，构�?�函数中的第二个参数true表示以追加形式写文件     
            writer = new FileWriter(fileName, true);
            writer.write(content + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 追加文件：使用RandomAccessFile
     *
     * @param fileName 文件名
     * @param content  追加的内容
     */
    public static void method3(String fileName, String content) {
        RandomAccessFile randomFile = null;
        try {
            // 打开�?个随机访问文件流，按读写方式     
            randomFile = new RandomAccessFile(fileName, "rw");
            // 文件长度，字节数     
            long fileLength = randomFile.length();
            // 将写文件指针移到文件尾�??     
            randomFile.seek(fileLength);
            randomFile.writeBytes(content + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomFile != null) {
                try {
                    randomFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static List<String> readContent(String fileName) {
        List<String> contents = new ArrayList<String>();
        try {
            String encoding = "UTF-8";
//            File file=new File(AppendFileUtil.class.getClassLoader().getResource("proxyIp.txt").getFile());
            File file = new File(fileName);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    if (!lineTxt.trim().equals(null)) {
                        contents.add((lineTxt.trim()));//获取代理ip端口存放可用代理队列中
                    }
                    System.out.println(lineTxt);
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return contents;
    }


    public static void main(String[] args) {
        try {
            File file = new File("d://text.txt");
            if (file.createNewFile()) {
                System.out.println("Create file successed");
            }
            method1("d://text.txt", "123");
            method2("d://text.txt", "123");
            method3("d://text.txt", "123");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * 删除文件
     *
     * @param filename
     */
    public static void delFile(String filename) {
        File file = new File(filename);
        if (file.exists() && file.isFile())
            file.delete();
    }

    /**
     * 创建文件
     *
     * @param filename
     * @throws IOException
     */
    public static void createFile(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists())
            file.createNewFile();
    }


    /*****
     * 文件创建写入
     * @param fileName 文件名
     * @param content  文件内容
     */
    public static void writeFile(String fileName, String content) throws IOException {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            File file = new File(fileName);
            //路径
            File fileDir = file.getParentFile();
            if (!fileDir.exists() && !fileDir.isDirectory()) {
                System.out.println("//路径不存在，进行创建");
                fileDir.mkdirs();
            }
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

            System.out.println("Done");

        } finally {
            try {
                fw.close();
                bw.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
