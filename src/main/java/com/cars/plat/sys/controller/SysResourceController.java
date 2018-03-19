package com.cars.plat.sys.controller;

import com.cars.plat.sys.model.SysResource;
import com.cars.plat.sys.model.SysUser;
import com.cars.plat.sys.service.SysResourceService;
import com.cars.plat.util.cache.EhCacheUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/sysResource")
public class SysResourceController {
    @Autowired
    private SysResourceService sysResourceService;

    /**
     * 显示所有资源列表
     * @return
     */
    @RequestMapping
    public ModelAndView listResource(){
        ModelAndView mv = new ModelAndView();
//        List<SysResource> listResource = sysResourceService.listResource();
        Map<String,Object> map = new HashMap<>();
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("userSession");
        //todo
//        map.put("userName",sysUser.getuserName());
        List<SysResource> listResource = sysResourceService.listResource();

        mv.addObject("listResource",listResource);
        mv.setViewName("plat/sys/resource/listResource");
        return mv;
    }

    /**
     * 转到新增资源页面
     * @param request
     * @param response
     * @param sysResource
     * @return
     */
    @RequestMapping("/toAddResource")
    public ModelAndView toAddResource(HttpServletRequest request, HttpServletResponse response, SysResource sysResource){
        ModelAndView mv = new ModelAndView();
        String ztree = sysResourceService.createZtreeByModule();//模块列表
        mv.addObject("ztree", ztree);
        if (sysResource.getParentId() != null && !sysResource.getParentId().equals("0")) {
            SysResource parent = sysResourceService.getResourceById(sysResource.getParentId());
            sysResource.setParentName(parent.getResourceName());
            sysResource.setResourceType("2");//设置为增加功能按钮界面
        } else {
            sysResource.setResourceType("1");//设置为模块
        }
        mv.addObject("sysResource", sysResource);
        mv.setViewName("plat/sys/resource/addResource");
        return mv;
    }

    /**
     * 新增资源
     * @param sysResource
     * @return
     */
    @RequestMapping("/addResource")
    public String addResource(SysResource sysResource){
        int n = sysResourceService.addResource(sysResource);
        return "redirect:/sysResource";
    }

    /**
     * 转到修改资源页面
     * @param resourceId
     * @return
     */
    @RequestMapping("/toUpdateResource")
    public ModelAndView toUpdateResource(String resourceId){
        ModelAndView mv = new ModelAndView();
        String ztree = sysResourceService.createZtreeByModule();//模块列表
        mv.addObject("ztree", ztree);

        SysResource sysResource = sysResourceService.getResourceById(resourceId);
        mv.addObject("sysResource",sysResource);

        mv.setViewName("plat/sys/resource/updateResource");
        return mv;
    }

    /**
     * 修改资源
     * @param sysResource
     * @return
     */
    @RequestMapping("/updateResource")
    public String updateResource(SysResource sysResource){
        int n = sysResourceService.updateResource(sysResource);
        return "redirect:/sysResource";
    }

    /**
     * 删除资源
     * @param resourceId
     * @return
     */
    @RequestMapping("/deleteResource")
    public String deleteResource(String resourceId){
        int n = sysResourceService.deleteResource(resourceId);
        return "redirect:/sysResource";
    }

    /**
     * 查询用户拥有的菜单
     * @return
     */
    @ResponseBody
    @RequestMapping("/loadMenu")
    public List<SysResource> loadMenu(){
        Map<String,Object> map = new HashMap<>();
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("userSession");
        map.put("resourceType",1);
        map.put("userName",sysUser.getUserName());
        List<SysResource> resourceList = sysResourceService.loadUserResource(map);
        return resourceList;
    }

    /**
     * 查询一个用户所有权限
     * @return
     */
    /*@ResponseBody
    @RequestMapping("/loadUserResource")
    public List<SysResource> loadUserResource(){
        Map<String,Object> map = new HashMap<>();
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("userSession");
        map.put("userName",sysUser.getuserName());

        List<SysResource> resourceList = EhCacheUtil.get("resourceCache", "resources");
        if (resourceList == null) {
            resourceList = sysResourceService.loadUserResource(map);
            EhCacheUtil.put("resourceCache", "resources", resourceList);
        }
        return resourceList;
    }*/

}
