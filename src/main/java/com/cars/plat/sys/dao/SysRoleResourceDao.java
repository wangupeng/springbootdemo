package com.cars.plat.sys.dao;

import com.cars.plat.sys.model.SysResource;
import com.cars.plat.sys.model.SysRoleResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by wangyupeng on 2017/8/18.
 */
@Mapper
public interface SysRoleResourceDao {

    /**
     * 增加角色资源对应关系
     * @param sysRoleResource
     * @return
     */
    int addRoleResource(SysRoleResource sysRoleResource);

    /**
     * 删除角色资源对应关系
     * @param roleId
     * @return
     */
    int deleteRoleResourceByRoleId(String roleId);
    int deleteRoleResourceResourceId(String resourceId);


    /**
     * 根据角色ID获取资源
     * @param roleId
     * @return
     */
    List<SysResource> listResourceByRoleId(String roleId);
}
