package com.cars.plat.sys.controller;

import com.cars.plat.sys.model.SysRole;
import com.cars.plat.sys.model.SysUser;
import com.cars.plat.sys.service.SysRoleService;
import com.cars.plat.sys.service.SysUserService;
import com.cars.plat.util.result.ResultEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by wangyupeng on 2017/8/18.
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController {
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysRoleService sysRoleService;

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
    @RequestMapping("/toAddUser")
    public ModelAndView toAddUser(SysUser sysUser){
        ModelAndView mv = new ModelAndView();
        //查询所有角色
        List<SysRole> listRole = sysRoleService.listRole();
        mv.addObject("listRole",listRole);
        mv.setViewName("plat/sys/user/addUser");
        return mv;
    }
    @RequestMapping("/addUser")
    public String addUser(SysUser sysUser){
        int n = userService.addUser(sysUser);
        return "redirect:/sysUser";
    }

    /**
     * 修改用户
     * @param userName
     * @return
     */
    @RequestMapping("/toUpdateUser")
    public ModelAndView toUpdateUser(String userName){
        ModelAndView mv = new ModelAndView();
        //根据用户ID查询用户信息
        SysUser sysUser = userService.getUserByUserName(userName);
        mv.addObject("sysUser",sysUser);

        //查询所有角色
        List<SysRole> listRole = sysRoleService.listRole();
        mv.addObject("listRole",listRole);

        mv.setViewName("plat/sys/user/updateUser");
        return mv;
    }
    @RequestMapping("/updateUser")
    public ModelAndView updateUser(SysUser sysUser, RedirectAttributes attr){
        ModelAndView mv = new ModelAndView();
        int n = userService.updateUser(sysUser);
        attr.addFlashAttribute("message", ResultEnum.UPDATE_SUCCESS);
        mv.setViewName("redirect:/sysUser");
        return mv;
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

    @ResponseBody
    @RequestMapping("/updatePassWord")
    public int updatePassWord(SysUser sysUser){
        SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("userSession");
        ModelAndView mv = new ModelAndView();

        int n = 0;
        if(userService.checkOldPassWord(user.getUserName(),sysUser.getOldPassWord())){
            n = userService.updatePassWord(sysUser);
        }else{
            n=2;
        }
        return n;
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
