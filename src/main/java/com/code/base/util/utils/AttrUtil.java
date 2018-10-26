package com.code.base.util.utils;

public class AttrUtil {
    public String field;    //字段名
    public String type;     //字段类型
    public String dbField;  //对应数据库字段

    public AttrUtil(String field, String type, String dbField) {
        this.field = field;
        this.type = type;
        this.dbField = dbField;
    }

    public String getField() {
        return this.field;
    }

    public String getType() {
        return this.type;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDbField() {
        return dbField;
    }

    public void setDbField(String dbField) {
        this.dbField = dbField;
    }

}
