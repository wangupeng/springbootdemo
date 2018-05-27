package com.cars.server.controller;

import com.cars.plat.common.base.BaseController;
import com.cars.plat.sys.model.SysUser;
import com.cars.plat.util.date.DateUtil;
import com.cars.plat.util.string.StringUtil;
import com.cars.server.model.Project;
import com.cars.server.model.Weblogic;
import com.cars.server.service.ProjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by wangyupeng on 2018/5/3 22:25
 */
@Controller
@RequestMapping("/server/project")
public class ProjectController extends BaseController {
    @Autowired
    private ProjectService projectService;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping
    public ModelAndView listProject(Project project){
        ModelAndView mv = new ModelAndView();
        PageHelper.startPage(project.getPageNum(), project.getPageSize());
        List<Project> listProject = projectService.selectAll();
        PageInfo<Project> pageInfo = new PageInfo<Project>(listProject);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("server/project/listProject");
        return mv;
    }

    /**
     * 添加
     * @param project
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public int addProject(Project project,@SessionAttribute SysUser userSession) {
        project.setProjectId(StringUtil.uuid());
        project.setCreateUser(userSession.getUserName());
        project.setCreateDate(DateUtil.getSystemDate());
        project.setUpdateUser(userSession.getUserName());
        project.setUpdateDate(DateUtil.getSystemDate());
        return projectService.insert(project);
    }

    /**
     * 修改
     * @param project
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public int updateProject(Project project){
        return projectService.update(project);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public int deleteProject(String id){
        return projectService.deleteByPrimaryKey(id);
    }

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getById")
    public Project getProjectById(String id){
        return projectService.selectById(id);
    }

}
