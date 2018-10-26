package ${packagePath};

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;

import ${className};
import ${className}Query;

/**
* @Author:
* @Description: 服务接口
* @date: ${.now}
*/
public interface ${simpleIServiceName} {

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
    PageInfo<${simpleclassName}> selectByPage(${simpleclassName}Query param, Integer page, Integer pageSize) throws Exception;


    /**
    * 新增
    *
    * @param ${simpleclassName?uncap_first}
    * @Author:
    * @return: java.lang.Integer
    * @exception:
    * @date: ${.now}
    */
    Integer insert(${simpleclassName} ${simpleclassName?uncap_first}) throws Exception;


    /**
    * 根据主键更新
    *
    * @param ${simpleclassName?uncap_first}
    * @Author:
    * @return: java.lang.Integer
    * @exception:
    * @date: ${.now}
    */
    Integer updateById(${simpleclassName} ${simpleclassName?uncap_first}) throws Exception;

    /**
    * 根据主键删除
    *
    * @param ${pid}
    * @Author:
    * @return: int
    * @exception:
    * @date: ${.now}
    */
    int deleteById(<#if 'Long' = pidType>Long<#else>String</#if> ${pid}) throws Exception;

    /**
    * 根据主键批量删除
    *
    * @param ${pid}s
    * @Author:
    * @return: int
    * @exception:
    * @date: ${.now}
    */
    Integer deleteBatchByIds(List ${pid}s) throws Exception;


    /**
    * 根据主键查询
    *
    * @param ${pid}
    * @Author:
    * @return: ${className}
    * @exception:
    * @date: ${.now}
    */
    ${simpleclassName} selectById(<#if 'Long' = pidType>Long<#else>String</#if> ${pid}) throws Exception;


}