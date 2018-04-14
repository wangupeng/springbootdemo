package com.cars.plat.sys.controller;

import com.cars.plat.common.aop.UserAccessAnnotation;
import com.cars.plat.common.task.MyScheduler;
import com.cars.plat.sys.model.SysUser;
import com.cars.plat.sys.service.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by wangyupeng on 2017/8/18.
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController {
    @Autowired
    private SysUserService userService;

    /**
     * 用户列表
     * @param sysUser
     * @return
     */
    @RequestMapping
    public ModelAndView listUser(SysUser sysUser){
        ModelAndView mv = new ModelAndView();
        PageHelper.startPage(sysUser.getPageIndex(), sysUser.getPageSize());
        //查询用户列表
        List<SysUser> listUser = userService.listUser(sysUser);
        PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(listUser);
        mv.addObject("pageInfo",pageInfo);

        mv.addObject("sysUser",sysUser);
        mv.setViewName("plat/sys/user/listUser");
        return mv;
    }

    /**
     * 添加用户
     * @param sysUser
     * @return
     */
    @ResponseBody
    @RequestMapping("/addUser")
    public int addUser(SysUser sysUser){
        return userService.addUser(sysUser);
    }

    /**
     * 修改用户
     * @param sysUser
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateUser")
    public int updateUser(SysUser sysUser){
        return userService.updateUser(sysUser);
    }

    /**
     * 删除用户
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteUser")
    public int deleteUser(String userName){
        return userService.deleteUser(userName);
    }

    /**
     * 锁定用户
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping("/lockUser")
    public int lockUser(String userName){
        return userService.lockUser(userName);
    }

    /**
     * 解锁用户
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping("/unlockUser")
    public int unlockUser(String userName){
        return userService.unlockUser(userName);
    }

    /**
     * 重置密码
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping("/resetPassWord")
    public int resetPassWord(String userName){
        return userService.resetPassWord(userName);
    }

    /**
     * 修改密码
     * @param sysUser
     * @return
     */
    @ResponseBody
    @RequestMapping("/updatePassWord")
    public int updatePassWord(SysUser sysUser){
        SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("userSession");
        int n = 0;
        if(userService.checkOldPassWord(user.getUserName(),sysUser.getOldPassWord())){
            n = userService.updatePassWord(sysUser);
        }else{
            //原密码不正确
            n=2;
        }
        return n;
    }

    /**
     * 根据用户名获取用户
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUserByUserName")
    public SysUser getUserByUserName(String userName){
        return userService.getUserByUserName(userName);
    }

    @RequestMapping("/checkExist")
    @ResponseBody
    public String checkExist(String userName){
        SysUser sysUser = userService.getUserByUserName(userName);
        String existFlag = "false";
        if(sysUser==null){
            existFlag ="true";
        }
        return existFlag;
    }
}
