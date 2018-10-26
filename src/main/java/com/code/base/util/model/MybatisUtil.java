package com.code.base.util.model;

import com.code.base.util.utils.PathUtil;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisUtil {


    static final Boolean IS_JAR=true;
    static final String RESURCE_PATH = "/src/main/resources";

    public static void main(String[] args) {

        try {
//    		creatBean();
            MybatisUtil.autoMakeBean("172.21.46.11:3306/hiaerrobot_device?useUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=false", "root", "Www#1234",
                    "main.java.com.hollysys.hollipark.dispatching.core.entity", "DeviceType", "device_type");
//    		autoMakeBean("114.55.99.146:3306/sis","sis","123qwe",
//					       "com.test.CorpInfo.bean","CorpInfo","corp_info");
//    		autoMakeBean("114.55.99.146:3306/sis","sis","123qwe",
//					       "com.test.CorpLicence.bean","CorpLicence","corp_licence");
//    		autoMakeBean("114.55.99.146:3306/sis","sis","123qwe",
//				       "com.test.CorpUserRa.bean","CorpUserRa","corp_user_ra");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /***
     * 创建生产bean对象的xml配置
     * @param urlDbName     数据库地址，如：114.55.99.146:3306/sis
     * @param dbUser        数据库用户名
     * @param dbPassWord    数据库密码
     * @param beanPackage   生产bean类包路径 如：com.test.UserInfo.bean
     * @param beanName      bean类名,注意与表名对应的驼峰规则   如：UserInfo
     * @param tableName     映射表名      如：user_info
     */
    public static void autoMakeBean(String urlDbName, String dbUser, String dbPassWord,
                                    String beanPackage, String beanName, String tableName) {
        makeGeneratorXml(urlDbName, dbUser, dbPassWord,
                beanPackage, beanName, tableName);
        creatBean();

        PathUtil.changeBootPath(beanPackage, beanName);
    }


    /***
     * 生产bean对象实现
     */
    public static void creatBean() {
        try {
            System.out.println("start generator ...");
            List<String> warnings = new ArrayList<String>();
            boolean overwrite = true;
            /****优化调整，从绝对路径中获取文件.用相对路径： 配置文件刚生成时工程未加载到最新生成文件不能正确生成bean*****/
//            File configFile = new File(MybatisUtil.class.getResource("/ftl/tobean/xml/generator.xml").getFile());
/*
            String relativelyPath = System.getProperty("user.dir");
            File configFile = new File(relativelyPath + "/src/ftl/tobean/xml/generator.xml");
*/
            String relativelyPath = System.getProperty("user.dir");
            String path = relativelyPath + RESURCE_PATH;
            File configFile = new File(path+"/ftl/toBean/generator.xml");
//            File configFile = new File(MybatisUtil.class.getClassLoader().getResource("ftl/toBean/generator.xml").getPath());

            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
            System.out.println("end generator!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /***
     * 创建生产bean对象的xml配置
     * @param urlDbName     数据库地址，如：114.55.99.146:3306/sis
     * @param dbUser        数据库用户名
     * @param dbPassWord    数据库密码
     * @param beanPackage   生产bean类包路径 如：com.test.UserInfo.bean
     * @param beanName      bean类名,注意与表名对应的驼峰规则   如：UserInfo
     * @param tableName     映射表名      如：user_info
     */
    public static void makeGeneratorXml(String urlDbName, String dbUser, String dbPassWord,
                                        String beanPackage, String beanName, String tableName) {
        Map<String, Object> root = new HashMap<String, Object>();

        root.put("urlDbName", urlDbName);
        root.put("dbUser", dbUser);
        root.put("dbPassWord", dbPassWord);
        root.put("beanPackage", beanPackage);
        root.put("beanName", beanName);
        root.put("tableName", tableName);

        freemarker.template.Configuration cfg = new freemarker.template.Configuration();
/*
        String relativelyPath = System.getProperty("user.dir");
//		String path = AutoTest.class.getResource("/").getPath()+"ftl";
        String path = relativelyPath + "/src/ftl/tobean";
        */
        String relativelyPath = System.getProperty("user.dir");
        String path = relativelyPath + RESURCE_PATH + "/ftl/toBean";
//        String path = MybatisUtil.class.getClassLoader().getResource("ftl/toBean").getPath();
        try {
            if(!IS_JAR) {
                cfg.setDirectoryForTemplateLoading(new File(path));
            }else {
                cfg.setClassForTemplateLoading(MybatisUtil.class, "/ftl/toBean");
            }
            Template template = cfg.getTemplate("/generator.ftl");
            StringWriter out = new StringWriter();
            template.process(root, out);

            System.out.println(out.toString());
//            String javaPathName = relativelyPath + "/src/ftl/tobean/xml/generator.xml";
            String javaPathName = path + "/generator.xml";
            System.out.println("javaPathName>>>"+javaPathName);
            writeFile(javaPathName, out.toString());
        } catch (IOException e) {
            System.out.println("Cause==>" + e.getCause());
        } catch (TemplateException e) {
            System.out.println("Cause==>" + e.getCause());
        }
    }

    /*****
     * 文件创建写入
     * @param fileName 文件名
     * @param content  文件内容
     */
    public static void writeFile(String fileName, String content) {
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
            bw.flush();
            bw.close();
            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
                bw.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
