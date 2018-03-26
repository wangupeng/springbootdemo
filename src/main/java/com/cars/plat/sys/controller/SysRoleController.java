package com.cars.plat.sys.controller;

import com.cars.plat.sys.model.SysResource;
import com.cars.plat.sys.model.SysRole;
import com.cars.plat.sys.model.SysUser;
import com.cars.plat.sys.service.SysResourceService;
import com.cars.plat.sys.service.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by wangyupeng on 2017/8/18.
 */
@Controller
@RequestMapping("/sysRole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysResourceService sysResourceService;

    /**
     * 角色列表
     * @return
     */
    @RequestMapping
    public ModelAndView listRole(SysRole sysRole){
        ModelAndView mv = new ModelAndView();
        PageHelper.startPage(sysRole.getPageIndex(), sysRole.getPageSize());
        List<SysRole> listRole = sysRoleService.listRole();
        PageInfo<SysRole> pageInfo = new PageInfo<SysRole>(listRole);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("plat/sys/role/listRole");
        return mv;
    }

    /**
     * 添加角色
     * @return
     */
    @RequestMapping("/toAddRole")
    public ModelAndView toAddRole(){
        ModelAndView mv = new ModelAndView();
        List<SysResource> listResource = sysResourceService.listResource();
        mv.addObject("listResource",listResource);
        mv.setViewName("plat/sys/role/addRole");
        return mv;
    }
    @RequestMapping("/addRole")
    public String addRole(HttpServletRequest request, HttpServletResponse response, SysRole sysRole){

        /*String moduleArr = WebUtil.getSafeStr(request.getParameter("moduleArr"));
        String functionArr = WebUtil.getSafeStr(request.getParameter("functionArr"));

        int n = sysRoleService.addRole(sysRole, moduleArr, functionArr);
*/
        return "redirect:/sysRole";
    }

    /**
     * 修改角色
     * @param roleCode
     * @return
     */
    @RequestMapping("/toUpdateRole")
    public ModelAndView toUpdateRole(String roleCode){
        ModelAndView mv = new ModelAndView();
        //查询角色信息
        SysRole sysRole = sysRoleService.getRoleByCode(roleCode);
        mv.addObject("sysRole",sysRole);

        //查询所有资源
        List<SysResource> listResource = sysResourceService.listResource();
        mv.addObject("listResource",listResource);

        //查询已选择的资源
        String checkButton = sysRoleService.checkedResource(sysRole.getRoleCode());
        mv.addObject("checkButton", checkButton);

        mv.setViewName("plat/sys/role/updateRole");
        return mv;
    }
    @RequestMapping("/updateRole")
    @ResponseBody
    public String updateRole(HttpServletRequest request, HttpServletResponse response, SysRole sysRole){

        /*String moduleArr = WebUtil.getSafeStr(request.getParameter("moduleArr"));
        String functionArr = WebUtil.getSafeStr(request.getParameter("functionArr"));

        int n = sysRoleService.updateRole(sysRole, moduleArr, functionArr);*/
        return "redirect:/sysRole";
    }

    /**
     * 删除角色
     * @param roleCode
     * @return
     */
    @RequestMapping("/deleteRole")
    @ResponseBody
    public int deleteRole(String roleCode){
        return sysRoleService.deleteRole(roleCode);
    }

    @RequestMapping("/checkExist")
    @ResponseBody
    public String checkExist(String roleCode){
        SysRole sysRole = sysRoleService.getRoleByCode(roleCode);
        String existFlag = "false";
        if(sysRole==null){
            existFlag ="true";
        }
        return existFlag;
    }
}
