package com.cars.plat.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created by wangyupeng on 2017/12/19.
 * 全局异常处理
 */
@ControllerAdvice
public class ExceptionHandle {
    private static Logger logger = LoggerFactory.getLogger("sysLog");

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ModelAndView handle(Exception e) {
        if (e instanceof MyException) {
            MyException myException = (MyException) e;

            ModelAndView mv = new ModelAndView("plat/common/500");//error页面
            mv.addObject("errorMsg",e.getMessage());
            logger.info( ((MyException) e).getCode() +":"+((MyException) e).getMessage());
            return mv;

//            return ResultUtil.error(myException.getCode(), myException.getMessage());
        }else {
            ModelAndView mv = new ModelAndView("plat/common/500");//error页面
            mv.addObject("errorMsg",e.getMessage());
            logger.info(e.getMessage());
            return mv;

//            logger.error("【系统异常】{}", e);
//            return ResultUtil.error(-1, e.getMessage());
        }
    }
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView noMapping(Exception exception, WebRequest request) {
        logger.info("No mapping exception", exception);
        return  new ModelAndView("plat/common/404");
    }
}
