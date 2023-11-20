package com.maker.register.controller.register;

import com.maker.register.annotation.AccessLimit;
import com.maker.register.model.FeeUser;
import com.maker.register.service.feeuser.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.LineNumberInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/register/*")
public class RegisterAction {
    private static final Logger LOGGER= LoggerFactory.getLogger(RegisterAction.class);
    @Autowired
    private UserService userService;

    private List<FeeUser> getAllUsers() throws Exception{
        List<FeeUser> userList=userService.getAllUser();
        return userList;
    }
    @RequestMapping("input")
    @AccessLimit(limit = 5,module = "paper")
    public ModelAndView toInput(){
        ModelAndView mav=new ModelAndView("/register/input");
        try {
            mav.addObject("ulist",getAllUsers());
        }catch (Exception e){
            LOGGER.error("获取付费用户账号失败：{}",e.getMessage());
        }
        return mav;
    }
    @RequestMapping("project_input")
    @AccessLimit(limit = 5,module = "project")
    public ModelAndView toProjectInput(){
        ModelAndView mav=new ModelAndView("/register/project_input");
        try {
            mav.addObject("ulist",getAllUsers());
        }catch (Exception e){
            LOGGER.error("获取付费用户账号失败：{}",e.getMessage());
        }
        return mav;


    }
    @RequestMapping("back/check_paper")
    public String toCheckPaper(){
        return "/register/back/check_paper";
    }
    @RequestMapping("back/check_project")
    public String toCheckProject(){
        return "/register/back/check_project";
    }
    @RequestMapping("back/result_summary")
    public String toResultSummary(){
        return "/register/back/result_summary";
    }


}
