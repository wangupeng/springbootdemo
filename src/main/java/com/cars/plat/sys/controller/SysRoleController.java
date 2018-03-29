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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String toAddRole(){
        return "plat/sys/role/addRole";
    }

    @RequestMapping("/addRole")
    public String addRole(SysRole sysRole){
        int n = sysRoleService.addRole(sysRole);
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
        mv.setViewName("plat/sys/role/updateRole");
        return mv;
    }

    @RequestMapping("/updateRole")
    public String updateRole(SysRole sysRole){
        int n = sysRoleService.updateRole(sysRole);
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

    /**
     * 授权
     * @param roleCode
     * @return
     */
    @RequestMapping("/addRoleResource")
    @ResponseBody
    public int addRoleResource(String roleCode,@RequestParam(value = "resourceIds",required = false)String[] resourceIds){
        Map<String, Object> map = new HashMap<>();
        map.put("roleCode", roleCode);
        map.put("resourceIds", resourceIds);
        return sysRoleService.addRoleResource(map);
    }

    /**
     * 根据角色ID获取资源
     * @param roleCode
     * @return
     */
    @RequestMapping("/listResourceByRoleCode")
    @ResponseBody
    public List<SysResource> listResourceByRoleCode(String roleCode){
        List<SysResource> list = sysRoleService.listResourceByRoleCode(roleCode);
        return list;
    }

    /**
     * 判断roleCode是否已存在
     * @param roleCode
     * @return
     */
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
