package ${packagePath};

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;
import java.util.Objects;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${className};
import ${className}Query;
import ${javaMapperName};
import ${iServiceName};

/**
* @Author:
* @Description: 服务实现
* @date:
*/
@Log4j2
@Service
public class ${simpleServiceImplName} implements ${simpleIServiceName} {
@Autowired
private ${simpleMapperName} ${simpleMapperName?uncap_first};


    /**
    * 根据参数分页查询
    *
    * @param param
    * @param page
    * @param pageSize
    * @Author:
    * @return: com.github.pagehelper.PageInfo<${className}>
    * @exception:
    * @date: ${.now}
    */
    @Override
    public PageInfo<${simpleclassName}> selectByPage(${simpleclassName}Query param, Integer page, Integer pageSize) throws Exception {
        int limit = pageSize != null ? pageSize : 10;
        int offset = (page != null ? page : 1);
        PageHelper.startPage(offset, limit);

        List<${simpleclassName}> datas = ${simpleMapperName?uncap_first}.selectByPropertyByPage(param);

        PageInfo<${simpleclassName}> pageInfo = new PageInfo<>(datas);
        return pageInfo;
    }


    /**
    * 新增
    *
    * @param ${simpleclassName?uncap_first}
    * @Author:
    * @return: java.lang.Integer
    * @exception:
    * @date: ${.now}
    */
    @Override
    public Integer insert(${simpleclassName} ${simpleclassName?uncap_first}) throws Exception {
        Integer r = 0;
        //数据非空判断
        if (Objects.isNull(${simpleclassName?uncap_first})) {
            return r = -1;
        }

        //默认数据处理

        //数据入库
        r = ${simpleMapperName?uncap_first}.insertSelective(${simpleclassName?uncap_first});

        return r;
    }


    /**
    * 根据主键更新
    *
    * @param ${simpleclassName?uncap_first}
    * @Author:
    * @return: java.lang.Integer
    * @exception:
    * @date: ${.now}
    */
    @Override
    public Integer updateById(${simpleclassName} ${simpleclassName?uncap_first}) throws Exception {
        Integer r = 0;
        //主键存在判断
        if (${simpleclassName?uncap_first} == null || ${simpleclassName?uncap_first}.get${pid?cap_first}() == null) {
            return r = -1;
        }
        //唯一性判断

        //数据更新
        r = ${simpleMapperName?uncap_first}.updateByPrimaryKeySelective(${simpleclassName?uncap_first});

        return r;
    }


    /**
    * 根据主键删除
    *
    * @param ${pid}
    * @Author:
    * @return: int
    * @exception:
    * @date: ${.now}
    */
    @Override
    public int deleteById(<#if 'Long' = pidType>Long<#else>String</#if> ${pid}) throws Exception {
        int r = 0;
        //${pid}非空判断
        if (${pid} == null <#if 'Long' = pidType> || ${pid} == 0 </#if>) {
            return -1;
        }
        //数据删除
        r = ${simpleMapperName?uncap_first}.deleteByPrimaryKey(${pid});

        return r;
    }


    /**
    * 根据主键批量删除
    *
    * @param ${pid}s
    * @Author:
    * @return: int
    * @exception:
    * @date: ${.now}
    */
    @Override
    public Integer deleteBatchByIds(List ${pid}s) throws Exception{
        int result = -1;

        //${pid}s非空判断
        if (${pid}s == null || ${pid}s.size() <= 0) {
            return -1;
        }
        result = ${simpleMapperName?uncap_first}.deleteBatchByPrimaryKey(${pid}s);

        return result;
    }


    /**
    * 根据主键查询
    *
    * @param ${pid}
    * @Author:
    * @return: ${className}
    * @exception:
    * @date: ${.now}
    */
    @Override
    public ${simpleclassName} selectById(<#if 'Long' = pidType>Long<#else>String</#if> ${pid}) throws Exception {
        ${simpleclassName} ${simpleclassName?uncap_first} = null;
        //id非空判断
        if (${pid} != null <#if 'Long' = pidType> || ${pid} > 0 </#if>) {
            ${simpleclassName?uncap_first} = ${simpleMapperName?uncap_first}.selectByPrimaryKey(${pid});
        }
        return ${simpleclassName?uncap_first};
    }


}