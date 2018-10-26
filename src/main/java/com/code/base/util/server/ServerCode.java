package com.code.base.util.server;

import com.code.base.util.utils.AppendFileUtil;
import com.code.base.util.utils.AttrUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerCode {

    static final String MODEL = "entity";
    static final String DAO = "dao.mapper";
    static final String SERVICE = "service";
    static final String CONTROLLER = "controller";
    static final String JAVA_PATH = "/src/main/java";
    static final String RESURCE_PATH = "/src/main/resources";
    static final Boolean IS_JAR=true;


    /****
     *生成所有
     * @param o      操作对象
     * @param dbPid  数据库主键字段
     * @param pid    对象主键
     * @param makeType    创建类型: 1 全部生成，2仅生成后端，3仅生成前端
     * @param parentPath    前端生成路径 例如：modules/system/test
     */
    public static void generalAll(Object o, String dbPid, String pid, Integer makeType, String tableName, String... parentPath) {
        /*********************后端服务代码创建************************/
        if (makeType == 1 || makeType == 2) {
            makeModulQuery(o);
            makeXmlMapper(o, dbPid, pid, tableName);
            makeJavaMapper(o, pid);
            makeIService(o, pid);
            makeServiceImpl(o, pid);
            makeController(o, pid, parentPath);
        }

/**********************前端页面和js代码*********************************/
  /*      if(makeType ==1 ||makeType ==3){
            makeAddjs(o,pid,parentPath);
            makeAddjsp(o,pid,parentPath);
            makeDetailjs(o,pid,parentPath);
            makeDetailjsp(o,pid,parentPath);
            makeEditjs(o,pid,parentPath);
            makeEditjsp(o,pid,parentPath);
            makeListjs(o,pid,parentPath);
            makeListjsp(o,pid,parentPath);
        }*/
    }

    /***********
     * 控制器方法实现
     * @param o      操作对象
     * @param pid    对象主键
     */
    public static void makeController(Object o, String pid, String... parentPath) {
        System.out.println(getClassName(o));
        String className = getClassName(o);
        System.out.println(className.substring(className.lastIndexOf(".") + 1));
        System.out.println(camelToUnderline(className.substring(className.lastIndexOf(".") + 1)).substring(1));


        Map<String, Object> root = new HashMap<String, Object>();

        String simpleclassName = className.substring(className.lastIndexOf(".") + 1);

        String iServiceName = getClassPackage(o).replace(MODEL, SERVICE) + ".I" + simpleclassName + "Service";
        String simpleIServiceName = "I" + simpleclassName + "Service";


        //服务实现接口
        String packagePath = getClassPackage(o).replace(MODEL, SERVICE) + ".impl";
        String serviceImplName = packagePath + "." + simpleclassName + "ServiceImpl";
        String simpleServiceImplName = simpleclassName + "ServiceImpl";

        //控制器
        packagePath = getClassPackage(o).replace(MODEL, CONTROLLER);
        String controllerName = packagePath + "." + simpleclassName + "Controller";
        String simpleControllerName = simpleclassName + "Controller";

//		String dbPid="id";
//		String pid="cid";


        root.put("packagePath", packagePath);
        root.put("className", className);
        root.put("simpleclassName", simpleclassName);

        root.put("iServiceName", iServiceName);
        root.put("simpleIServiceName", simpleServiceImplName);

        root.put("serviceImplName", serviceImplName);
        root.put("simpleServiceImplName", simpleServiceImplName);

        root.put("controllerName", controllerName);
        root.put("simpleControllerName", simpleControllerName);
        root.put("pidType", getFiledType(o, pid));

//		String parentPackageName =packagePath.substring(packagePath.lastIndexOf(".")+1);
        String parentPackageName = "";
        if (parentPath != null && parentPath.length > 0 && parentPath[0] != null && parentPath[0] != "") {
            parentPackageName = parentPath[0];
        } else {
            parentPackageName = packagePath.replace("com.web.", "").replace(".controller", "").replace(".", "/");
        }
        root.put("parentPackageName", parentPackageName);

        root.put("pid", pid);

        List<Object> attrs = new ArrayList<Object>();
        List<Map> pm = getFiledsInfo(o);
        for (Map item : pm) {
            if (!"serialVersionUID".equals(item.get("name").toString())) {
                attrs.add(new AttrUtil(item.get("name").toString(), item.get("type").toString(), camelToUnderline(item.get("name").toString())));
            }
        }
        root.put("attrs", attrs);


        Configuration cfg = new Configuration();
        String path = null;
        String relativelyPath = System.getProperty("user.dir");
        if(!IS_JAR) {
//        String path = relativelyPath+"/src/ftl";
            path = relativelyPath + RESURCE_PATH + "/ftl/server";
        }else{
            path = ServerCode.class.getResource("/")+"/ftl/server";
            System.out.println(path);
        }
        try {
            if(!IS_JAR) {
                cfg.setDirectoryForTemplateLoading(new File(path));
            }else {
                cfg.setClassForTemplateLoading(ServerCode.class, "/ftl/server");
            }
            Template template = cfg.getTemplate("/Controller.ftl");
            StringWriter out = new StringWriter();
            template.process(root, out);

            System.out.println(out.toString());
            String javaPathName = relativelyPath + JAVA_PATH + "/" + controllerName.replace(".", "/") + ".java";
            AppendFileUtil.writeFile(javaPathName, out.toString());
        } catch (IOException e) {
            System.out.println("Cause==>" + e.getCause());
        } catch (TemplateException e) {
            System.out.println("Cause==>" + e.getCause());
        }
    }


    /*****
     * 服务实现生成
     * @param o      操作对象
     * @param pid    对象主键
     */
    public static void makeServiceImpl(Object o, String pid) {
        System.out.println(getClassName(o));
        String className = getClassName(o);
        System.out.println(className.substring(className.lastIndexOf(".") + 1));
        System.out.println(camelToUnderline(className.substring(className.lastIndexOf(".") + 1)).substring(1));


        Map<String, Object> root = new HashMap<String, Object>();
        String packagePath = getClassPackage(o).replace(MODEL, SERVICE) + ".impl";
        String simpleclassName = className.substring(className.lastIndexOf(".") + 1);
        String serviceImplName = packagePath + "." + simpleclassName + "ServiceImpl";
        String simpleServiceImplName = simpleclassName + "ServiceImpl";

        String iServiceName = getClassPackage(o).replace(MODEL, SERVICE) + ".I" + simpleclassName + "Service";
        String simpleIServiceName = "I" + simpleclassName + "Service";

        String javaMapperName = className.replace(MODEL, DAO) + "Mapper";
        String simpleMapperName = simpleclassName + "Mapper";

//		String dbPid="id";
//		String pid="cid";


        root.put("packagePath", packagePath);
        root.put("className", className);
        root.put("simpleclassName", simpleclassName);

        root.put("iServiceName", iServiceName);
        root.put("simpleIServiceName", simpleIServiceName);

        root.put("serviceImplName", serviceImplName);
        root.put("simpleServiceImplName", simpleServiceImplName);

        root.put("javaMapperName", javaMapperName);
        root.put("simpleMapperName", simpleMapperName);
        root.put("pid", pid);
        root.put("pidType", getFiledType(o, pid));

        List<Object> attrs = new ArrayList<Object>();
        List<Map> pm = getFiledsInfo(o);
        for (Map item : pm) {
            if (!"serialVersionUID".equals(item.get("name").toString())) {
                attrs.add(new AttrUtil(item.get("name").toString(), item.get("type").toString(), camelToUnderline(item.get("name").toString())));
            }
        }
        root.put("attrs", attrs);


        Configuration cfg = new Configuration();
        String relativelyPath = System.getProperty("user.dir");
//        String path = relativelyPath+"/src/ftl";
        String path = relativelyPath + RESURCE_PATH + "/ftl/server";
        try {
            if(!IS_JAR) {
                cfg.setDirectoryForTemplateLoading(new File(path));
            }else {
                cfg.setClassForTemplateLoading(ServerCode.class, "/ftl/server");
            }
            Template template = cfg.getTemplate("/ServiceImpl.ftl");
            StringWriter out = new StringWriter();
            template.process(root, out);

            System.out.println(out.toString());
            String javaPathName = relativelyPath + JAVA_PATH + "/" + serviceImplName.replace(".", "/") + ".java";
            AppendFileUtil.writeFile(javaPathName, out.toString());
        } catch (IOException e) {
            System.out.println("Cause==>" + e.getCause());
        } catch (TemplateException e) {
            System.out.println("Cause==>" + e.getCause());
        }
    }


    /****
     * 生成服务接口
     * @param o      操作对象
     * @param pid    对象主键
     */
    public static void makeIService(Object o, String pid) {
        System.out.println(getClassName(o));
        String className = getClassName(o);
        System.out.println(className.substring(className.lastIndexOf(".") + 1));
        System.out.println(camelToUnderline(className.substring(className.lastIndexOf(".") + 1)).substring(1));


        Map<String, Object> root = new HashMap<String, Object>();
        String packagePath = getClassPackage(o).replace(MODEL, SERVICE);
        String simpleclassName = className.substring(className.lastIndexOf(".") + 1);
        String iServiceName = packagePath + ".I" + simpleclassName + "Service";
        String simpleIServiceName = "I" + simpleclassName + "Service";
//		String dbPid="id";
//		String pid="cid";

        root.put("className", className);
        root.put("packagePath", packagePath);
        root.put("simpleclassName", simpleclassName);
        root.put("simpleIServiceName", simpleIServiceName);
        root.put("pid", pid);
        root.put("pidType", getFiledType(o, pid));

        Configuration cfg = new Configuration();
        String relativelyPath = System.getProperty("user.dir");
//        String path = relativelyPath+"/src/ftl";
        String path = relativelyPath + RESURCE_PATH + "/ftl/server";

        try {
            if(!IS_JAR) {
                cfg.setDirectoryForTemplateLoading(new File(path));
            }else {
                cfg.setClassForTemplateLoading(ServerCode.class, "/ftl/server");
            }
            Template template = cfg.getTemplate("/IService.ftl");
            StringWriter out = new StringWriter();
            template.process(root, out);

            System.out.println(out.toString());
            String javaPathName = relativelyPath + JAVA_PATH + "/" + iServiceName.replace(".", "/") + ".java";
            AppendFileUtil.writeFile(javaPathName, out.toString());
        } catch (IOException e) {
            System.out.println("Cause==>" + e.getCause());
        } catch (TemplateException e) {
            System.out.println("Cause==>" + e.getCause());
        }
    }


    /****
     * javaMapper生成
     * @param o      操作对象
     * @param pid    对象主键
     */
    public static void makeJavaMapper(Object o, String pid) {
        System.out.println(getClassName(o));
        String className = getClassName(o);
        System.out.println(className.substring(className.lastIndexOf(".") + 1));
        System.out.println(camelToUnderline(className.substring(className.lastIndexOf(".") + 1)).substring(1));


        Map<String, Object> root = new HashMap<String, Object>();
        String packagePath = getClassPackage(o).replace(MODEL, DAO);
        String simpleclassName = className.substring(className.lastIndexOf(".") + 1);
        String javaMapperName = className.replace(MODEL, DAO) + "Mapper";
        String simpleMapperName = simpleclassName + "Mapper";

        root.put("className", className);
        root.put("packagePath", packagePath);
        root.put("simpleclassName", simpleclassName);
        root.put("simpleMapperName", simpleMapperName);
        root.put("pid", pid);
        root.put("pidType", getFiledType(o, pid));

        Configuration cfg = new Configuration();
        String relativelyPath = System.getProperty("user.dir");
//        String path = relativelyPath+"/src/ftl";
        String path = relativelyPath + RESURCE_PATH + "/ftl/server";

        try {
            if(!IS_JAR) {
                cfg.setDirectoryForTemplateLoading(new File(path));
            }else {
                cfg.setClassForTemplateLoading(ServerCode.class, "/ftl/server");
            }
            Template template = cfg.getTemplate("/javaMapper.ftl");
            StringWriter out = new StringWriter();
            template.process(root, out);

            System.out.println(out.toString());
            String javaPathName = relativelyPath + JAVA_PATH + "/" + javaMapperName.replace(".", "/") + ".java";
            AppendFileUtil.writeFile(javaPathName, out.toString());
        } catch (IOException e) {
            System.out.println("Cause==>" + e.getCause());
        } catch (TemplateException e) {
            System.out.println("Cause==>" + e.getCause());
        }
    }


    /****
     * javaMapper生成
     * @param o      操作对象
     */
    public static void makeModulQuery(Object o) {
        System.out.println(getClassName(o));
        String className = getClassName(o);
        System.out.println(className.substring(className.lastIndexOf(".") + 1));
        System.out.println(camelToUnderline(className.substring(className.lastIndexOf(".") + 1)).substring(1));


        Map<String, Object> root = new HashMap<String, Object>();
        String packagePath = getClassPackage(o);
        String simpleclassName = className.substring(className.lastIndexOf(".") + 1);
        String moduelQueryName = packagePath+"."+simpleclassName+"Query";

        root.put("className", className);
        root.put("packagePath", packagePath);
        root.put("simpleclassName", simpleclassName);

        Configuration cfg = new Configuration();
        String path =null;
        String relativelyPath = System.getProperty("user.dir");
//        String path = relativelyPath+"/src/ftl";
            path = relativelyPath + RESURCE_PATH + "/ftl/server";


        try {
            if(!IS_JAR) {
                cfg.setDirectoryForTemplateLoading(new File(path));
            }else {
                cfg.setClassForTemplateLoading(ServerCode.class, "/ftl/server");
            }
            Template template = cfg.getTemplate("/ModuleQuery.ftl");
            StringWriter out = new StringWriter();
            template.process(root, out);

            System.out.println(out.toString());
            String javaPathName = relativelyPath + JAVA_PATH + "/" + moduelQueryName.replace(".", "/") + ".java";
            AppendFileUtil.writeFile(javaPathName, out.toString());
        } catch (IOException e) {
            System.out.println("Cause==>" + e.getCause());
        } catch (TemplateException e) {
            System.out.println("Cause==>" + e.getCause());
        }
    }



    /****
     * xml 文件生成
     * @param o      操作对象
     * @param dbPid
     * @param pid    对象主键
     */
    public static void makeXmlMapper(Object o, String dbPid, String pid, String tableName) {
        System.out.println(getClassName(o));
        String className = getClassName(o);
        System.out.println(className.substring(className.lastIndexOf(".") + 1));
        System.out.println(camelToUnderline(className.substring(className.lastIndexOf(".") + 1)).substring(1));
        String simpleclassName = className.substring(className.lastIndexOf(".") + 1);

        List<Object> attrs = new ArrayList<Object>();
        List<Map> pm = getFiledsInfo(o);
        for (Map item : pm) {
            if (!"serialVersionUID".equals(item.get("name").toString())) {
                attrs.add(new AttrUtil(item.get("name").toString(), item.get("type").toString(), camelToUnderline(item.get("name").toString())));
            }
        }

        Map<String, Object> root = new HashMap<String, Object>();
        String xmlMapperName = className.replace(MODEL, DAO) + "Mapper";
        if (tableName == null || "".equals(tableName.trim())) {
            tableName = camelToUnderline(className.substring(className.lastIndexOf(".") + 1)).substring(1);
        }

        root.put("mapperName", xmlMapperName);
        root.put("modelName", className);
        root.put("simpleclassName", simpleclassName);
        root.put("attrs", attrs);
        root.put("dbPid", dbPid);
        root.put("pid", pid);
        root.put("tableName", tableName);
        root.put("pidType", getFiledType(o, pid));

        Configuration cfg = new Configuration();
        String relativelyPath = System.getProperty("user.dir");
//		String path = AutoTest.class.getResource("/").getPath()+"ftl";
        String path = relativelyPath + RESURCE_PATH + "/ftl/server";

        try {
            if(!IS_JAR) {
                cfg.setDirectoryForTemplateLoading(new File(path));
            }else {
                cfg.setClassForTemplateLoading(ServerCode.class, "/ftl/server");
            }
            Template template = cfg.getTemplate("/xmlMapper.ftl");
            StringWriter out = new StringWriter();
            template.process(root, out);

            String simpleXmlMapperName = xmlMapperName.substring(xmlMapperName.lastIndexOf(".") + 1);
            System.out.println(out.toString());
            String xmlPathName = relativelyPath + RESURCE_PATH + "/mapper/" + simpleXmlMapperName.replace(".", "/") + ".xml";
            AppendFileUtil.writeFile(xmlPathName, out.toString());
        } catch (IOException e) {
            System.out.println("Cause==>" + e.getCause());
        } catch (TemplateException e) {
            System.out.println("Cause==>" + e.getCause());
        }

    }


    /****
     * 获取对象包路径
     * @param o      操作对象
     * @return
     */
    public static String getClassPackage(Object o) {
        return o.getClass().getPackage().getName();
    }

    /******
     * 获取类名称
     * @param o      操作对象
     * @return com.test.model.HyBusiness
     */
    public static String getClassName(Object o) {
        return o.getClass().getName();
    }

    /***
     * 下划线"_"
     */
    public static final char UNDERLINE = '_';

    /***
     * 驼峰规则转下划线
     * @param param 驼峰写法字符串
     */
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /****
     * 获取对象属性和属性类型
     * @param o      操作对象
     * @return List<Map> 对象属性和属性类型集合
     */
    public static List getFiledsInfo(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        List list = new ArrayList();
        Map infoMap = null;
        String typeString = null;
        for (int i = 0; i < fields.length; i++) {
            infoMap = new HashMap();
            typeString = fields[i].getType().toString();
            infoMap.put("type", typeString.indexOf(".") < 0 ? typeString : typeString.substring(typeString.lastIndexOf(".") + 1));
            infoMap.put("name", fields[i].getName());
//        infoMap.put("value", getFieldValueByName(fields[i].getName(), o));
            list.add(infoMap);
        }
        return list;
    }

    /****
     * 字符串首字母小写转换
     * @param name 待转换字符串
     * @return String 转换后字符串
     */
    public static String toLowerCaseFirstOne(String name) {
        char[] cs = name.toCharArray();
        cs[0] += 32;
        return String.valueOf(cs);

    }

    /***
     * 获取对象指定字段的类型
     * @param o  对象
     * @param filedName 字段
     * @return 字段类型
     */
    public static String getFiledType(Object o, String filedName) {
        String filedType = "";
        List<Map<String, String>> l = getFiledsInfo(o);
        for (Map<String, String> item : l) {
            if (item.get("name").equals(filedName)) {
                filedType = item.get("type");
                break;
            }
        }
        return filedType;
    }


}
