package ${packagePath};


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.code.base.util.utils.RestResponse;
import com.github.pagehelper.PageInfo;


import ${className};
import ${serviceImplName};

/**
* @Author:
* @Description: 管理
* @date: ${.now}
*/
@Log4j2
@RestController
@RequestMapping("/api/v1/${simpleclassName?uncap_first}s")
@Api(description = "${simpleclassName}-API", tags = "${simpleclassName}-API")
public class ${simpleControllerName}{
@Autowired
private ${simpleServiceImplName} ${simpleServiceImplName?uncap_first};

    /**
    * 分页查询
    *
    * @param page
    * @param pageSize
    * @Author:
    * @return: com.code.base.util.utils.RestResponse<com.github.pagehelper.PageInfo>
    * @exception:
    * @date: ${.now}
    */
    @ApiOperation(value = "获取..列表", notes = "获取..列表")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public RestResponse<PageInfo> getList(
        @RequestParam(value = "page", required = false) Integer page,
        @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        PageInfo<${simpleclassName}> pageInfo = null;
        try {
            pageInfo = ${simpleServiceImplName?uncap_first}.selectByPage(null, page == null ? 1 : page, pageSize == null ?
            Integer.MAX_VALUE : pageSize);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex.getMessage());
        }

        return new RestResponse().setSuccess(true).setMessage("success").setData(pageInfo);
    }


    /**
    * 新增
    *
    * @param ${simpleclassName?uncap_first}
    * @Author:
    * @return: com.code.base.util.utils.RestResponse
    * @exception:
    * @date: ${.now}
    */
    @ApiOperation(value = "新增..", notes = "新增..")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RestResponse doAdd(@RequestBody ${simpleclassName} ${simpleclassName?uncap_first}) {
        RestResponse result = new RestResponse();
        try {
            Integer r = ${simpleServiceImplName?uncap_first}.insert(${simpleclassName?uncap_first});
            if (r > 0) {
                result.setSuccess(true).setMessage("success");
            } else {
                result.setSuccess(false).setMessage("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            result.setSuccess(false).setMessage("fail");
        }
        return result;
    }


    /**
    * 根据主键删除
    *
    * @param ${pid}
    * @Author:
    * @return: com.code.base.util.utils.RestResponse
    * @exception:
    * @date: ${.now}
    */
    @ApiOperation(value = "根据主键删除", notes = "根据主键删除")
    @RequestMapping(value = "/{${pid}}", method = RequestMethod.DELETE)
    public RestResponse doDelete(@PathVariable <#if 'Long' = pidType>Long<#else>String</#if> ${pid}) {
        RestResponse result = new RestResponse();
        try {
            Integer r = ${simpleServiceImplName?uncap_first}.deleteById(${pid});
            if (r > 0) {
                result.setSuccess(true).setMessage("success");
            } else {
                result.setSuccess(false).setMessage("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            result.setSuccess(false).setMessage("fail");
        }

        return result;
    }


    /**
    * 根据主键获取详情
    *
    * @param ${pid}
    * @Author:
    * @return: com.code.base.util.utils.RestResponse<${className}>
    * @exception:
    * @date: ${.now}
    */
    @ApiOperation(value = "根据id获取权限信息", notes = "根据id获取权限信息")
    @RequestMapping(value = "/{${pid}}", method = RequestMethod.GET)
    public RestResponse<${simpleclassName}> doGetDetail(@PathVariable <#if 'Long' = pidType>Long<#else>
    String</#if> ${pid}) {
        RestResponse result = new RestResponse();
        try {
            ${simpleclassName} ${simpleclassName?uncap_first} = ${simpleServiceImplName?uncap_first}.selectById(${pid});
            result.setSuccess(true).setMessage("success").setData(${simpleclassName?uncap_first});
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            result.setSuccess(false).setMessage("fail");
        }

        return result;
    }


    /**
    * 根据主键更新
    *
    * @param ${pid}
    * @param ${simpleclassName?uncap_first}
    * @Author:
    * @return: com.hollysys.haier.robot.bean.RestResponse
    * @exception:
    * @date: ${.now}
    */
    @ApiOperation(value = "根据主键更新", notes = "根据主键更新")
    @RequestMapping(value = "/{${pid}}", method = RequestMethod.PUT)
    public RestResponse doUpdate(@PathVariable <#if 'Long' = pidType>Long<#else>String</#if> ${pid},
    @RequestBody ${simpleclassName} ${simpleclassName?uncap_first}) {
        RestResponse result = new RestResponse();
        try {
            Integer r = ${simpleServiceImplName?uncap_first}.updateById(${simpleclassName?uncap_first});
            if (r > 0) {
                result.setSuccess(true).setMessage("success");
            } else {
                result.setSuccess(false).setMessage("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            result.setSuccess(false).setMessage("fail");
        }

        return result;
    }


}