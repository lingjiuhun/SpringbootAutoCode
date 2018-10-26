package com.code.base.util.utils;

import java.util.List;

public class ModelBean {
    private String packageName; //包名
    private String modelName;//实体类名
    private String modelNote;//实体类注释
    private String dbPid;    //数据库主键名
    private String pid;      //实体主键名
    private List<ModelProperty> propertys; //属性


    public String getPackageName() {
        return packageName;
    }


    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }


    public String getModelName() {
        return modelName;
    }


    public void setModelName(String modelName) {
        this.modelName = modelName;
    }


    public String getModelNote() {
        return modelNote;
    }


    public void setModelNote(String modelNote) {
        this.modelNote = modelNote;
    }


    public String getDbPid() {
        return dbPid;
    }


    public void setDbPid(String dbPid) {
        this.dbPid = dbPid;
    }


    public String getPid() {
        return pid;
    }


    public void setPid(String pid) {
        this.pid = pid;
    }


    public List<ModelProperty> getPropertys() {
        return propertys;
    }


    public void setPropertys(List<ModelProperty> propertys) {
        this.propertys = propertys;
    }


}
