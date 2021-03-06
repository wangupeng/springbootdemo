package com.cars.outsource.controller;

import com.cars.outsource.model.Company;
import com.cars.outsource.model.Person;
import com.cars.outsource.service.PersonService;
import com.cars.plat.common.base.BaseController;
import com.cars.plat.util.string.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by wangyupeng on 2018/4/28 22:15
 */
@Controller
@RequestMapping("/outsource/person")
public class PersonController extends BaseController {

    @Autowired
    private PersonService personService;

    /**
     * 查询所有
     * @param person
     * @return
     */
    @RequestMapping
    public ModelAndView list(Person person){
        ModelAndView mv = new ModelAndView();
        PageHelper.startPage(person.getPageNum(), person.getPageSize());
        //查询用户列表
        List<Person> listPerson = personService.select(person);
        PageInfo<Person> pageInfo = new PageInfo<Person>(listPerson);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("outSource/person/listPerson");
        return mv;
    }

    /**
     * 添加
     * @param person
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public int add(Person person){
        person.setId(StringUtil.uuid());
        return personService.insert(person);
    }

    /**
     * 修改
     * @param person
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public int update(Person person){
        return personService.update(person);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public int delete(String id){
        return personService.deleteByPrimaryKey(id);
    }

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getById")
    public Person getById(String id){
        return personService.selectById(id);
    }

}
