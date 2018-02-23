package com.cars.plat.sys.dao;

import com.cars.plat.sys.model.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by wangyupeng on 2017/8/18.
 */
@Mapper
public interface SysRoleDao {
    /**
     * 新增角色
     * @param sysRole
     * @return
     */
    int addRole(SysRole sysRole);

    /**
     * 修改
     * @param sysRole
     * @return
     */
    int updateRole(SysRole sysRole);

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    int deleteRole(String roleId);

    /**
     * 查询角色
     * @return
     */
    List<SysRole> listRole(SysRole sysRole);
    /**
     * 查询角色
     * @return
     */
    List<SysRole> listRoleForUser();

    /**
     * 查询总数
     * @return
     */
    int count();
    /**
     * 获取角色信息
     * @param roleId
     * @return
     */
    SysRole getRole(String roleId);

    /**
     * 删除角色资源表中某个角色ID的角色资源关系
     * @param roleId
     * @return
     */
    int deleteRoleResourceByRoleId(String roleId);
}
