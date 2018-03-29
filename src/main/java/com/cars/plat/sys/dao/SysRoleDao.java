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
     * 查询角色
     * @return
     */
    List<SysRole> listRole();

    /**
     * 新增角色
     * @param sysRole
     * @return
     */
    int addRole(SysRole sysRole);

    /**
     * 根据roleCode获取角色信息
     * @param roleCode
     * @return
     */
    SysRole getRoleByCode(String roleCode);

    /**
     * 修改
     * @param sysRole
     * @return
     */
    int updateRole(SysRole sysRole);

    /**
     * 删除角色
     * @param roleCode
     * @return
     */
    int deleteRole(String roleCode);
}
