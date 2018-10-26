package com.code.base.util.utils;

import java.util.ArrayList;
import java.util.List;
/**
 * 分页工具类
 * @auth baitao
 * @date 2018.8.31
 *
 */
public class PageUtil {

    public static List<String> getIdsForList(String ids){
        List<String> list = new ArrayList<String>();
        if(ValidateUtils.isNotNullStr(ids)){
            String[] allId = ids.split(",");
            if (ValidateUtils.isNotNullArray(allId)){
                for(int i=0;i<allId.length;i++){
                    list.add(allId[i]);
                }
            }
        }
        return list;
    }
}
