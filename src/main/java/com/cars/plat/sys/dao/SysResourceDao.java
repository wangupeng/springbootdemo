package com.cars.plat.sys.dao;

import com.cars.plat.common.base.BaseDao;
import com.cars.plat.sys.model.SysResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by wangyupeng on 2017/8/18.
 */
@Mapper
public interface SysResourceDao extends BaseDao<SysResource> {

    /**
     * 查询资源
     * @return
     */
    List<SysResource> listResource(SysResource sysResource);

    /**
     * 删除资源
     * @param resourceId
     * @return
     */
    int deleteResource(String resourceId);

    /**
     * 根据ID类型查询资源
     * @param resourceId
     * @return
     */
    SysResource getResourceById(String resourceId);

    /**
     * 获取菜单资源
     * @param map
     * @return
     */
    List<SysResource> loadUserResource(Map<String, Object> map);
}
