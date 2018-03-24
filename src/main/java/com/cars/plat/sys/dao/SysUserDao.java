package com.cars.plat.sys.dao;

import com.cars.plat.sys.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserDao {

    /**
     * 用户列表
     * @return
     */
    List<SysUser> listUser(SysUser sysUser);
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
     * 根据用户名获取用户信息
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
     * 获取旧密码的盐
     * @param userName
     * @return
     */
    SysUser getOldPassSalt(String userName);
    /**
     * 修改密码
     * @return
     */
    int updatePassWord(SysUser sysUser);
}