package com.code.base.util.utils;

import java.util.List;

public class PathUtil {

    /**
     * springboot 生成bean路径矫正
     *
     * @param beanPackage
     * @param beanName
     */
    public static void changeBootPath(String beanPackage, String beanName) {
        String relativelyPath = System.getProperty("user.dir");
        String path = relativelyPath + "/src/" + beanPackage.replaceAll("\\.", "/") + "/" + beanName + ".java";
        try {
            //读文件
            List<String> contents = AppendFileUtil.readContent(path);


            AppendFileUtil.delFile(path);
            AppendFileUtil.createFile(path);

            //更改包路径,回写文件
            for (String item : contents
                    ) {
                if (item.trim().startsWith("package ")) {
                    AppendFileUtil.method2(path, item.replace("main.java.", ""));
                } else {
                    AppendFileUtil.method2(path, item);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(path + "================");
    }
}
