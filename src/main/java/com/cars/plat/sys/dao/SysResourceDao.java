package com.cars.plat.sys.dao;

import com.cars.plat.sys.model.SysResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by wangyupeng on 2017/8/18.
 */
@Mapper
public interface SysResourceDao {

    /**
     * 查询资源
     * @return
     */
    List<SysResource> listResource(SysResource sysResource);

    /**
     * 新增资源
     * @param sysResource
     * @return
     */
    int addResource(SysResource sysResource);

    /**
     * 更新资源
     * @param sysResource
     * @return
     */
    int updateResource(SysResource sysResource);

    /**
     * 删除资源
     * @param resourceId
     * @return
     */
    int deleteResource(String resourceId);



    /**
     * 根据资源类型查询资源
     * @param resourceType
     * @return
     */
    List<SysResource> listResourceByType(String resourceType);

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
