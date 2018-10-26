package com.hollysys.hollipark.dispatching.core.entity;

public class DeviceType {
/**
* 设备类型Id
*/
private Long id;

/**
* 生产厂商id
*/
private Integer manufacture;

/**
* 图片文件uri
*/
private String photo;

/**
* 设备类型 id
*/
private String categoryId;

/**
* 设备型号名称
*/
private String typeName;

/**
* 负载
*/
private String payload;

/**
* 臂长
*/
private String armLength;

/**
* 自由度
*/
private String dof;

/**
* 安装方式 参考/dictionary数据字典
*/
private String inatallWayId;

/**
* 重复精度
*/
private String repeatability;

/**
* 环境温度
*/
private String tempurature;

/**
* 近似重量
*/
private String weight;

/**
* 额定功率
*/
private String ratedPower;

/**
* 控制柜型号
*/
private String cabinetType;

/**
* 防护等级
*/
private String protectLevel;

/**
* 是否删除
*/
private Byte deleted;

/**
* 厂商电话
*/
private String mobile;

/**
* 设备类型Id
* @return id 设备类型Id
*/
public Long getId() {
return id;
}

/**
* 设备类型Id
* @param id 设备类型Id
*/
public void setId(Long id) {
this.id = id;
}

/**
* 生产厂商id
* @return manufacture 生产厂商id
*/
public Integer getManufacture() {
return manufacture;
}

/**
* 生产厂商id
* @param manufacture 生产厂商id
*/
public void setManufacture(Integer manufacture) {
this.manufacture = manufacture;
}

/**
* 图片文件uri
* @return photo 图片文件uri
*/
public String getPhoto() {
return photo;
}

/**
* 图片文件uri
* @param photo 图片文件uri
*/
public void setPhoto(String photo) {
this.photo = photo == null ? null : photo.trim();
}

/**
* 设备类型 id
* @return category_id 设备类型 id
*/
public String getCategoryId() {
return categoryId;
}

/**
* 设备类型 id
* @param categoryId 设备类型 id
*/
public void setCategoryId(String categoryId) {
this.categoryId = categoryId == null ? null : categoryId.trim();
}

/**
* 设备型号名称
* @return type_name 设备型号名称
*/
public String getTypeName() {
return typeName;
}

/**
* 设备型号名称
* @param typeName 设备型号名称
*/
public void setTypeName(String typeName) {
this.typeName = typeName == null ? null : typeName.trim();
}

/**
* 负载
* @return payload 负载
*/
public String getPayload() {
return payload;
}

/**
* 负载
* @param payload 负载
*/
public void setPayload(String payload) {
this.payload = payload == null ? null : payload.trim();
}

/**
* 臂长
* @return arm_length 臂长
*/
public String getArmLength() {
return armLength;
}

/**
* 臂长
* @param armLength 臂长
*/
public void setArmLength(String armLength) {
this.armLength = armLength == null ? null : armLength.trim();
}

/**
* 自由度
* @return dof 自由度
*/
public String getDof() {
return dof;
}

/**
* 自由度
* @param dof 自由度
*/
public void setDof(String dof) {
this.dof = dof == null ? null : dof.trim();
}

/**
* 安装方式 参考/dictionary数据字典
* @return inatall_way_id 安装方式 参考/dictionary数据字典
*/
public String getInatallWayId() {
return inatallWayId;
}

/**
* 安装方式 参考/dictionary数据字典
* @param inatallWayId 安装方式 参考/dictionary数据字典
*/
public void setInatallWayId(String inatallWayId) {
this.inatallWayId = inatallWayId == null ? null : inatallWayId.trim();
}

/**
* 重复精度
* @return repeatability 重复精度
*/
public String getRepeatability() {
return repeatability;
}

/**
* 重复精度
* @param repeatability 重复精度
*/
public void setRepeatability(String repeatability) {
this.repeatability = repeatability == null ? null : repeatability.trim();
}

/**
* 环境温度
* @return tempurature 环境温度
*/
public String getTempurature() {
return tempurature;
}

/**
* 环境温度
* @param tempurature 环境温度
*/
public void setTempurature(String tempurature) {
this.tempurature = tempurature == null ? null : tempurature.trim();
}

/**
* 近似重量
* @return weight 近似重量
*/
public String getWeight() {
return weight;
}

/**
* 近似重量
* @param weight 近似重量
*/
public void setWeight(String weight) {
this.weight = weight == null ? null : weight.trim();
}

/**
* 额定功率
* @return rated_power 额定功率
*/
public String getRatedPower() {
return ratedPower;
}

/**
* 额定功率
* @param ratedPower 额定功率
*/
public void setRatedPower(String ratedPower) {
this.ratedPower = ratedPower == null ? null : ratedPower.trim();
}

/**
* 控制柜型号
* @return cabinet_type 控制柜型号
*/
public String getCabinetType() {
return cabinetType;
}

/**
* 控制柜型号
* @param cabinetType 控制柜型号
*/
public void setCabinetType(String cabinetType) {
this.cabinetType = cabinetType == null ? null : cabinetType.trim();
}

/**
* 防护等级
* @return protect_level 防护等级
*/
public String getProtectLevel() {
return protectLevel;
}

/**
* 防护等级
* @param protectLevel 防护等级
*/
public void setProtectLevel(String protectLevel) {
this.protectLevel = protectLevel == null ? null : protectLevel.trim();
}

/**
* 是否删除
* @return deleted 是否删除
*/
public Byte getDeleted() {
return deleted;
}

/**
* 是否删除
* @param deleted 是否删除
*/
public void setDeleted(Byte deleted) {
this.deleted = deleted;
}

/**
* 厂商电话
* @return mobile 厂商电话
*/
public String getMobile() {
return mobile;
}

/**
* 厂商电话
* @param mobile 厂商电话
*/
public void setMobile(String mobile) {
this.mobile = mobile == null ? null : mobile.trim();
}
}
