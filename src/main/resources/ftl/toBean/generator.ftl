<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>


    <context id="DB2Tables" targetRuntime="MyBatis3">
        <!-- 指定生成的java文件的编码,没有直接生成到项目时中文可能会乱码 -->
        <property name="javaFileEncoding" value="UTF-8"/>
        <!-- 这里的type里写的是你的实现类的类全路径 -->
        <commentGenerator type="com.code.base.util.model.MyCommentGenerator">
        </commentGenerator>

        <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                        connectionURL="jdbc:mysql://${urlDbName}"
                        userId="${dbUser}"
                        password="${dbPassWord}">
        </jdbcConnection>
        <javaTypeResolver>
            <property name="forceBigDecimals" value="false"/>
        </javaTypeResolver>

        <javaModelGenerator targetPackage="${beanPackage}" targetProject="src">
            <property name="enableSubPackages" value="true"/>
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>

        <table tableName="${tableName}" domainObjectName="${beanName}" enableCountByExample="false"
               enableUpdateByExample="false" enableDeleteByExample="false" enableSelectByExample="false"
               selectByExampleQueryId="false"></table>
    </context>
</generatorConfiguration>  