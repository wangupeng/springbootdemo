package com.cars.plat.sys.controller;

import com.cars.plat.sys.model.SysResource;
import com.cars.plat.sys.model.SysRole;
import com.cars.plat.sys.service.SysResourceService;
import com.cars.plat.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
        //添加分页
        int totalRecords = sysRoleService.count(); //总数
//        PageNavigate pageNavigate = new PageNavigate("", sysRole.getPageIndex(), totalRecords);//定义分页对象,传入url、当前页、总记录数
//        mv.addObject("pageNavigate", pageNavigate);// 设置分页的变量

//        List<SysRole> listRole = sysRoleService.listRole(sysRole);
//        mv.addObject("listRole",listRole);
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
       /* if (n == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("角色新增失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("角色新增成功");*/
        return "redirect:/sysRole";
    }

    /**
     * 修改角色
     * @param roleId
     * @return
     */
    @RequestMapping("/toUpdateRole")
    public ModelAndView toUpdateRole(String roleId){
        ModelAndView mv = new ModelAndView();
        //查询角色信息
        SysRole sysRole = sysRoleService.getRole(roleId);
        mv.addObject("sysRole",sysRole);

        //查询所有资源
        List<SysResource> listResource = sysResourceService.listResource();
        mv.addObject("listResource",listResource);

        //查询已选择的资源
        String checkButton = sysRoleService.checkedResource(sysRole.getRoleId());
        mv.addObject("checkButton", checkButton);

        mv.setViewName("plat/sys/role/updateRole");
        return mv;
    }
    @RequestMapping("/updateRole")
    public String updateRole(HttpServletRequest request, HttpServletResponse response, SysRole sysRole){

        /*String moduleArr = WebUtil.getSafeStr(request.getParameter("moduleArr"));
        String functionArr = WebUtil.getSafeStr(request.getParameter("functionArr"));

        int n = sysRoleService.updateRole(sysRole, moduleArr, functionArr);*/
        return "redirect:/sysRole";
    }

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    @RequestMapping("/deleteRole")
    public String deleteRole(String roleId){
        sysRoleService.deleteRole(roleId);
        return "redirect:/sysRole";
    }
}
