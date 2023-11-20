package com.maker.register.controller.register.project;

import com.maker.register.model.Project;
import com.maker.register.model.ProjectParam;
import com.maker.register.service.project.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/project/*")
public class ProjectAction {
    private static final Logger LOGGER= LoggerFactory.getLogger(ProjectAction.class);
    @Autowired
    private ProjectService projectService;
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    @RequestMapping("save")
    @ResponseBody
    public Integer saveProjects(@RequestBody ProjectParam projectParam){
    String account=projectParam.getAccount();
    String date=projectParam.getDate();
    String  informant=projectParam.getInformant();
    String email=projectParam.getEmail();
    String[] pid=projectParam.getPid();
    String[] pname=projectParam.getPname();
    String[] money=projectParam.getMoney();
    String[] type=projectParam.getType();
    String[] phost=projectParam.getPhost();
    if(account==null || "".equals(account)){
        LOGGER.error("奖励账号为空！");
        return 0;
    }
    if(date==null || "".equals(date)){
        date=sdf.format(new Date());
    }
    try{
        if(pid.length==pname.length && pid.length== money.length && pid.length== type.length && pid.length== phost.length){
            List<Project> projects=new ArrayList<>();
            for(int i=0;i<pid.length;i++){
                Project project=new Project();
                project.setPid(pid[i]);
                project.setPname(pname[i]);
                project.setAccount(account);
                project.setMoney(money[i]);
                project.setType(type[i]);
                project.setPhost(phost[i]);
                project.setInformant(informant);
                project.setEmail(email);
                project.setDate(date);
                project.setTimestamp(new Date().getTime());
                project.setBonus(0.0);
                project.setIsGrant(0);
                projects.add(project);
            }
            return projectService.insertProjects(projects);
        }
    }catch (Exception e){
        LOGGER.error("新增Project出现异常：{}",e.getMessage());
    }
        return 0;
    }
    @RequestMapping("getProjectsByDate")
    @ResponseBody
    public Object getProjectsByDate(String startDate,String endDate,Integer isGrant){
        Map<String,Object> result=new HashMap<>();
        if(isGrant==null){
            isGrant=0;
        }
        if(startDate==null || "".equals(startDate) || endDate==null || "".equals(endDate)){
            LOGGER.error("日期参数异常！");
            result.put("code",0);
            result.put("message","日期参数异常");
            return result;
        }
        Map<String,Object> reqParam=new HashMap<>();
        reqParam.put("startDate",startDate);
        reqParam.put("endDate",endDate);
        reqParam.put("isGrant",isGrant);
        try{
        List<Project> projects=projectService.getProjectsByDate(reqParam);
        result.put("code",1);
        result.put("pdata",projects);
        }catch (Exception e){
            LOGGER.error("根据日期查询项目出现异常:{}",e.getMessage());
            result.put("code",0);
            result.put("message","查询项目异常...");
        }
        return result;
    }
    @RequestMapping("replace")
    @ResponseBody
    public Object replaceProjects(@RequestBody List<Project> projects){
        Map<String,Object> result=new HashMap<>();
        if(projects==null || projects.isEmpty()){
            result.put("code",0);
            result.put("message","传入项目参数为空");
            return result;
        }
       try{
           Integer itemnum=projectService.replaceProjects(projects);
           result.put("code",1);
           result.put("itemnum",itemnum);
       }catch (Exception e){
           LOGGER.error("更新替换项目数据出现异常：{}",e.getMessage());
           result.put("code",0);
           result.put("message","更新项目出现异常！");
       }

        return result;

    }
}
