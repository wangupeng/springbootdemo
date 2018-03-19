package com.cars.plat.sys.service;

import com.cars.plat.common.password.PasswordHelper;
import com.cars.plat.sys.dao.SysUserDao;
import com.cars.plat.sys.model.SysUser;
import com.cars.plat.util.date.DateUtil;
import com.cars.plat.util.string.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by wangyupeng on 2017/8/18.
 */
@Component
public class SysUserService {
    @Autowired
    private SysUserDao userDao;

    /**
     * 用户列表
     * @param sysUser
     * @return
     */
    public List<SysUser> listUser(SysUser sysUser){
        List<SysUser> list = userDao.listUser(sysUser);
        return list;
    }

    /**
     * 查询总数
     * @param sysUser
     * @return
     */
    public int count(SysUser sysUser){
        int recordCount = userDao.count(sysUser);
        return recordCount;
    }

    /**
     * 根据用户标识和公司查询用户
     * @param sysUser
     * @return
     */
    /*public List<SysUser> listUserByInner(SysUser sysUser){
        List<SysUser> list = userDao.listUserByInner(sysUser);
        return list;
    }*/

    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    @Transactional
    public int addUser(SysUser sysUser){
        //密码加密，及存储盐值
        sysUser.setPassWord("123456");//设置默认密码
        PasswordHelper passwordHelper = new PasswordHelper();
        passwordHelper.encryptPassword(sysUser);

        //获取当前登录用户
        SysUser sysUser2 = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("userSession");
        sysUser.setCreateUser(sysUser2.getUserName());
        sysUser.setLastModifiedUser(sysUser2.getUserName());

        int n = userDao.addUser(sysUser);
        return n;
    }

    /**
     * 删除用户
     * @param userName
     * @return
     */
    @Transactional
    public int deleteUser(String userName){
        int n = userDao.deleteUser(userName);
        return n;
    }

    /**
     * 修改用户
     * @param sysUser
     * @return
     */
    @Transactional
    public int updateUser(SysUser sysUser){
        //获取当前登录用户
        SysUser sysUser2 = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("userSession");
        sysUser.setLastModifiedUser(sysUser2.getUserName());
        sysUser.setLastModifiedDate(new Date());

        int n = userDao.updateUser(sysUser);
        return n;
    }

    /**
     * 锁定用户
     * @param userName
     * @return
     */
    @Transactional
    public int lockUser(String userName){
        int n = userDao.lockUser(userName);
        return n;
    }

    /**
     * 解锁用户
     * @param userName
     * @return
     */
    @Transactional
    public int unlockUser(String userName){
        int n = userDao.unlockUser(userName);
        return n;
    }

    /**
     * 重置密码
     * @param userName
     * @return
     */
    @Transactional
    public int resetPassWord(String userName){
        SysUser sysUser = new SysUser();
        //todo 重置密码
//        sysUser.setuserName(userName);
        //密码加密，及存储盐值
        sysUser.setPassWord("123456");//设置默认密码
        PasswordHelper passwordHelper = new PasswordHelper();
        passwordHelper.encryptPassword(sysUser);

        int n = userDao.resetPassWord(sysUser);
        return n;
    }


    /**
     * 判断页面获取的旧密码是否与数据库存储密码相同
     * @param userName
     * @param oldPassWord
     * @return
     */
    public boolean checkOldPassWord(String userName,String oldPassWord){
        boolean flag = false;
        if(StringUtil.isNotNullOrEmpty(oldPassWord)){
            //获取数据库存储的密码和盐
            SysUser sysUser = userDao.getOldPassSalt(userName);
            //对页面获取的旧密码用数据库查出的盐加密，与数据库查出的密码比较
            PasswordHelper passwordHelper = new PasswordHelper();
            String pOldPassWord = passwordHelper.encryptPasswordWithSalt(oldPassWord,sysUser.getSalt());

            if(pOldPassWord.equals(sysUser.getPassWord())){
                flag = true;
            }
        }
        return flag;
    }
    /**
     * 修改密码
     * @param sysUser
     * @return
     */
    @Transactional
    public int updatePassWord(SysUser sysUser){
        //获取当前登录用户
        SysUser sysUser2 = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("userSession");
        //密码加密，及存储盐值
        //todo 修改密码
//        sysUser.setuserName(sysUser2.getuserName());
        PasswordHelper passwordHelper = new PasswordHelper();
        passwordHelper.encryptPassword(sysUser);

        int n = userDao.updatePassWord(sysUser);
        return n;
    }

    /**
     * 通过用户ID获取用户信息
     * @param userName
     * @return
     */
    /*public SysUser getUserByuserName(String userName){
        SysUser sysUser = userDao.getUserByUserName(userName);
        return sysUser;
    }*/
    /**
     * 通过用户名获取用户信息
     * @param userName
     * @return
     */
    public SysUser getUserByUserName(String userName){
        SysUser sysUser = userDao.getUserByUserName(userName);
        return sysUser;
    }
}
