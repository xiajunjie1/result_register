package com.maker.register.controller.register.user;

import com.maker.register.model.FeeUser;
import com.maker.register.service.feeuser.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/user/*")
public class UserAction {
    @Autowired
    private UserService userService;
    private static final Logger LOGGER= LoggerFactory.getLogger(UserAction.class);
    @RequestMapping("getalluser")
    @ResponseBody
    public Object getAllUser(){
        List<FeeUser> feeUsers=new ArrayList<>();
        try{
            feeUsers=userService.getAllUser();


        }catch (Exception e){
            LOGGER.error("【获取所有用户出现异常：{}】",e.getMessage());
        }
        return feeUsers;
    }
}
