package com.cars.server.controller;

import com.cars.plat.common.base.BaseController;
import com.cars.server.model.Project;
import com.cars.server.service.ProjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by wangyupeng on 2018/5/3 22:25
 */
@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController {
    @Autowired
    private ProjectService projectService;

    /**
     * 查询所有
     * @return
     *//*
    @RequestMapping
    public ModelAndView listProject(Project project){
        ModelAndView mv = new ModelAndView();
        PageHelper.startPage(project.getPageIndex(), project.getPageSize());
        //查询用户列表
        List<Project> listProject = projectService.selectAll();
        PageInfo<Project> pageInfo = new PageInfo<Project>(listProject);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("server/project/listProject");
        return mv;
    }

    @RequestMapping("/add")
    public int addProject(Project project) {
        project.setCreateUser(userSession.getUserName());
        return projectService.insert(project);
    }*/

}
