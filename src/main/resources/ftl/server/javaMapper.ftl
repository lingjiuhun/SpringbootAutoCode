package ${packagePath};

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

import ${className};
import ${className}Query;

/**
* @Author:
* @Description: mapper接口
* @date: ${.now}
*/
@Component
@Mapper
public interface ${simpleMapperName} {


    /**
    * 根据主键查询
    *
    * @param id
    * @Author:
    * @return: ${className}
    * @exception:
    * @date: ${.now}
    */
    ${simpleclassName} selectByPrimaryKey(<#if 'Long' = pidType>Long<#else>String</#if> ${pid});

    /**
    * 根据主键删除
    *
    * @param id
    * @Author:
    * @return: int
    * @exception:
    * @date: ${.now}
    */
    int deleteByPrimaryKey(<#if 'Long' = pidType>Long<#else>String</#if> ${pid});

    /**
    * 根据主键批量删除
    *
    * @param list
    * @Author:
    * @return: int
    * @exception:
    * @date: ${.now}
    */
    int deleteBatchByPrimaryKey(List<String> list);


    /**
    * 新增
    *
    * @param record
    * @Author:
    * @return: int
    * @exception:
    * @date: ${.now}
    */
    int insertSelective(${simpleclassName} record);

    /**
    * 根据属性分页查询
    *
    * @param map
    * @Author:
    * @return: java.util.List<${className}>
    * @exception:
    * @date: ${.now}
    */
    List<${simpleclassName}> selectByPropertyByPage(${simpleclassName}Query query);

    /**
    * 根据属性查询记录数
    *
    * @param map
    * @Author:
    * @return: int
    * @exception:
    * @date: ${.now}
    */
    int selectCountByProperty(Map map);

    /**
    * 根据主键更新记录
    *
    * @param record
    * @Author:
    * @return: int
    * @exception:
    * @date: ${.now}
    */
    int updateByPrimaryKeySelective(${simpleclassName} record);

    }