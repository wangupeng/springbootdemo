package com.cars.plat.sys.dao;

import com.cars.plat.sys.model.SysResource;
import com.cars.plat.sys.model.SysRoleResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by wangyupeng on 2017/8/18.
 */
@Mapper
public interface SysRoleResourceDao {

    /**
     * 增加角色资源对应关系
     * @param map
     * @return
     */
    int addRoleResource(Map<String, Object> map);

    /**
     * 根据角色id删除角色资源对应关系
     * @param roleCode
     * @return
     */
    int deleteRoleResourceByRoleCode(String roleCode);

    /**
     * 根据资源id删除角色资源对应关系
     * @param resourceId
     * @return
     */
    int deleteRoleResourceResourceId(String resourceId);


    /**
     * 根据角色ID获取资源
     * @param roleCode
     * @return
     */
    List<SysResource> listResourceByRoleCode(String roleCode);
}
