package com.cars.plat.sys.dao;

import com.cars.plat.sys.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserDao {

    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    int addUser(SysUser sysUser);

    /**
     * 删除用户
     * @param userName
     * @return
     */
    int deleteUser(String userName);

    /**
     * 修改用户
     * @param sysUser
     * @return
     */
    int updateUser(SysUser sysUser);
    /**
     * 获取用户信息
     * @return
     */
    List<SysUser> listUser(SysUser sysUser);

    /**
     * 查询总数
     * @param sysUser
     * @return
     */
    int count(SysUser sysUser);

    /**
     *
     * @param sysUser
     * @return
     */
    List<SysUser> listUserByInner(SysUser sysUser);

    /**
     * 根据用户ID获取用户信息
     * @param userName
     * @return
     */
    //SysUser getUserByuserName(String userName);
    /**
     * 根据用户ID获取用户信息
     * @param userName
     * @return
     */
    SysUser getUserByUserName(String userName);

    /**
     * 锁定用户
     * @param userName
     * @return
     */
    int lockUser(String userName);

    /**
     * 解锁用户
     * @param userName
     * @return
     */
    int unlockUser(String userName);

    /**
     * 重置密码
     * @param sysUser
     * @return
     */
    int resetPassWord(SysUser sysUser);

    /**
     * 修改密码
     * @return
     */
    SysUser getOldPassSalt(String userName);
    int updatePassWord(SysUser sysUser);
}