package com.cars.plat.sys.controller;

import com.cars.plat.sys.model.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by wangyupeng on 2017/8/18.
 */
@Controller
public class LoginController {

    @RequestMapping(value="/login",method= RequestMethod.GET)
    public String toLogin(HttpServletRequest request){
        return "plat/sys/login";
    }

    @RequestMapping(value="/login",method= RequestMethod.POST)
    public String login(HttpServletRequest request, @Valid SysUser user,BindingResult result){
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassWord())) {
            request.setAttribute("msg", "用户名或密码不能为空！");
            return "plat/sys/login";
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(user.getUserName(),user.getPassWord());
        try {
            subject.login(token);
            return "redirect:index";
//            return "plat/sys/index";
        }catch (LockedAccountException lae) {
            token.clear();
            request.setAttribute("msg", "用户已经被锁定不能登录，请与管理员联系！");
            return "plat/sys/login";
        } catch (AuthenticationException e) {
            token.clear();
            request.setAttribute("msg", "用户或密码不正确！");
            return "plat/sys/login";
        }
    }

    @RequestMapping("/index")
    public String index(){
        return "plat/sys/index";
        //return "plat/demo/signup/signup";
        //return "greeting";
    }

    @RequestMapping("/lm")
    public String lm(){
        return "plat/sys/system-category";
        //return "plat/demo/signup/signup";
        //return "greeting";
    }
}
