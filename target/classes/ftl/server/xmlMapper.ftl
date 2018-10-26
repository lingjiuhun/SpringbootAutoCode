<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${mapperName}">
    <resultMap id="BaseResultMap" type="${modelName}">
        <id column="${dbPid}" property="${pid}" jdbcType="<#if 'Long' = pidType>BIGINT<#else>VARCHAR</#if>"/>
    <#list attrs as a>
        <result column="${a.dbField}" property="${a.field}" jdbcType="<#if a.type='Long'>BIGINT<#else>VARCHAR</#if>"/>
    </#list>
    </resultMap>

    <sql id="Base_Column_List">
    <#list attrs as a>
        <#if a_has_next>
        ${a.dbField},
        <#else>
        ${a.dbField}
        </#if>
    </#list>
    </sql>

    <select id="selectByPrimaryKey" resultMap="BaseResultMap"
            parameterType="<#if 'Long' = pidType>java.lang.Long<#else>java.lang.String</#if>">
        select
        <include refid="Base_Column_List"/>
        from ${tableName}
        where  ${dbPid} =${r" #{ "}${pid}${r",jdbcType="}<#if 'Long' = pidType>BIGINT<#else>VARCHAR</#if>${r"}"}
    </select>

    <delete id="deleteByPrimaryKey" parameterType="<#if 'Long' = pidType>java.lang.Long<#else>java.lang.String</#if>">
        delete from ${tableName}
        where ${dbPid} = ${r" #{ "}${pid}${r",jdbcType="}<#if 'Long' = pidType>BIGINT<#else>VARCHAR</#if>${r"}"}
    </delete>

    <delete id="deleteBatchByPrimaryKey" parameterType="java.util.List">
        delete from ${tableName}
        where ${dbPid} in
        <foreach collection="list" item="${pid}" index="index"
                 open="(" close=")" separator=",">
        ${r" #{ "}${pid}${r",jdbcType="}<#if 'Long' = pidType>BIGINT<#else>VARCHAR</#if>${r"}"}
        </foreach>
    </delete>


    <insert id="insertSelective" parameterType="${modelName}">
        insert into ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#list attrs as a>
            <if test="${a.field} != null">
            ${a.dbField},
            </if>
        </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
        <#list attrs as a>
            <if test="${a.field} != null">
            ${r" #{ "}${a.field}${r",jdbcType="}<#if a.type='Long'> BIGINT <#else> VARCHAR </#if>${r"}"},
            </if>
        </#list>
        </trim>
    <#if 'Long' = pidType>
        <selectKey resultType="java.lang.Long" order="AFTER"
                   keyProperty="${pid}">
            SELECT LAST_INSERT_ID() AS ${dbPid}
        </selectKey>
    </#if>
    </insert>

    <select id="selectByPropertyByPage" resultMap="BaseResultMap"
            parameterType="${modelName}Query">
        select
        <include refid="Base_Column_List"/>
        from ${tableName}
        where 1=1
    <#list attrs as a>
        <if test="${a.field} != null">
            AND ${a.dbField} = ${r" #{ "}${a.field}${r",jdbcType="}<#if a.type='Long'> BIGINT <#else>
            VARCHAR </#if>${r"}"}
        </if>
    </#list>
        ORDER BY ${dbPid} desc
    </select>

    <select id="selectCountByProperty" resultType="java.lang.Integer"
            parameterType="Map">
        select
        count(1)
        from ${tableName}
        where 1=1
    <#list attrs as a>
        <if test="${a.field} != null">
            AND ${a.dbField} = ${r" #{ "}${a.field}${r",jdbcType="}<#if a.type='Long'> BIGINT <#else>
            VARCHAR </#if>${r"}"}
        </if>
    </#list>
    </select>

    <update id="updateByPrimaryKeySelective" parameterType="${modelName}">
        <if test="${pid} !=null">
            update ${tableName}
            <set>
            <#list attrs as a>
                <if test="${a.field} != null">
                    <#if a_has_next>
                    ${a.dbField} = ${r" #{ "}${a.field}${r",jdbcType="}<#if a.type='Long'> BIGINT <#else>
                        VARCHAR </#if>${r"}"},
                    <#else>
                    ${a.dbField} = ${r" #{ "}${a.field}${r",jdbcType="}<#if a.type='Long'> BIGINT <#else>
                        VARCHAR </#if>${r"}"}
                    </#if>
                </if>
            </#list>
            </set>
            where ${dbPid} = ${r" #{ "}${pid}${r",jdbcType="}<#if 'Long' = pidType>BIGINT<#else>VARCHAR</#if>${r"}"}
        </if>
    </update>

</mapper>